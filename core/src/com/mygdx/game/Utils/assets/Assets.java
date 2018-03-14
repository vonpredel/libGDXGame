package com.mygdx.game.Utils.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Utils.textFile.TextFile;
import com.mygdx.game.Utils.textFile.TextFileAssetLoader;
import java.io.File;

public class Assets implements Disposable {

    public final AssetManager manager = new AssetManager();

    public Assets() {
        this.manager.setLoader(TextFile.class, new TextFileAssetLoader(new InternalFileHandleResolver()));
    }

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
            case ".csv": {
                return TextFile.class;
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
