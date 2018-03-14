package com.mygdx.game.Entities.NonStatics.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.Creatures.Creature;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.inventory.Inventory;

public abstract class Character extends NonStatic {

    protected Inventory inventory;

    @Override
    public int countDamage(NonStatic targetNonStatic) {
        int damage;
        if(!(MathUtils.random(1,100) <= inventory.getEquipedWeapon().getAccuracy())) return 0;
        else {
            int reducedDamage = targetNonStatic instanceof Character
                    ? ((Character) targetNonStatic).getInventory().getEquipedArmor().getDefence()
                    : ((Creature) targetNonStatic).getDefence();

            damage = MathUtils.random(inventory.getEquipedWeapon().getMinDamage(),
                    inventory.getEquipedWeapon().getMaxDamage());
            damage = (MathUtils.random(1,100) <= inventory.getEquipedWeapon().getCritChance() ? damage*2 : damage)
                    - reducedDamage;
            return Math.max(0,damage);
        }
    }

    @Override
    protected void die() {
        super.die();
        dropEquipment();
    }

    @Override
    public void attackUpdate() {
        attackTimeHelper += Gdx.graphics.getDeltaTime();
        if (attackTimeHelper > this.inventory.getEquipedWeapon().getSpeed()) {
            this.isAttacking = false;
            attackTimeHelper = 0;
        }
    }

    public abstract void initializeInventory();

    protected void dropEquipment() {
        Tile tile = getCurrentTile();
        inventory.getEquipedWeapon().generateOnMap(tile.x,tile.y);
        inventory.getEquipedArmor().generateOnMap(tile.x,tile.y);
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
}
