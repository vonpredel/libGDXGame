package com.mygdx.game.Utils;

import com.badlogic.gdx.utils.Disposable;

public class TextFile implements Disposable {

    private final String content;

    public TextFile(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void dispose() {
        // Do nothing.
    }
}
