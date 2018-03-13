package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Items.EquiableItems.Armors.Armor;
import com.mygdx.game.Items.EquiableItems.Weapons.Weapon;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Utils.AssetsConstants;
import com.mygdx.game.inventory.Inventory;
import java.util.ArrayList;
import java.util.List;

public class InventoryGUI extends AbstractGUI {

    private static final int space = 50;

    private static final int WEAPON_CLASS_ENUM = 0;
    private static final int ARMOR_CLASS_ENUM = 1;
    private static final int USABLE_CLASS_ENUM = 2;
    private static final int QUEST_CLASS_ENUM = 3;
    private static final int MISCELLANEOUS_CLASS_ENUM = 4;

    private static final String[] categoryList = {"WEAPONS","ARMORS","USABLE ITEMS","QUEST ITEMS","MISCELLANEOUS"};

    public boolean inside = false;

    private int selectedMenu;
    private int selectedItem;
    private int menuSize = categoryList.length-1;
    private int itemsSize;

    private Inventory inventory;
    private List<? extends Item> list;

    public InventoryGUI(Player player) {
        this.texture = new Texture(AssetsConstants.INVENTORY);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
        inventory = player.getInventory();
        list = new ArrayList<>();
    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        super.draw(batch, font);
        if(isEnabled) {
            if(!inside) {
                int offSet = space * selectedMenu;
                drawSelectors(batch,font,offSet);
                for (int i = 0; i < categoryList.length; i++) {
                    font.draw(batch, categoryList[i], player.x, player.y - space * i);
                }
            } else {
                switch (selectedMenu) {
                    case WEAPON_CLASS_ENUM: list = inventory.getWeapons(); break;
                    case ARMOR_CLASS_ENUM: list = inventory.getArmors(); break;
                    case USABLE_CLASS_ENUM: list = inventory.getUsableItems(); break;
                    case QUEST_CLASS_ENUM: list = inventory.getQuestItems(); break;
                    case MISCELLANEOUS_CLASS_ENUM: list = inventory.getMiscellaneousItems(); break;
                }
                itemsSize = list.size()-1;
                int offSet = space * selectedItem;
                drawSelectors(batch,font,offSet);
                for (int i = 0; i < list.size(); i++) {
                    font.draw(batch, list.get(i).getName(), player.x, player.y - space * i);
                }
            }

        }

    }

    private void drawSelectors(SpriteBatch batch, BitmapFont font, int offSet) {
        font.draw(batch,"<",player.x + space*3,player.y - offSet);
        font.draw(batch,"> ",player.x - space,player.y - offSet);
    }

    public void listDown() {
        if(inside) {
            if (selectedItem == itemsSize) return;
            selectedItem++;
        }
        else {
            if (selectedMenu == menuSize) return;
            selectedMenu++;
        }
    }

    public void listUp() {
        if(inside) {
            if (selectedItem==0) return;
            selectedItem--;
        }
        else {
            if (selectedMenu==0) return;
            selectedMenu--;
        }
    }

    public void enterMenu() {
        if (!inside) inside = true;
        else {
            if(selectedMenu == 0) {
                inventory.equipWeapon((Weapon) list.get(selectedItem));
            } else if (selectedMenu == 1) {
//                inventory.equipArmor((Armor) list.get(selectedItem));
            }
        }
    }

    public void returnMenu() {
        if(inside) inside = false;
    }
}
