package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;

public class CharacterPanelGUI extends AbstractGUI {

    private static final int SPACE = 50;

    private BitmapFont font;

    private float textScopeX;
    private float textScopeY;

    public CharacterPanelGUI(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.CHARACTER_INFO);
        this.width = texture.getWidth() * 4;
        this.height = texture.getHeight() * 4;
        this.player = player;
        font = new BitmapFont();
        this.assets = assets;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isEnabled) {
            font.draw(batch, "LEVEL " + "1", textScopeX, textScopeY);
            font.draw(batch, "STRENGTH " + player.getStrength(), textScopeX, textScopeY - SPACE);
            font.draw(batch, "DEXTERITY " + player.getDexterity(), textScopeX, textScopeY - SPACE*2);
            font.draw(batch, "VITALITY " + player.getVitality(), textScopeX, textScopeY - SPACE*3);
            font.draw(batch, "ENERGY " + player.getEnergy(), textScopeX, textScopeY - SPACE*4);
            font.draw(batch, "HEALTH " + player.getCurrentHealthPoints() + " / " + player.getMaxHealthPoints(), textScopeX, textScopeY - SPACE*5);
            font.draw(batch, "STAMINA " + player.getCurrentStaminaPoints() + " / " + player.getMaxStaminaPoints(), textScopeX, textScopeY - SPACE*6);
            font.draw(batch, "MANA " + player.getCurrentManaPoints() + " / " + player.getMaxManaPoints(), textScopeX, textScopeY - SPACE*7);
            font.draw(batch, "DAMAGE " + player.getMinDamage() + " - " + player.getMaxDamage(), textScopeX + SPACE*4, textScopeY );
            font.draw(batch, "DEFENCE " + player.getDefence(), textScopeX + SPACE*4, textScopeY - SPACE);
            font.draw(batch, "ACCURACY " + player.getAccuracy(), textScopeX + SPACE*4 , textScopeY - SPACE*2);
            font.draw(batch, "CRITICAL STRIKE " + player.getCritChance(), textScopeX + SPACE*4, textScopeY - SPACE*3);
            font.draw(batch, "ATTACK SPEED " + player.getAttackSpeed(), textScopeX + SPACE*4, textScopeY - SPACE*4);
            font.draw(batch, "MOVEMENT SPEED " + player.getMovementSpeed(), textScopeX + SPACE*4, textScopeY - SPACE*5);

            // TODO !!!!!!!!
            font.draw(batch, "EQUIPED WEAPON " + player.getMovementSpeed(), textScopeX + SPACE*8, textScopeY - SPACE);

            font.draw(batch, "EQUIPED ARMOR " + player.getMovementSpeed(), textScopeX + SPACE*8, textScopeY - SPACE*2);

            font.draw(batch, "EQUIPED SHIELD " + player.getMovementSpeed(), textScopeX + SPACE*8, textScopeY - SPACE*3);

            font.draw(batch, "EQUIPED HELMET " + player.getMovementSpeed(), textScopeX + SPACE*8, textScopeY - SPACE*4);
        }
    }

    @Override
    public void update() {
        super.update();
        textScopeX = x + 35;
        textScopeY = y + 660;
    }
}
