package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Items.types.Weapon;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import java.awt.TextArea;

public class CharacterPanelGUI extends AbstractGUI {

    private static final int STRENGHT_INDEX = 0;
    private static final int DEXTERITY_INDEX = 1;
    private static final int VITALITY_INDEX = 2;
    private static final int ENERGY_INDEX = 3;

    private static final int SPACE = 50;

    private BitmapFont font;
    private Texture addStat;
    private Texture addStatSelected;

    private int index = 0;

    private float textScopeX;
    private float textScopeY;

    public CharacterPanelGUI(Player player, Assets assets) {
        super(player, assets);
        addStat = assets.manager.get(AssetsConstants.ADD_STAT);
        addStatSelected = assets.manager.get(AssetsConstants.ADD_STAT_SELECTED);
        this.texture = assets.manager.get(AssetsConstants.CHARACTER_INFO);
        this.width = texture.getWidth() * 4;
        this.height = texture.getHeight() * 4;
        font = new BitmapFont();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isEnabled) {
            font.draw(batch, "LEVEL " + player.getLevel(), textScopeX, textScopeY);

            font.draw(batch, "HEALTH " + player.getCurrentHealthPoints() + " / " + player.getMaxHealthPoints(), textScopeX, textScopeY - SPACE*5);
            font.draw(batch, "STAMINA " + player.getCurrentStaminaPoints() + " / " + player.getMaxStaminaPoints(), textScopeX, textScopeY - SPACE*6);
            font.draw(batch, "MANA " + player.getCurrentManaPoints() + " / " + player.getMaxManaPoints(), textScopeX, textScopeY - SPACE*7);
            font.draw(batch, "DAMAGE " + player.getMinDamage() + " - " + player.getMaxDamage(), textScopeX + SPACE*4, textScopeY );
            font.draw(batch, "DEFENCE " + player.getDefence(), textScopeX + SPACE*4, textScopeY - SPACE);
            font.draw(batch, "ACCURACY " + player.getAccuracy(), textScopeX + SPACE*4 , textScopeY - SPACE*2);
            font.draw(batch, "CRITICAL STRIKE " + player.getCritChance(), textScopeX + SPACE*4, textScopeY - SPACE*3);
            font.draw(batch, "ATTACK SPEED " + player.getAttackSpeed(), textScopeX + SPACE*4, textScopeY - SPACE*4);
            font.draw(batch, "MOVEMENT SPEED " + player.getMovementSpeed(), textScopeX + SPACE*4, textScopeY - SPACE*5);
            font.draw(batch, "EXPERIENCE " + player.getExperience() + " / " + player.getExperienceToNextLevel(),textScopeX + SPACE*4, textScopeY - SPACE*6);

            font.draw(batch, "POINTS TO SPEND " + player.getPointsToSpend(), textScopeX + SPACE*8, textScopeY - SPACE*1);

            batch.draw(addStat,textScopeX + SPACE*8,textScopeY - SPACE*2);
            font.draw(batch, "STRENGTH " + player.getStrength(), textScopeX + SPACE*9, textScopeY - SPACE*1.5f);

            batch.draw(addStat,textScopeX + SPACE*8,textScopeY - SPACE*3);
            font.draw(batch, "DEXTERITY " + player.getDexterity(), textScopeX + SPACE*9, textScopeY - SPACE*2.5f);

            batch.draw(addStat,textScopeX + SPACE*8,textScopeY - SPACE*4);
            font.draw(batch, "VITALITY " + player.getVitality(), textScopeX + SPACE*9, textScopeY - SPACE*3.5f);

            batch.draw(addStat,textScopeX + SPACE*8,textScopeY - SPACE*5);
            font.draw(batch, "ENERGY " + player.getEnergy(), textScopeX + SPACE*9, textScopeY - SPACE*4.5f);

            batch.draw(addStatSelected,textScopeX + SPACE*8,textScopeY - SPACE*index - SPACE*2);
        }
    }

    public void listUp() {
        index = index == 0 ? 3 : index-1;
    }

    public void listDown() {
        index = index == 3 ? 0 : index+1;
    }

    public void addStat() {
        if (player.getPointsToSpend()!=0) {
            player.setPointsToSpend(player.getPointsToSpend()-1);
            switch (index) {
                case STRENGHT_INDEX:
                    player.setStrength(player.getStrength()+1);
                    break;
                case DEXTERITY_INDEX:
                    player.setDexterity(player.getDexterity()+1);
                    break;
                case VITALITY_INDEX:
                    player.setVitality(player.getVitality()+1);
                    player.setMaxHealthPoints(player.getMaxHealthPoints()+5);
                    break;
                case ENERGY_INDEX:
                    player.setEnergy(player.getEnergy()+1);
                    player.setMaxStaminaPoints(player.getMaxStaminaPoints()+2);
                    player.setMaxManaPoints(player.getMaxManaPoints()+1);
                    break;
            }
        }
    }

    @Override
    public void update() {
        super.update();
        textScopeX = x + 35;
        textScopeY = y + 660;
    }
}
