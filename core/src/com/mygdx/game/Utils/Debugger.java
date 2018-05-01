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
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
                World.getZoneContainer().getZoneList().forEach(zone -> zone.setVisited(true));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
                World.getPlayer().setPointsToSpend(100);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
                PathFinding.test(World.getPlayer());
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
