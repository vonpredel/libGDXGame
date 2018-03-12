package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Control.PlayerController;
import com.mygdx.game.Entities.Characters.Foe;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Graphics.AbstractGUI;
import com.mygdx.game.Graphics.InventoryGUI;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.AssetsConstants;
import com.mygdx.game.Utils.CameraHandler;
import com.mygdx.game.Utils.ItemsContainer;
import com.mygdx.game.Utils.Timer;
import com.mygdx.game.World.World;
import com.mygdx.game.World.ZoneGenerator;
import com.mygdx.game.World.ZoneRenderer;
import com.mygdx.game.World.Zones.Zone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private BitmapFont font;
	private Player player;
	private Foe foe;
	private Assets assets;
	private PlayerController playerController;
	private CameraHandler cameraHandler;
	private ItemsContainer itemsContainer;
	private Timer timer;
	private ZoneGenerator zoneGenerator;
	private ZoneRenderer zoneRenderer;
	private List<Entity> entities;

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
		playerController = new PlayerController(player);
		cameraHandler = new CameraHandler(batch,player);
		itemsContainer = new ItemsContainer(entities);
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
		World.init(entities,zoneGenerator.getTileList(),zoneGenerator.getWidth(),zoneGenerator.getHeight(),batch,font,assets,itemsContainer);
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
		playerController.draw(batch,font);
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
			if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
				System.out.println("/");
				System.out.println("/");
				System.out.println("/");
				System.out.println("/");
				player.getInventory().getItems().forEach(i -> System.out.println(i.getName()));
			}
			//TEMP TEMP TEMP
			if(Gdx.input.isKeyJustPressed(Input.Keys.V)) {
				if(!player.getInventory().getItems().isEmpty()) {
					player.dropItem(player.getInventory().getItems().get(0));
				}
			}
		}
        playerController.update();
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