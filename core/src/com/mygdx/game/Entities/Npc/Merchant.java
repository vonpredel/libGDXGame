package com.mygdx.game.Entities.Npc;

import com.mygdx.game.Items.Item;
import java.util.List;

public class Merchant extends Npc {

    // MERCHANT FIELDS
    private MerchantType merchantType;
    private List<Item> itemList;

    @Override
    public void performAction() {

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
