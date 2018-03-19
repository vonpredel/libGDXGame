package com.mygdx.game.ControlAndGUIs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Graphics.AbstractGUI;
import com.mygdx.game.Graphics.CharacterPanelGUI;
import com.mygdx.game.Graphics.InventoryGUI;
import com.mygdx.game.Graphics.MenuGUI;
import com.mygdx.game.Graphics.QuickInfoGUI;
import com.mygdx.game.Utils.assets.Assets;
import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.CHARACTER_PANEL_STATE;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.DEFAULT_STATE;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.INVENTORY_STATE_V2;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.MENU_STATE;

public class ControlsAndGUIsHandler {

    Player player;
    private GUIState state;
    private CharacterPanelGUI characterPanelGUI;
    private QuickInfoGUI quickInfoGUI;
    private MenuGUI menuGUI;

    private InventoryGUI inventoryGUI;

    private Assets assets;
    private List<AbstractGUI> guiList;

    public ControlsAndGUIsHandler(Player player,Assets assets) {
        this.assets = assets;
        this.player = player;
        initGUIs();
        this.state = DEFAULT_STATE;
    }

    private void initGUIs() {
        this.characterPanelGUI = new CharacterPanelGUI(player,assets);
        this.menuGUI = new MenuGUI(player,assets);
        this.quickInfoGUI = new QuickInfoGUI(player,assets);
        this.inventoryGUI = new InventoryGUI(player,assets);
        quickInfoGUI.isEnabled = true;
        guiList = new ArrayList<>();
        guiList.add(characterPanelGUI);
        guiList.add(menuGUI);
        guiList.add(quickInfoGUI);
        guiList.add(inventoryGUI);
    }

    public void update() {
        movementControls();
        switch (state) {
            case DEFAULT_STATE:
                defaultControls();
                break;
            case MENU_STATE:
                menuControls();
                break;
            case CHARACTER_PANEL_STATE:
                characterPanelControls();
                break;
            case INVENTORY_STATE_V2:
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
        // TEST
        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            inventoryGUI.isEnabled = true;
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

    private void inventoryControlsV2() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            inventoryGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            inventoryGUI.listUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            inventoryGUI.listDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            inventoryGUI.listLeft();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            inventoryGUI.listRight();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            inventoryGUI.changeTab();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            inventoryGUI.enterMenu();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            inventoryGUI.escape();
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

    enum GUIState {
        DEFAULT_STATE,
        MENU_STATE,
        CHARACTER_PANEL_STATE,
        INVENTORY_STATE_V2;
    }
}
