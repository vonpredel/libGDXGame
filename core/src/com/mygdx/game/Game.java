package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Utils.Assets;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player;
	Assets assets;
	
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
		img = new Texture("badlogic.jpg");
	}

	private void init() {
		batch = new SpriteBatch();
		player = new Player(assets);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.draw(img, 500, 500);
		batch.end();
	}
	
	@Override
	public void dispose () {
		assets.dispose();
		batch.dispose();
		img.dispose();
	}
}
