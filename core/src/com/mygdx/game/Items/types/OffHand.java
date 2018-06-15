package com.mygdx.game.Items.types;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.World.World;
import java.util.ArrayList;
import java.util.List;

public class OffHand extends Item {

    private OffHandType offHandType;
    private float value;

    private boolean active = false;
    private float timeHelper;

    public OffHand(String name, int price, OffHandType offHandType, float value) {
        super(name, price);
        this.offHandType = offHandType;
        this.value = value;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>(3);
        description.add(name);
        description.add("Type : " + String.valueOf(offHandType).toLowerCase());
        String des = "";
        switch (offHandType) {
            case QUIVER:
                des = "Increase attack speed : ";
                break;
            case SHIELD:
                des = "Increase defence : ";
                break;
            case MAGIC:
                des = "Regenerates 1 mana per : ";
                break;
        }
        description.add(des + value);
        return description;
    }

    @Override
    public void update() {
        super.update();
        if (!active) return;
        final Player player = World.getPlayer();
        if (player.getCurrentManaPoints() == player.getMaxManaPoints()) {
            return;
        }
        timeHelper += Gdx.graphics.getDeltaTime();
        if (timeHelper > value) {
            player.setCurrentManaPoints(player.getCurrentManaPoints() + 1);
            timeHelper = 0;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OffHandType getOffHandType() {
        return offHandType;
    }

    public void setOffHandType(OffHandType offHandType) {
        this.offHandType = offHandType;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public enum OffHandType {
        SHIELD, MAGIC, QUIVER
    }
}
