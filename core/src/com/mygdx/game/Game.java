package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Entities.Characters.Foe;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.CameraHandler;
import com.mygdx.game.Items.ItemsContainer;
import com.mygdx.game.Utils.Timer;
import com.mygdx.game.World.World;
import com.mygdx.game.Zones.ZoneGenerator;
import com.mygdx.game.Zones.ZoneRenderer;
import com.mygdx.game.Zones.Zone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;
	private Foe foe;
	private Assets assets;
	private ControlsAndGUIsHandler controlsAndGUIsHandler;
	private CameraHandler cameraHandler;
	private ItemsContainer itemsContainer;
	private Timer timer;
	private ZoneGenerator zoneGenerator;
	private ZoneRenderer zoneRenderer;
	private List<Entity> entities;
	private ItemsManager itemsManager;

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
        entities = new ArrayList<>();
        entities.add(player);
        entities.add(foe);
    }

	private void loadData()  {
		batch = new SpriteBatch();
		font = new BitmapFont();
		itemsContainer = new ItemsContainer();
		itemsManager = new ItemsManager(assets, itemsContainer);
		itemsManager.loadDefinitions();
		controlsAndGUIsHandler = new ControlsAndGUIsHandler(player);
		cameraHandler = new CameraHandler(batch,player);
        timer = new Timer();
		zoneGenerator = new ZoneGenerator(assets);
		Zone zone = null;
		try {
			zone = zoneGenerator.generateZone(AssetsConstants.MAP1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		zoneRenderer = new ZoneRenderer(zone,batch);
    }

	private void worldInit() {
		World.init(entities,zoneGenerator.getTileList(),zoneGenerator.getWidth(),zoneGenerator.getHeight(),batch,font,assets,itemsContainer,itemsManager);
		entities.forEach(Entity::initializeInventory);
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
		controlsAndGUIsHandler.draw(batch,font);
		batch.end();
	}

	private void update() {
		if(isDebug) {
			if(Gdx.input.isKeyPressed(Input.Keys.O)) {
				cameraHandler.focusOn(foe);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.P)) {
				cameraHandler.focusOn(player);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.K)) {
				cameraHandler.zoomIn();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.L)) {
				cameraHandler.zoomOut();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.N)) {
				cameraHandler.rotateCameraLeft();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.M)) {
				cameraHandler.rotateCameraRight();
			}
			//TEMP TEMP TEMP
			if(Gdx.input.isKeyJustPressed(Input.Keys.V)) {
				if(!player.getInventory().getItems().isEmpty()) {
					player.dropItem(player.getInventory().getItems().get(0));
				}
			}
		}
        controlsAndGUIsHandler.update();
        cameraHandler.update();
        timer.update(entities);
        itemsContainer.update();
    }


	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
	}
}