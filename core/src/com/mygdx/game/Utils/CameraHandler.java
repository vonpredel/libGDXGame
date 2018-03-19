package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.NonStatics.NonStatic;

public class CameraHandler {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Entity entity;

    public CameraHandler(SpriteBatch batch, NonStatic initialEntity) {
        this.camera = new OrthographicCamera(Constants.DEFAULT_RESOLUTION_WIDTH,Constants.DEFAULT_RESOLUTION_HEIGHT);
        this.batch = batch;
        this.entity = initialEntity;
    }

    public void update() {
        camera.position.set(entity.x + entity.width / 2, entity.y + entity.height / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void zoomOut() {
        camera.zoom += 1 * Gdx.graphics.getDeltaTime();
    }

    public void zoomOut(int i) {
        camera.zoom += i * Gdx.graphics.getDeltaTime();
    }

    public void zoomIn() {
        camera.zoom -= 1 * Gdx.graphics.getDeltaTime();
    }

    public void zoomIn(int i) {
        camera.zoom -= i * Gdx.graphics.getDeltaTime();
    }

    public void rotateCameraRight() {
        camera.rotate(-0.5f);
    }

    public void rotateCameraLeft() {
        camera.rotate(0.5f);
    }

    public void resetZoom() {
        camera.zoom = 1;
    }

    public void focusOn(Entity entity) {
        this.entity = entity;
    }
}
