package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Control.CollisionDetection;
import com.mygdx.game.Control.PlayerController;
import com.mygdx.game.Entities.Characters.Foe;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.CameraController;
import com.mygdx.game.Utils.ZoneGenerator;
import com.mygdx.game.World.ZoneRenderer;
import com.mygdx.game.World.Zones.Zone;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Player player;
	private Foe foe;
	private Assets assets;
	private PlayerController playerController;
	private CameraController cameraController;
	private CollisionDetection collisionDetection;
	private ZoneGenerator zoneGenerator;
	private ZoneRenderer zoneRenderer;
	private List<Entity> entities;
	private List<Tile> tiles;

	@Override
	public void create () {
		assets = new Assets();
		assets.load();
		assets.manager.finishLoading();
		if(assets.manager.update()) {
			loadData();
			init();
		}
	}

	private void loadData()  {
		batch = new SpriteBatch();
		player = new Player(assets);
		playerController = new PlayerController(player,foe);
		collisionDetection = new CollisionDetection();
		cameraController = new CameraController(batch,player);
		foe = new Foe(assets);
        entities = new ArrayList<>();
        entities.add(player);
        entities.add(foe);
		zoneGenerator = new ZoneGenerator(assets);
		Zone zone = null;
		try {
			zone = zoneGenerator.generateZone("map1.bmp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		tiles = zoneGenerator.getTileList();
		zoneRenderer = new ZoneRenderer(zone,batch);
    }

	private void init() {

	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		zoneRenderer.renderZone();
		entities.forEach(e->e.draw(batch));
		batch.end();
	}

	private void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.O)) {
			cameraController.focusOn(foe);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.P)) {
			cameraController.focusOn(player);
		}
        playerController.update();
        collisionDetection.update(entities,tiles);
        cameraController.update();
	}


	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
	}
}
