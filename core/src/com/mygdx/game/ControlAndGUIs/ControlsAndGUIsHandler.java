package com.mygdx.game.ControlAndGUIs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Graphics.AbstractGUI;
import com.mygdx.game.Graphics.CharacterPanelGUI;
import com.mygdx.game.Graphics.InventoryGUI;
import com.mygdx.game.Graphics.MapGUI;
import com.mygdx.game.Graphics.MenuGUI;
import com.mygdx.game.Graphics.QuickInfoGUI;
import com.mygdx.game.Graphics.SkillGUI;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.CHARACTER_PANEL_STATE;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.DEFAULT_STATE;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.INVENTORY_STATE_V2;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.MAP_STATE;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.MENU_STATE;
import static com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler.GUIState.SKILL_STATE;

public class ControlsAndGUIsHandler {

    Player player;
    public GUIState state;
    public CharacterPanelGUI characterPanelGUI;
    public QuickInfoGUI quickInfoGUI;
    public MenuGUI menuGUI;
    public MapGUI mapGUI;
    public InventoryGUI inventoryGUI;
    public SkillGUI skillGUI;

    private Assets assets;
    private List<AbstractGUI> guiList;

    public ControlsAndGUIsHandler(Assets assets) {
        this.assets = assets;
        this.state = DEFAULT_STATE;
    }

    public void initGUIs(Player player) {
        this.player = player;
        this.characterPanelGUI = new CharacterPanelGUI(player,assets);
        this.menuGUI = new MenuGUI(player,assets);
        this.quickInfoGUI = new QuickInfoGUI(player,assets);
        this.inventoryGUI = new InventoryGUI(player,assets);
        this.mapGUI = new MapGUI(player,assets);
        this.skillGUI = new SkillGUI(player,assets);
        quickInfoGUI.isEnabled = true;
        guiList = new ArrayList<>();
        guiList.add(characterPanelGUI);
        guiList.add(menuGUI);
        guiList.add(quickInfoGUI);
        guiList.add(inventoryGUI);
        guiList.add(mapGUI);
        guiList.add(skillGUI);
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
            case MAP_STATE:
                mapControls();
                break;
            case SKILL_STATE:
                skillControls();
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
            player.performAttack(World.UP);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.performAttack(World.DOWN);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.performAttack(World.LEFT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.performAttack(World.RIGHT);
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            skillGUI.isEnabled = true;
            state = SKILL_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            final Skill bindedSkill = player.getSpellBook().getBindedSkill(0);
            if (bindedSkill!=null) {
                bindedSkill.use();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            final Skill bindedSkill = player.getSpellBook().getBindedSkill(1);
            if (bindedSkill!=null) {
                bindedSkill.use();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            final Skill bindedSkill = player.getSpellBook().getBindedSkill(2);
            if (bindedSkill!=null) {
                bindedSkill.use();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            player.ActionOnStatic();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
//            player.ActionOnNpc();
        }

    }

    private void inventoryControlsV2() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            inventoryGUI.inside = false;
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)
                || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            characterPanelGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            characterPanelGUI.listDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            characterPanelGUI.listUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            characterPanelGUI.addStat();
        }

    }

    private void menuControls() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            menuGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
    }

    private void mapControls() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
                || Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            mapGUI.isEnabled = false;
            inventoryGUI.isEnabled = true;
            state = INVENTORY_STATE_V2;
        }
    }

    private void skillControls() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (skillGUI.inside) {
                skillGUI.inside = false;
            } else {
                skillGUI.isEnabled = false;
                state = DEFAULT_STATE;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            skillGUI.inside = false;
            skillGUI.isEnabled = false;
            state = DEFAULT_STATE;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            skillGUI.moveUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            skillGUI.moveDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            skillGUI.moveLeft();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            skillGUI.moveRight();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            skillGUI.enter();
        }

    }

    public void setMapState() {
        this.state = MAP_STATE;
    }

    enum GUIState {
        DEFAULT_STATE,
        MENU_STATE,
        CHARACTER_PANEL_STATE,
        INVENTORY_STATE_V2,
        MAP_STATE,
        SKILL_STATE,
        // TODO STATES
        TRADE_STATE,
        DIALOGUE_STATE,
        QUEST_STATE;

    }
}
