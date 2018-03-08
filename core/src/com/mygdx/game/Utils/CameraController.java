package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Character;
import com.mygdx.game.Entities.Entity;

public class CameraController {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Entity entity;

    public CameraController(SpriteBatch batch, Character initialEntity) {
        this.camera = new OrthographicCamera(Constants.DEFAULT_RESOLUTION_WIDTH,Constants.DEFAULT_RESOLUTION_HEIGHT);
        this.batch = batch;
        this.entity = initialEntity;
    }

    public void update() {
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        camera.position.set(entity.x + entity.width / 2, entity.y + entity.height / 2, 0);
    }

    public void focusOn(Entity entity) {
        this.entity = entity;
    }
}
