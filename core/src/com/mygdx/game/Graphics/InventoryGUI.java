package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
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

    private BitmapFont font;

    private int selectedMenu;
    private int selectedItem;
    private int menuSize = categoryList.length-1;
    private int itemsSize;

    private Inventory inventory;

    private List<? extends Item> list;

    public InventoryGUI(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.INVENTORY);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
        font = new BitmapFont();
        list = new ArrayList<>();
        this.assets = assets;
    }

    @Override
    public void draw(SpriteBatch batch) {
        inventory = player.getInventory();
        super.draw(batch);
        if(isEnabled) {
            String weaponName = inventory.getEquipedWeapon() == null ? "No Weapon" : inventory.getEquipedWeapon().getName();
            String armorName = inventory.getEquipedArmor() == null ? "No Weapon" : inventory.getEquipedArmor().getName();
            font.draw(batch,"CURRENT WEAPON : " + weaponName,player.x-250,player.y -50);
            font.draw(batch,"CURRENT ARMOR : " + armorName,player.x-250,player.y - 100);
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
                font.draw(batch,categoryList[selectedMenu],player.x - 50,player.y + 50);
                for (int i = 0; i < list.size(); i++) {
                    font.draw(batch, list.get(i).getName(), player.x, player.y - space * i);
                }
            }
        }
    }

    private void drawSelectors(SpriteBatch batch, BitmapFont font, int offSet) {
        font.draw(batch,"<",player.x + space*3,player.y - offSet);
        font.draw(batch,">",player.x - space,player.y - offSet);
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
        if (!inside) {
            inside = true;
            selectedItem = 0;
        }
        else {
            inventory = player.getInventory();
            if(selectedMenu == WEAPON_CLASS_ENUM) {
                if(!list.isEmpty()) inventory.equipWeapon((Weapon) list.get(selectedItem));
            } else if (selectedMenu == ARMOR_CLASS_ENUM) {
                if(!list.isEmpty()) inventory.equipArmor((Armor) list.get(selectedItem));
            } else if (selectedMenu == USABLE_CLASS_ENUM) {
                if(!list.isEmpty()) inventory.useItem((UsableItem) list.get(selectedItem),player);
            } else if (selectedMenu == QUEST_CLASS_ENUM) {
//                if(!list.isEmpty()) inventory.equipArmor((Armor) list.get(selectedItem));
            } else if (selectedMenu == MISCELLANEOUS_CLASS_ENUM) {
//                if(!list.isEmpty()) inventory.equipArmor((Armor) list.get(selectedItem));
            }
        }
    }

    public void returnMenu() {
        if(inside) inside = false;
    }
}
