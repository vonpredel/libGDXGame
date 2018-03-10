package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    private Class<? extends Disposable> determineClassFromExtension(final String fileName) {
        final String extension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
        switch (extension) {
            case ".jpg":
            case ".png":
            case ".bmp": {
                return Texture.class;
            }
            case ".mp3": {
                return Music.class;
            }
            case ".ogg": {
                return Sound.class;
            }
        }
        return null;
    }

    private void processFile(final File f) {
        if (f.isDirectory()) {
            final File[] files = f.listFiles();
            if (files != null) {
                for (final File file : files) {
                    this.processFile(file);
                }
            }
        } else {
            final String fileName = f.getName();
            manager.load(f.getParentFile().getName() + '/' + fileName,
                    this.determineClassFromExtension(fileName));
        }
    }

    public void load() {
        this.processFile(new File(Gdx.files.getLocalStoragePath()));
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

}
