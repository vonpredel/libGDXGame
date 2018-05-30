package com.mygdx.game.Entities.Npc;

import com.mygdx.game.ControlAndGUIs.ControlsAndGUIsHandler;
import com.mygdx.game.Items.Item;
import com.mygdx.game.World.World;
import java.util.List;

public class Merchant extends Npc {

    private MerchantType merchantType;
    private List<Item> itemList;

    @Override
    public void performAction() {
        final ControlsAndGUIsHandler controlsAndGUIsHandler = World.getControlsAndGUIsHandler();
        controlsAndGUIsHandler.tradeGUI.setMerchant(this);
        controlsAndGUIsHandler.tradeGUI.updateTextureType(merchantType);
        controlsAndGUIsHandler.tradeGUI.isEnabled = true;
        controlsAndGUIsHandler.setTradeState();
    }

    public MerchantType getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(MerchantType merchantType) {
        this.merchantType = merchantType;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public enum MerchantType {
        BLACKSMITH,ALCHEMIST,MISC
    }

}
