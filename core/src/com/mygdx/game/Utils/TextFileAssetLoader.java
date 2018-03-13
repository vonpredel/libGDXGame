package com.mygdx.game.Utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class TextFileAssetLoader extends SynchronousAssetLoader<TextFile, TextFileAssetLoader.TextFileAssetLoaderParams> {

    public TextFileAssetLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public TextFile load(AssetManager assetManager, String fileName, FileHandle file, TextFileAssetLoaderParams parameter) {
        try {
            final String fileContent = Files
                    .lines(file.file().toPath(), StandardCharsets.UTF_8)
                    .collect(Collectors.joining("\n"));
            return new TextFile(fileContent);
        } catch (IOException e) {
            throw new GdxRuntimeException(e);
        }
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextFileAssetLoaderParams parameter) {
        return null;
    }

    public static class TextFileAssetLoaderParams extends AssetLoaderParameters<TextFile> {
        public TextFileAssetLoaderParams() {
        }
    }
}
