package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Control.PlayerController;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Utils.Assets;

public class Game extends ApplicationAdapter {

	SpriteBatch batch;
	Player player;
	Assets assets;
	PlayerController playerController;
	private OrthographicCamera camera;
	
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

	private void loadData() {

	}

	private void init() {
		camera = new OrthographicCamera(1280,720);
		batch = new SpriteBatch();
		player = new Player(assets);
		playerController = new PlayerController(player);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.end();
	}

	private void update() {
		handleCamera();
		playerController.control();

	}

	private void handleCamera() {
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		camera.position.set(player.x + player.width / 2, player.y + player.height / 2, 0);
	}

	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
	}
}
