package com.mygdx.game.Utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void load() {
        manager.load("badlogic.jpg", Texture.class);
        manager.load("trawa.png", Texture.class);
        manager.load("sciana.png", Texture.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
