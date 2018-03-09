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
        manager.load("grass/grass1.png", Texture.class);
        manager.load("grass/grass2.png", Texture.class);
        manager.load("grass/grass3.png", Texture.class);
        manager.load("grass/grassD.png", Texture.class);
        manager.load("grass/grassDL.png", Texture.class);
        manager.load("grass/grassDR.png", Texture.class);
        manager.load("grass/grassL.png", Texture.class);
        manager.load("grass/grassR.png", Texture.class);
        manager.load("grass/grassU.png", Texture.class);
        manager.load("grass/grassUL.png", Texture.class);
        manager.load("grass/grassUR.png", Texture.class);
        manager.load("grass/grassURm.png", Texture.class);
        manager.load("grass/grassULm.png", Texture.class);
        manager.load("grass/grassDRm.png", Texture.class);
        manager.load("grass/grassDLm.png", Texture.class);
        manager.load("dirt/dirt.png", Texture.class);
        manager.load("dirt/dirtD.png", Texture.class);
        manager.load("dirt/dirtDL.png", Texture.class);
        manager.load("dirt/dirtDR.png", Texture.class);
        manager.load("dirt/dirtL.png", Texture.class);
        manager.load("dirt/dirtR.png", Texture.class);
        manager.load("dirt/dirtU.png", Texture.class);
        manager.load("dirt/dirtUL.png", Texture.class);
        manager.load("dirt/dirtUR.png", Texture.class);
        manager.load("dirt/dirtURm.png", Texture.class);
        manager.load("dirt/dirtULm.png", Texture.class);
        manager.load("dirt/dirtDRm.png", Texture.class);
        manager.load("dirt/dirtDLm.png", Texture.class);
        manager.load("water/water.png", Texture.class);
        manager.load("water/waterD.png", Texture.class);
        manager.load("water/waterDL.png", Texture.class);
        manager.load("water/waterDR.png", Texture.class);
        manager.load("water/waterL.png", Texture.class);
        manager.load("water/waterR.png", Texture.class);
        manager.load("water/waterU.png", Texture.class);
        manager.load("water/waterUL.png", Texture.class);
        manager.load("water/waterUR.png", Texture.class);
        manager.load("water/waterURm.png", Texture.class);
        manager.load("water/waterULm.png", Texture.class);
        manager.load("water/waterDRm.png", Texture.class);
        manager.load("water/waterDLm.png", Texture.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
