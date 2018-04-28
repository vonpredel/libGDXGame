package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.World.World;

public final class Debugger {

    private static boolean isDebug = false;

    private Debugger() {

    }

    public static void update() {
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
                World.getPlayer().setPointsToSpend(100);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                PathFinding.test(World.getPlayer());
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(20));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(30));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(40));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(1));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(11));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(21));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(31));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                World.setCurrentZone(World.getZoneContainer().getZoneList().get(41));
            }

            //TEMP TEMP TEMP
            if(Gdx.input.isKeyJustPressed(Input.Keys.V)) {
                if(!World.getPlayer().getInventory().getItems().isEmpty()) {
                    World.getPlayer().dropItem(World.getPlayer().getInventory().getItems().get(0));
                }
            }
        }
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

}
