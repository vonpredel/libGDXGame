package com.mygdx.game.Entities.NonStatics.Characters;

import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.types.Armor;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.inventory.Inventory;
import java.util.Optional;

public abstract class Character extends NonStatic {

    protected Inventory inventory;

    @Override
    protected void die() {
        super.die();
        dropEquipment();
    }

    public abstract void initializeInventory();

    protected void dropEquipment() {
        Tile tile = getCurrentTile();
        if(inventory.getEquipedWeapon()!=null) inventory.getEquipedWeapon().generateOnMap(tile.x, tile.y);
        if(inventory.getEquipedArmor()!=null) inventory.getEquipedArmor().generateOnMap(tile.x, tile.y);
        inventory.getItems().forEach(i -> i.generateOnMap(tile.x,tile.y));
        inventory.getItems().clear();
    }

    public void dropItem(Item item) {
        Tile tile = getCurrentTile();
        item.generateOnMap(tile.x,tile.y);
        inventory.getItems().remove(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    protected Optional<Weapon> getWeapon() {
        return Optional.ofNullable(this.getInventory().getEquipedWeapon());
    }

    @Override
    public int getDefence() {
        final Armor equipedArmor = this.getInventory().getEquipedArmor();
        return equipedArmor == null ? 0 : equipedArmor.getDefence();
    }

    @Override
    public int getMinDamage() {
        return this.getWeapon().map(Weapon::getMinDamage).orElse(0);
    }

    @Override
    public int getMaxDamage() {
        return this.getWeapon().map(Weapon::getMaxDamage).orElse(1);
    }

    @Override
    public float getAttackSpeed() {
        return this.getWeapon().map(Weapon::getSpeed).orElse(1.0f);
    }

    @Override
    public int getCritChance() {
        return this.getWeapon().map(Weapon::getCritChance).orElse(0);
    }

    @Override
    public int getAccuracy() {
        return this.getWeapon().map(Weapon::getAccuracy).orElse(50);
    }
}
