package com.mygdx.game.Utils.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class AssetChopper {

    private static final int SIZE = 64;

    private AssetChopper() {
    }

    public static TextureRegion[] crop(Texture texture) {
        TextureRegion textureRegion = new TextureRegion(texture);
        final TextureRegion[][] split = textureRegion.split(SIZE, SIZE);
        return Arrays.stream(split).flatMap(Arrays::stream).toArray(TextureRegion[]::new);
    }

}
