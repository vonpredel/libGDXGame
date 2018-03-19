package com.mygdx.game.ControlAndGUIs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Graphics.AbstractGUI;
import com.mygdx.game.Graphics.CharacterPanelGUI;
import com.mygdx.game.Graphics.InventoryGUI;
import com.mygdx.game.Graphics.InventoryGUIv2;
import com.mygdx.game.Graphics.MenuGUI;
import com.mygdx.game.Graphics.QuickInfoGUI;
import com.mygdx.game.Utils.assets.Assets;
import java.util.ArrayList;
import java.util.List;

public class ControlsAndGUIsHandler {

    private static final int DEFAULT_STATE = 1;
    private static final int INVENTORY_STATE = 2;
    private static final int MENU_STATE = 3;
    private static final int CHARACTER_PANEL_STATE = 4;
    private static final int INVENTORY_STATE_V2 = 5;

    Player player;
    private int state;
    private CharacterPanelGUI characterPanelGUI;
    private InventoryGUI inventoryGUI;
    private QuickInfoGUI quickInfoGUI;
    private MenuGUI menuGUI;

    private InventoryGUIv2 inventoryGUIv2;

    private Assets assets;
    private List<AbstractGUI> guiList;

    public ControlsAndGUIsHandler(Player player,Assets assets) {
        this.assets = assets;
        this.player = player;
        initGUIs();
        this.state = 1;
    }

    private void initGUIs() {
        this.characterPanelGUI = new CharacterPanelGUI(player,assets);
        this.inventoryGUI = new InventoryGUI(player,assets);
        this.menuGUI = new MenuGUI(player,assets);
        this.quickInfoGUI = new QuickInfoGUI(player,assets);
        this.inventoryGUIv2 = new InventoryGUIv2(player,assets);
        quickInfoGUI.isEnabled = true;
        guiList = new ArrayList<>();
        guiList.add(characterPanelGUI);
        guiList.add(inventoryGUI);
        guiList.add(menuGUI);
        guiList.add(quickInfoGUI);
        guiList.add(inventoryGUIv2);
    }

    public void update() {
        movementControls();
        switch (state) {
            case 1:
                defaultControls();
                break;
            case 2:
                inventoryControls();
                break;
            case 3:
                menuControls();
                break;
            case 4:
                characterPanelControls();
                break;
            case 5:
                inventoryControlsV2();
                break;
            default:
                defaultControls();
        }
        guiList.forEach(AbstractGUI::update);
    }

    public void draw(SpriteBatch batch) {
        guiList.forEach(g -> g.draw(batch));
    }

    private void movementControls() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
    }

    private void defaultControls() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.attackUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.attackDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.attackLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.attackRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.pickUpItems();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventoryGUI.isEnabled = true;
            state = INVENTORY_STATE;
        }
        // TEST
        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            inventoryGUIv2.isEnabled = true;
            state = INVENTORY_STATE_V2;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            characterPanelGUI.isEnabled = true;
            state = CHARACTER_PANEL_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            menuGUI.isEnabled = true;
            state = MENU_STATE;
        }

    }

    private void inventoryControls() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventoryGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            inventoryGUI.listDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            inventoryGUI.listUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            inventoryGUI.enterMenu();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            inventoryGUI.returnMenu();
        }
    }

    private void inventoryControlsV2() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            inventoryGUIv2.isEnabled = false;
            state = DEFAULT_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            inventoryGUIv2.listUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            inventoryGUIv2.listDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            inventoryGUIv2.listLeft();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            inventoryGUIv2.listRight();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            inventoryGUIv2.changeTab();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            inventoryGUIv2.enterMenu();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            inventoryGUIv2.escape();
        }
    }

    private void characterPanelControls() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.C) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            characterPanelGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
    }

    private void menuControls() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            menuGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
