package com.mygdx.game.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.OffHand;
import com.mygdx.game.Items.types.QuestItem;
import com.mygdx.game.Items.types.UsableItem;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.InventoryAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventoryGUI extends AbstractGUI {

    private static final int WEAPON_CLASS_ENUM = 0;
    private static final int ARMOR_CLASS_ENUM = 1;
    private static final int USABLE_CLASS_ENUM = 2;
    private static final int QUEST_CLASS_ENUM = 3;
    private static final int MISCELLANEOUS_CLASS_ENUM = 4;

    private static final int windowSize = 56;
    private static final int WindowGap = 68;

    public boolean inside;

    private List<? extends Item> list;
    private BitmapFont font;
    private Inventory inventory;

    private InventoryAction inventoryAction;

    private Item describedItem;

    private Texture weaponTab;
    private Texture armorTab;
    private Texture usableTab;
    private Texture questTab;
    private Texture miscTab;
    private Texture selectedTab;
    private Texture selectedItem;
    private Texture dropButton;
    private Texture useButton;

    private int selectedMenuIndex;
    private int selectedItemIndex;

    private int itemIndexX;
    private int itemIndexY;

    private int insideIndex;

    private Texture goldInfoTexture;
    private Texture armorTexture;
    private Texture weaponTexture;
    private Texture shieldTexture;
    private Texture helmetTexture;

    private Texture glovesTexture;
    private Texture bootsTexture;

    public InventoryGUI(Player player, Assets assets) {
        super(player, assets);
        this.texture = assets.manager.get(AssetsConstants.INVENTORY_V2);
        this.width = texture.getWidth()*4;
        this.height = texture.getHeight()*4;
        this.inventoryAction = new InventoryAction(player);
        font = new BitmapFont();
        list = new ArrayList<>();
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
        goldInfoTexture = assets.manager.get(AssetsConstants.GOLD_INFO);
    }

    @Override
    public void draw(SpriteBatch batch) {
        // TEMP FPS CHECK
        final int framesPerSecond = Gdx.graphics.getFramesPerSecond();
        font.setColor(Color.RED);
        font.draw(batch, "FPS : " + framesPerSecond, x+1130, y+870);
        font.setColor(Color.WHITE);
        // TEST
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

            //DRAW EQUIPPED ITEMS
            drawEquipedItems(batch);

            //DRAW DESCRIPTION
            drawDescription(batch);

            //DRAW GOLD INFO
            drawGoldInfo(batch);
        }
    }

    private void drawGoldInfo(SpriteBatch batch) {
        batch.draw(goldInfoTexture,x+410,y+85);
        font.draw(batch, String.valueOf(player.getGold()), x + 480, y + 125);
    }

    private void drawDescription(SpriteBatch batch) {

        if(selectedItemIndex<list.size()) {
            describedItem = list.get(selectedItemIndex);
            for (int i = 0; i < describedItem.getDescription().size(); i++) {
                font.draw(batch,describedItem.getDescription().get(i),x+45,y+280 -(i*20));
            }
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
            case USABLE_CLASS_ENUM: list = inventory.getUsableItems(); break;
            case QUEST_CLASS_ENUM: list = inventory.getQuestItems(); break;
            case MISCELLANEOUS_CLASS_ENUM: list = inventory.getMiscellaneousItems(); break;
            case ARMOR_CLASS_ENUM: list = Stream.of(inventory.getArmors(), inventory.getOffHands())
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList()); break;
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

    private void drawEquipedItems(SpriteBatch batch) {
        batch.draw(weaponTexture,x+40+(WindowGap*0),y+36,windowSize,windowSize);
        batch.draw(shieldTexture,x+40+(WindowGap*1),y+36,windowSize,windowSize);
        batch.draw(armorTexture,x+40+(WindowGap*2),y+36,windowSize,windowSize);
        batch.draw(helmetTexture,x+40+(WindowGap*3),y+36,windowSize,windowSize);
        batch.draw(bootsTexture,x+40+(WindowGap*5),y+36,windowSize,windowSize);
        batch.draw(glovesTexture,x+40+(WindowGap*4),y+36,windowSize,windowSize);
    }

    private <T extends Item> Texture chooseItemTexture(final T item) {
        return item == null ? selectedItem : item.getTexture();
    }

    @Override
    public void update() {
        super.update();
        itemIndexX = selectedItemIndex % 5;
        itemIndexY = selectedItemIndex / 5;

        weaponTexture = this.chooseItemTexture(player.getInventory().getEquipedWeapon());
        shieldTexture = this.chooseItemTexture(player.getInventory().getEquipedOffHand());
        armorTexture = this.chooseItemTexture(player.getInventory().getEquipedArmor());
        helmetTexture = this.chooseItemTexture(player.getInventory().getEquipedHelmet());
        glovesTexture = this.chooseItemTexture(player.getInventory().getEquipedGloves());
        bootsTexture = this.chooseItemTexture(player.getInventory().getEquipedBoots());

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
            if (selectedItemIndex < 5) return;
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
            if(!(selectedItemIndex<list.size())) return;
            inside = true;
        } else {
            inventory = player.getInventory();
            final Item item = list.get(selectedItemIndex);
            if(insideIndex==1) {
                player.dropItem(item);
            }
            else {
                if(selectedMenuIndex == WEAPON_CLASS_ENUM) inventory.equipWeapon((Weapon) item);
                else if (selectedMenuIndex == USABLE_CLASS_ENUM) inventoryAction.useItem((UsableItem) item);
                else if (selectedMenuIndex == QUEST_CLASS_ENUM) inventoryAction.useQuestItem((QuestItem) item);
                else if (selectedMenuIndex == MISCELLANEOUS_CLASS_ENUM) ;
                else if (selectedMenuIndex == ARMOR_CLASS_ENUM) {
                    if (item instanceof Armor) inventory.equipArmor((Armor) item);
                    else if (item instanceof OffHand) inventory.equipOffHand((OffHand) item);
                }

            }
            inside = false;
        }
    }

    public List<? extends Item> getList() {
        return list;
    }

    public void escape() {
        if(inside) inside = false;
    }
}
