package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
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

public class InventoryGUIv2 extends AbstractGUI {

    private static final int WEAPON_CLASS_ENUM = 0;
    private static final int ARMOR_CLASS_ENUM = 1;
    private static final int USABLE_CLASS_ENUM = 2;
    private static final int QUEST_CLASS_ENUM = 3;
    private static final int MISCELLANEOUS_CLASS_ENUM = 4;

    private static final int windowSize = 56;
    private static final int WindowGap = 68;

    private boolean inside = false;

    private List<? extends Item> list;
    private BitmapFont font;
    private Inventory inventory;

    Texture weaponTab;
    Texture armorTab;
    Texture usableTab;
    Texture questTab;
    Texture miscTab;
    Texture selectedTab;
    Texture selectedItem;
    Texture dropButton;
    Texture useButton;

    private int selectedMenuIndex;
    private int selectedItemIndex;

    private int itemIndexX;
    private int itemIndexY;

    private int insideIndex;

    public InventoryGUIv2(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.INVENTORY_V2);
        this.width = texture.getWidth()*4;
        this.height = texture.getHeight()*4;
        this.player = player;
        font = new BitmapFont();
        list = new ArrayList<>();
        this.assets = assets;
        initAssets();
    }

    private void initAssets() {
        weaponTab = assets.manager.get(AssetsConstants.INVENTORY_WEAPON_TAB);
        armorTab = assets.manager.get(AssetsConstants.INVENTORY_ARMOR_TAB);
        usableTab = assets.manager.get(AssetsConstants.INVENTORY_USABLE_TAB);
        questTab = assets.manager.get(AssetsConstants.INVENTORY_QUEST_TAB);
        miscTab = assets.manager.get(AssetsConstants.INVENTORY_MISC_TAB);
        selectedTab = assets.manager.get(AssetsConstants.INVENTORY_SELECTED_TAB);
        selectedItem = assets.manager.get(AssetsConstants.INVENTORY_SELECTED_ITEM);
        dropButton = assets.manager.get(AssetsConstants.INVENTORY_DROP_BUTTON);
        useButton = assets.manager.get(AssetsConstants.INVENTORY_USE_BUTTON);
    }

    @Override
    public void draw(SpriteBatch batch) {
        inventory = player.getInventory();
        super.draw(batch);
        if(isEnabled) {
            //MENU 71PX
            //ITEM 68PX X-AXIS
            //ITEM 00PX Y-AXIS

            // WINDOW 56px
            //DRAW SELECTED TAB
            batch.draw(selectedTab,x+400,y+460-(selectedMenuIndex *71),selectedTab.getWidth()*4,selectedTab.getHeight()*4);

            //DRAW SELECTED ITEM
            batch.draw(selectedItem,x+40+(WindowGap *itemIndexX),y+460-(WindowGap*itemIndexY),windowSize,windowSize);

            //DRAW ITEMS
            drawItems(batch);

            //DRAW INSIDE MENU
            drawInsideMenu(batch);

            //DRAW CATEGORIES ICONS
            drawCategoriesMethods(batch);
        }
    }

    private void drawCategoriesMethods(SpriteBatch batch) {
        batch.draw(weaponTab,x+420,y+475,weaponTab.getWidth()*4,weaponTab.getHeight()*4);
        batch.draw(armorTab,x+420,y+405,armorTab.getWidth()*4,armorTab.getHeight()*4);
        batch.draw(usableTab,x+415,y+330,usableTab.getWidth()*4,usableTab.getHeight()*4);
        batch.draw(questTab,x+425,y+260,questTab.getWidth()*4,questTab.getHeight()*4);
        batch.draw(miscTab,x+415,y+185,miscTab.getWidth()*4,miscTab.getHeight()*4);
    }

    private void drawItems(SpriteBatch batch) {
        switch (selectedMenuIndex) {
            case WEAPON_CLASS_ENUM: list = inventory.getWeapons(); break;
            case ARMOR_CLASS_ENUM: list = inventory.getArmors(); break;
            case USABLE_CLASS_ENUM: list = inventory.getUsableItems(); break;
            case QUEST_CLASS_ENUM: list = inventory.getQuestItems(); break;
            case MISCELLANEOUS_CLASS_ENUM: list = inventory.getMiscellaneousItems(); break;
        }
        for (int i = 0; i < list.size(); i++) {
            int yAxisIndex = i / 5;
            int xAxisIndex = i > 9 ? i - 10 : i > 4 ? i - 5 : i;
            batch.draw(list.get(i).getTexture(),x+40+(WindowGap* xAxisIndex),y+460-(WindowGap* yAxisIndex),windowSize,windowSize);
        }
    }

    private void drawInsideMenu(SpriteBatch batch) {
        if (inside) {
            if (insideIndex==0) {
                batch.draw(useButton,x+288,y+232,useButton.getWidth()*4,useButton.getHeight()*4);
            }
            else {
                batch.draw(dropButton,x+288,y+188,dropButton.getWidth()*4,dropButton.getHeight()*4);
            }
        }
    }

    @Override
    public void update() {
        super.update();
        itemIndexX = selectedItemIndex % 5;
        itemIndexY = selectedItemIndex / 5;
    }

    public void changeTab() {
        if(!inside) {
            if(selectedMenuIndex==4) selectedMenuIndex = 0;
            else selectedMenuIndex++;
        }
    }

    public void listUp() {
        if (inside) {
            insideIndex = insideIndex!=1 ? 1 : 0;
        } else {
            if (selectedItemIndex < 4) return;
            else selectedItemIndex-=5;
        }
    }

    public void listDown() {
        if(inside) {
            insideIndex = insideIndex!=1 ? 1 : 0;
        } else {
            if (selectedItemIndex > 9) return;
            else selectedItemIndex+=5;
        }
    }

    public void listLeft() {
        if(!inside) {
            if (selectedItemIndex == 0) selectedItemIndex = 14;
            else selectedItemIndex--;
        }
    }

    public void listRight() {
        if(!inside) {
            if (selectedItemIndex == 14) selectedItemIndex = 0;
            else selectedItemIndex++;
        }
    }

    public void enterMenu() {
        if(!inside) {
            inside = true;
        } else {
            inventory = player.getInventory();
            if(insideIndex==1) {
                player.dropItem(list.get(selectedItemIndex));
            } else {
                if(selectedMenuIndex == WEAPON_CLASS_ENUM) {
                    if(!list.isEmpty()) inventory.equipWeapon((Weapon) list.get(selectedItemIndex));
                } else if (selectedMenuIndex == ARMOR_CLASS_ENUM) {
                    if(!list.isEmpty()) inventory.equipArmor((Armor) list.get(selectedItemIndex));
                } else if (selectedMenuIndex == USABLE_CLASS_ENUM) {
                    if(!list.isEmpty()) inventory.useItem((UsableItem) list.get(selectedItemIndex),player);
                } else if (selectedMenuIndex == QUEST_CLASS_ENUM) {
//                if(!list.isEmpty()) inventory.equipArmor((Armor) list.get(selectedItem));
                } else if (selectedMenuIndex == MISCELLANEOUS_CLASS_ENUM) {
//                if(!list.isEmpty()) inventory.equipArmor((Armor) list.get(selectedItem));
                }
            }
            inside = false;
        }
    }

    public void escape() {
        if(inside) inside = false;
    }
}
