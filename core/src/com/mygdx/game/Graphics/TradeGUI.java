package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Entities.Npc.Merchant;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.inventory.Inventory;
import java.util.ArrayList;
import java.util.List;

public class TradeGUI extends AbstractGUI {

    private static final int windowSize = 56;
    private static final int WindowGap = 68;
    private static final int PLAYER = 0;
    private static final int MERCHANT = 1;

    private BitmapFont font;
    private Merchant merchant;
    private Texture merchantTexture;

    private int selectedItemIndex;
    private int itemIndexX;
    private int itemIndexY;

    public boolean inside;
    private int selectedTab;

    private Texture selectedItem;
    private Texture selectedItemTexture;
    private Texture sellButton;
    private Texture buyButton;

    private List<Item> playersItems;
    private Inventory inventory;

    public TradeGUI(Player player, Assets assets) {
        super(player, assets);
        this.texture = assets.manager.get(AssetsConstants.TRADE_PANEL);
        this.width = texture.getWidth() * 2;
        this.height = texture.getHeight() * 2;
        font = new BitmapFont();
        playersItems = new ArrayList<>();
        inventory = player.getInventory();
        selectedItem = assets.manager.get(AssetsConstants.INVENTORY_SELECTED_ITEM);
        sellButton = assets.manager.get(AssetsConstants.SELL_BUTTON);
        buyButton = assets.manager.get(AssetsConstants.BUY_BUTTON);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (!isEnabled) {
            return;
        }
        batch.draw(merchantTexture, x + 846, y + 556);

        batch.draw(selectedItem, x + 40 + (WindowGap * itemIndexX) + (selectedTab * 850), y + 460 - (WindowGap * itemIndexY) + (selectedTab * -6), windowSize, windowSize);

        for (int i = 0; i < playersItems.size(); i++) {
            int yAxisIndex = i / 5;
            int xAxisIndex = i % 5;
            batch.draw(playersItems.get(i).getTexture(), x + 40 + (WindowGap * xAxisIndex), y + 456 - (WindowGap * yAxisIndex), windowSize, windowSize);
        }

        for (int i = 0; i < merchant.getItemList().size(); i++) {
            if (i > 34) break;
            int yAxisIndex = i / 5;
            int xAxisIndex = i % 5;
            batch.draw(merchant.getItemList().get(i).getTexture(), x + 40 + (WindowGap * xAxisIndex) + 846, y + 456 - (WindowGap * yAxisIndex), windowSize, windowSize);
        }

        batch.draw(selectedItemTexture, x + 594, y + 500);

        List<Item> items = selectedTab == 0 ? playersItems : merchant.getItemList();
        if(selectedItemIndex<items.size()) {
            Item describedItem = items.get(selectedItemIndex);
            for (int i = 0; i < describedItem.getDescription().size(); i++) {
                font.draw(batch,describedItem.getDescription().get(i),x + 470,y + 440 - (i * 20));
            }
        }

        if (inside) {
            if (selectedTab == MERCHANT) batch.draw(buyButton, x + 714, y + 408);
            else batch.draw(sellButton, x + 714, y + 366);
        }

        drawDescription();
    }

    private void drawDescription() {
        
    }

    @Override
    public void update() {
        super.update();
        if (!isEnabled) {
            return;
        }
        updateItemsToDisplay();
        itemIndexX = selectedItemIndex % 5;
        itemIndexY = selectedItemIndex / 5;
        updateSelectedItemTexture();
    }

    private void updateSelectedItemTexture() {

        selectedItemTexture = selectedTab == PLAYER
                ? selectedItemIndex < playersItems.size()
                    ? playersItems.get(selectedItemIndex).getTexture()
                    : selectedItem
                : selectedItemIndex < merchant.getItemList().size()
                    ? merchant.getItemList().get(selectedItemIndex).getTexture()
                    : selectedItem;
    }

    private void updateItemsToDisplay() {
        switch (merchant.getMerchantType()) {
            case BLACKSMITH:
                playersItems.clear();
                playersItems.addAll(inventory.getWeapons());
                playersItems.addAll(inventory.getArmors());
                break;
            case MISC:
                playersItems.clear();
                playersItems.addAll(inventory.getMiscellaneousItems());
                break;
            case ALCHEMIST:
                playersItems.clear();
                playersItems.addAll(inventory.getUsableItems());
                break;
        }
    }

    public void updateTextureType(Merchant.MerchantType merchantType) {
        switch (merchantType) {
            case BLACKSMITH:
                merchantTexture = assets.manager.get(AssetsConstants.BLACKSMITH);
                break;
            case ALCHEMIST:
                merchantTexture = assets.manager.get(AssetsConstants.ALCHEMIST);
                break;
            case MISC:
                merchantTexture = assets.manager.get(AssetsConstants.MERCHANT);
                break;
        }
    }

    public void listUp() {
        if (inside) return;
        if (selectedItemIndex > 4)
            selectedItemIndex -= 5;

    }

    public void listDown() {
        if (inside) return;
        if (selectedItemIndex < 30)
            selectedItemIndex += 5;

    }

    public void listLeft() {
        if (inside) return;
        if (selectedItemIndex == 0) selectedItemIndex = 34;
        else selectedItemIndex--;

    }

    public void listRight() {
        if (inside) return;
        if (selectedItemIndex == 34) selectedItemIndex = 0;
        else selectedItemIndex++;

    }

    public void enter() {
        if (!inside) {
            List<Item> listToCheck = selectedTab == PLAYER
                    ? playersItems : merchant.getItemList();
            if (!(selectedItemIndex < listToCheck.size())) return;
            inside = true;
        } else {
            if (selectedTab == PLAYER) sell();
            else if (selectedTab == MERCHANT) buy();
            inside = false;
        }
    }

    private void buy() {
        final Item item = merchant.getItemList().get(selectedItemIndex);
        inventory.getItems().add(item);
        merchant.getItemList().remove(item);
    }

    private void sell() {
        final Item item = playersItems.get(selectedItemIndex);
        merchant.getItemList().add(item);
        inventory.getItems().remove(item);
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public void changeTab() {
        if (inside) return;
        selectedTab = selectedTab == 0 ? 1 : 0;
    }
}
