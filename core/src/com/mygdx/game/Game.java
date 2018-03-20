package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.EntitiesContainer;
import com.mygdx.game.Entities.EntitiesManager;
import com.mygdx.game.Entities.EntityType;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.ItemsContainer;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Utils.CameraHandler;
import com.mygdx.game.Utils.Debugger;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.World.World;
import com.mygdx.game.Zones.ZoneContainer;
import com.mygdx.game.Zones.ZoneRenderer;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Player player;
	private Assets assets;
	private ControlsAndGUIsHandler controlsAndGUIsHandler;
	private CameraHandler cameraHandler;
	private ItemsContainer itemsContainer;
	private EntitiesContainer entitiesContainer;
	private ZoneRenderer zoneRenderer;
	private ZoneContainer zoneContainer;
	private ItemsManager itemsManager;
	private EntitiesManager entitiesManager;

	private Debugger debugger;
	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();
		if(assets.manager.update()) {
			loadData();
			worldInit();
			loadEntities();
			configure();
		}
	}

	private void loadData()  {
		batch = new SpriteBatch();
		itemsContainer = new ItemsContainer();
		entitiesContainer = new EntitiesContainer();
		itemsManager = new ItemsManager(assets, itemsContainer);
		entitiesManager = new EntitiesManager(assets, entitiesContainer, itemsManager);
		itemsManager.loadDefinitions();
		entitiesManager.loadDefinitions();
		controlsAndGUIsHandler = new ControlsAndGUIsHandler(assets);
		cameraHandler = new CameraHandler(batch);
        zoneContainer = new ZoneContainer(assets);
		zoneRenderer = new ZoneRenderer(batch);

		zoneRenderer.setZone(zoneContainer.getZoneList().get(0));

		debugger = new Debugger();
		debugger.setDebug(true);
    }

	private void worldInit() {
		World.init(zoneRenderer,zoneContainer,batch,assets,itemsManager, cameraHandler, entitiesManager);

	}

	private void loadEntities() {
		entitiesManager.create(EntityType.PLAYER,(entity, objects)
				-> entity.warp(128,128));
		entitiesManager.create(EntityType.NIGGA,(entity, objects)
				-> entity.warp(600,600));
		entitiesManager.create(EntityType.CHEST, (entity, objects)
				-> entity.warp(256,64));
	}

	private void configure() {
		player = (Player) entitiesContainer.getAllItems().get(0);
		World.setPlayer(player);
		cameraHandler.focusOn(player);
		controlsAndGUIsHandler.initGUIs(player);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		zoneRenderer.renderZone();
		itemsContainer.draw(batch);
		entitiesContainer.draw(batch);
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