package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;

public class Timer {

    private float latency;

    public Timer(float latency) {
        this.latency = latency;
    }

    public void timerHelper() {
        float timer = 0;
        timer += Gdx.graphics.getDeltaTime();
        if (timer > latency) {
            timer = 0;
        }
    }

}
