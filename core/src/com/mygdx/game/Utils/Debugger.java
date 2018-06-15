package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Utils.assets.AssetChopper;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import com.mygdx.game.quests.QuestType;

public final class Debugger {

    private static boolean isDebug = false;

    private Debugger() {

    }

    public static void update() {
        if (!isDebug) return;
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            World.getCameraHandler().focusOn(World.getEntitiesContainer().getAllItems().get(1));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            World.getCameraHandler().focusOn(World.getPlayer());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            World.getCameraHandler().zoomOut();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            World.getCameraHandler().zoomIn();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.N)) {
            World.getCameraHandler().rotateCameraLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            World.getCameraHandler().rotateCameraRight();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            World.getZoneContainer().getZoneList().forEach(zone -> zone.setVisited(true));
        }

        // MAP

//        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
//            MapFormatConverter.loadRawFiles();
//            MapFormatConverter.convertAndSave();
//        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            AssetChopper.chopchop();
        }

        // CHOP CHOP

//        if(Gdx.input.isKeyJustPressed(Input.Keys.H)) {
//            final Texture texture1 = World.getAssets().manager.get(AssetsConstants.TO_CHOP, Texture.class);
//            final TextureRegion[] crop = AssetChopper.crop(texture1);
//            AssetChopper.saveImages(crop);
//        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            World.getPlayer().getQuestHandler().addQuest(World.getQuestsManager().create(QuestType.TEST_COLLECT_QUEST));
            World.getPlayer().getQuestHandler().addQuest(World.getQuestsManager().create(QuestType.TEST_KILL_QUEST));
            World.getPlayer().getQuestHandler().addQuest(World.getQuestsManager().create(QuestType.TEST_COLLECT_QUEST2));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
//                World.getControlsAndGUIsHandler().tradeGUI.updateTextureType(Merchant.MerchantType.BLACKSMITH);
//                World.getControlsAndGUIsHandler().setTradeState();
//                World.getControlsAndGUIsHandler().tradeGUI.isEnabled = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
            Player player = World.getPlayer();
            player.setPointsToSpend(1000);
            player.setSkillPoints(50);
            player.setStrength(200);
            player.setDexterity(200);
            player.setEnergy(200);
            player.setVitality(200);
            player.setMaxManaPoints(1000);
            player.setMaxHealthPoints(1000);
            player.setMaxStaminaPoints(1000);
            player.setExperience(10000000);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
            PathFinding.test(World.getPlayer());
        }

        //TEMP TEMP TEMP
        if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            if (!World.getPlayer().getInventory().getItems().isEmpty()) {
                World.getPlayer().dropItem(World.getPlayer().getInventory().getItems().get(0));
            }
        }
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

}
