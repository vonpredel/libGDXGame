package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Control.PlayerController;
import com.mygdx.game.Entities.Characters.Foe;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Utils.Assets;
import com.mygdx.game.Utils.CameraController;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Player player;
	private Foe foe;
	private Assets assets;
	private PlayerController playerController;
	private CameraController cameraController;

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
		batch = new SpriteBatch();
		player = new Player(assets);
		foe = new Foe(assets);
	}

	private void init() {
		playerController = new PlayerController(player);
		cameraController = new CameraController(batch,player);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		foe.draw(batch);
		batch.end();
	}

	private void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.O)) {
			cameraController.focusOn(foe);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.P)) {
			cameraController.focusOn(player);
		}
		cameraController.update();
		playerController.update();
	}


	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
	}
}
