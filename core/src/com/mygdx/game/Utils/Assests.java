package com.mygdx.game.Utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Assests implements Disposable {

    public final AssetManager manager = new AssetManager();

    public void load() {
        manager.load("badlogic.jpg",Texture.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
