package com.mygdx.game.Entities.NonStatics.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.NonStatics.NonStatic;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.World.World;
import com.mygdx.game.inventory.Inventory;

public abstract class Character extends NonStatic {

    protected Inventory inventory;

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void attack(Tile tile) {
        if (!isAttacking && World.isTileOccupied(tile)) {
            Character targetCharacter = World.getCharacterFromTile(tile);
            if (!(targetCharacter instanceof NonStatic)) return;
            isAttacking = true;
            int damage;
            if(!(MathUtils.random(1,100) <= inventory.getEquipedWeapon().getAccuracy())) damage = 0;
            else {
                damage = MathUtils.random(this.inventory.getEquipedWeapon().getMinDamage(),
                        this.inventory.getEquipedWeapon().getMaxDamage());
                damage = (MathUtils.random(1,100) <= this.inventory.getEquipedWeapon().getCritChance() ? damage*2 : damage)
                        - targetCharacter.inventory.getEquipedArmor().getDefence();
                damage = Math.max(0,damage);
            }
            targetCharacter.hurt(damage);
            targetCharacter.isDamaged = true;
            targetCharacter.damageGot = damage;
            targetCharacter.cleanDamageTimerHelper = 0f;
            attackTimeHelper = 0;
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
        inventory.getItems().forEach(i -> i.generateOnMap(tile.x,tile.y));
    }

    protected void dropAllItems() {
        inventory.getItems().forEach(this::dropItem);
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
