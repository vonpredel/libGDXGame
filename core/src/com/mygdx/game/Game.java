package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.Characters.Character;
import com.mygdx.game.Entities.NonStatics.Characters.Foe;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Entities.NonStatics.Creatures.Goblin;
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
	private Foe foe;
	private Goblin goblin;
	private Assets assets;
	private ControlsAndGUIsHandler controlsAndGUIsHandler;
	private CameraHandler cameraHandler;
	private ItemsContainer itemsContainer;
	private Updater updater;
	private ZoneRenderer zoneRenderer;
	private ZoneContainer zoneContainer;
	private List<Entity> entities;
	private ItemsManager itemsManager;

	private Debugger debugger;

	private boolean isDebug = true;

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
		player = new Player(assets);
		foe = new Foe(assets);
		goblin = new Goblin(assets);
		entities = new ArrayList<>();
        entities.add(player);
        entities.add(foe);
        entities.add(goblin);
    }

	private void loadData()  {
		batch = new SpriteBatch();
		font = new BitmapFont();
		itemsContainer = new ItemsContainer();
		itemsManager = new ItemsManager(assets, itemsContainer);
		itemsManager.loadDefinitions();
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
		World.init(entities,zoneRenderer,zoneContainer,batch,font,assets,itemsManager, cameraHandler,player);
		entities.forEach(e -> {
			if(e instanceof Character) ((Character) e).initializeInventory();
		});
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		zoneRenderer.renderZone();
		itemsContainer.draw(batch);
		entities.forEach(e->e.draw(batch,font));
		controlsAndGUIsHandler.draw(batch);
		batch.end();
	}

	private void update() {
		debugger.update();

		cameraHandler.update();
		controlsAndGUIsHandler.update();
        updater.update(entities);
        itemsContainer.update();
    }


	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
	}
}