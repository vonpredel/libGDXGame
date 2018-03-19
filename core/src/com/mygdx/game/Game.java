package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.EntitiesContainer;
import com.mygdx.game.Entities.EntitiesManager;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.EntityType;
import com.mygdx.game.Entities.NonStatics.Enemy;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.ItemsContainer;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Utils.CameraHandler;
import com.mygdx.game.Utils.Debugger;
import com.mygdx.game.Utils.Updater;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.World.World;
import com.mygdx.game.Zones.ZoneContainer;
import com.mygdx.game.Zones.ZoneRenderer;
import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;
	private Enemy enemy;
	private Assets assets;
	private ControlsAndGUIsHandler controlsAndGUIsHandler;
	private CameraHandler cameraHandler;
	private ItemsContainer itemsContainer;
	private EntitiesContainer entitiesContainer;
	private Updater updater;
	private ZoneRenderer zoneRenderer;
	private ZoneContainer zoneContainer;
	private List<Entity> entities;
	private ItemsManager itemsManager;
	private EntitiesManager entitiesManager;

	private Debugger debugger;
	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();
		if(assets.manager.update()) {
			loadEntities();
			loadData();
			worldInit();
		}
	}

    private void loadEntities() {
		player = new Player(assets,100,100);
//		enemy = new Enemy(assets,200,200);
		entities = new ArrayList<>();
        entities.add(player);
//        entities.add(enemy);
    }

	private void loadData()  {
		batch = new SpriteBatch();
		font = new BitmapFont();
		itemsContainer = new ItemsContainer();
		entitiesContainer = new EntitiesContainer(this.font);
		itemsManager = new ItemsManager(assets, itemsContainer);
		entitiesManager = new EntitiesManager(assets, entitiesContainer, itemsManager);
		itemsManager.loadDefinitions();
		entitiesManager.loadDefinitions();
		controlsAndGUIsHandler = new ControlsAndGUIsHandler(player,assets);
		cameraHandler = new CameraHandler(batch,player);
        updater = new Updater();
        zoneContainer = new ZoneContainer(assets);
		zoneRenderer = new ZoneRenderer(batch);
		zoneRenderer.setZone(zoneContainer.getZoneList().get(0));

		debugger = new Debugger();
		debugger.setDebug(true);
    }

	private void worldInit() {
		World.init(entities,zoneRenderer,zoneContainer,batch,font,assets,itemsManager, cameraHandler, entitiesManager, player);
		entities.forEach(e -> {
			if(e instanceof NonStatic) ((NonStatic) e).initializeInventory();
		});
		entitiesContainer.addItem(player);
		entitiesManager.create(EntityType.NIGGA,(entity, objects) -> {
			((NonStatic) entity).warp((Float) objects[0], (Float) objects[1]);
		},600.0f,600.0f);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		zoneRenderer.renderZone();
		itemsContainer.draw(batch);

		//
		entitiesContainer.draw(batch);

		//
//		entities.forEach(e->e.draw(batch,font));
		controlsAndGUIsHandler.draw(batch);
		batch.end();
	}

	private void update() {
		debugger.update();
		cameraHandler.update();
		controlsAndGUIsHandler.update();
		itemsContainer.update();
		entitiesContainer.update();
    }


	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
	}
}