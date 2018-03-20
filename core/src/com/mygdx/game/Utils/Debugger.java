package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.World.World;

public class Debugger {

    private boolean isDebug = false;

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void update() {
        if(isDebug) {
            if(Gdx.input.isKeyPressed(Input.Keys.O)) {
                World.getCameraHandler().focusOn(World.getEntitiesContainer().getAllItems().get(1));
            }
            if(Gdx.input.isKeyPressed(Input.Keys.P)) {
                World.getCameraHandler().focusOn(World.getPlayer());
            }
            if(Gdx.input.isKeyPressed(Input.Keys.K)) {
                World.getCameraHandler().zoomOut();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.L)) {
                World.getCameraHandler().zoomIn();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.N)) {
                World.getCameraHandler().rotateCameraLeft();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.M)) {
                World.getCameraHandler().rotateCameraRight();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(0));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(1));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(2));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(3));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(4));
            }
            //TEMP TEMP TEMP
            if(Gdx.input.isKeyJustPressed(Input.Keys.V)) {
                if(!World.getPlayer().getInventory().getItems().isEmpty()) {
                    World.getPlayer().dropItem(World.getPlayer().getInventory().getItems().get(0));
                }
            }
        }
    }

}
