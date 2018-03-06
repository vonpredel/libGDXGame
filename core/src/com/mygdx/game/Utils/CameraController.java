package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Character;

public class CameraController {

    OrthographicCamera camera;
    SpriteBatch batch;
    Character character;

    public CameraController(SpriteBatch batch, Character InitialCharacter) {
        this.camera = new OrthographicCamera(Constants.DEFAULT_RESOLUTION_WIDTH,Constants.DEFAULT_RESOLUTION_HEIGHT);
        this.batch = batch;
        this.character = InitialCharacter;
    }

    public void update() {
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        camera.position.set(character.x + character.width / 2, character.y + character.height / 2, 0);
    }

    public void focusOn(Character character) {
        this.character = character;
    }
}
