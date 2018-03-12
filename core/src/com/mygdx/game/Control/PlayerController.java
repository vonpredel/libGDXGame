package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Graphics.AbstractGUI;
import com.mygdx.game.Graphics.CharacterPanelGUI;
import com.mygdx.game.Graphics.InventoryGUI;
import com.mygdx.game.Graphics.MenuGUI;
import com.mygdx.game.Graphics.QuickInfoGUI;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlayerController {

    private static final int DEFAULT_STATE = 1;
    private static final int INVENTORY_STATE = 2;
    private static final int MENU_STATE = 3;
    private static final int CHARACTER_PANEL_STATE = 4;

    Player player;
    private int state;
    private CharacterPanelGUI characterPanelGUI;
    private InventoryGUI inventoryGUI;
    private QuickInfoGUI quickInfoGUI;
    private MenuGUI menuGUI;
    private List<AbstractGUI> guiList;

    public PlayerController(Player player) {
        this.player = player;
        this.characterPanelGUI = new CharacterPanelGUI(player);
        this.inventoryGUI = new InventoryGUI(player);
        this.menuGUI = new MenuGUI(player);
        this.quickInfoGUI = new QuickInfoGUI(player);
        quickInfoGUI.isEnabled = true;
        guiList = new ArrayList<>();
        guiList.add(characterPanelGUI);
        guiList.add(inventoryGUI);
        guiList.add(menuGUI);
        guiList.add(quickInfoGUI);
        this.state = 1;
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
            default:
                defaultControls();
        }
        guiList.forEach(AbstractGUI::update);
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        guiList.forEach(g -> g.draw(batch,font));
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.I) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            inventoryGUI.isEnabled = false;
            state = DEFAULT_STATE;
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
