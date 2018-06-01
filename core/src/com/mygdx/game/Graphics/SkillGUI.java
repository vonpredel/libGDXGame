package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Skills.SpellBook;
import com.mygdx.game.Skills.types.Passive;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SkillGUI extends AbstractGUI {

    public boolean inside;

    private int index;
    private int insideIndex;

    private BitmapFont font;

    private Texture known;
    private Texture selectedSlot;
    private Texture selectedFrame;
    private Texture reqBar;
    private Texture skillDes;
    private Texture passive;

    private SpellBook spellBook;

    private Map<Integer, Boolean> map;
    private Skill skill;

    public SkillGUI(Player player, Assets assets) {
        super(player, assets);
        this.texture = assets.manager.get(AssetsConstants.SPELL_BOOK_BETA);
        this.width = texture.getWidth() * 4;
        this.height = texture.getHeight() * 4;
        this.known = assets.manager.get(AssetsConstants.SKILL_ASSIGNED);
        this.selectedSlot = assets.manager.get(AssetsConstants.SKILL_SELECTED_SLOT);
        this.selectedFrame = assets.manager.get(AssetsConstants.SKILL_SELECTED_FRAME);
        this.reqBar = assets.manager.get(AssetsConstants.REQ_BAR);
        this.skillDes = assets.manager.get(AssetsConstants.SKILL_DESCRIPTION);
        this.passive = assets.manager.get(AssetsConstants.PASSIVE_SKILL);

        font = new BitmapFont();
        this.spellBook = player.getSpellBook();
        map = new HashMap<>();
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < 36; i++) {
            map.put(i,false);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isEnabled) {
            int scope = index / 6;
            final List<Skill> spells = spellBook.getSpells();
            for (int i = 0; i < spells.size(); i++) {
                Texture textureToRender = spells.get(i).getTexture();
                batch.draw(textureToRender,x + 60 + (i * 176) - (176 * 6 * (i/6)), y + 764 - ((i/6) * 132),
                        textureToRender.getWidth() * 1.5f,textureToRender.getHeight() * 1.5f);
                if (map.get(i)) {
                    Texture texture = spells.get(i) instanceof Passive ? passive : known;
                    batch.draw(texture, x + 36 + (i * 176) - (176 * 6 * (i/6)),
                            y + 704 - ((i/6) * 132), texture.getWidth() * 4,
                            texture.getHeight() * 4);
                }
            }
            batch.draw(selectedFrame, x + 36 + (index * 176) - (176 * 6 * scope),
                    y + 704 - (scope * 132), selectedFrame.getWidth() * 4,
                    selectedFrame.getHeight() * 4);
            if (inside) {
                batch.draw(selectedSlot,x - 348 + (insideIndex * 100),y + 10,
                        selectedSlot.getWidth() * 4, selectedSlot.getHeight() *4);
            }

            drawDescription(batch, spells);

            font.draw(batch,"Skill points : " + player.getSkillPoints(), x + 250, y + 650);

            batch.draw(reqBar, x+1102, y+38, reqBar.getWidth() * 4, reqBar.getHeight() * 4);


        }
    }

    private void drawDescription(Batch batch, List<Skill> spells) {
        batch.draw(skillDes,x - 350,y + 130,skillDes.getWidth()*4,skillDes.getHeight()*4);
        final List<String> description = spells.get(index).getDescription();
        for (int i = 0; i < description.size(); i++)
            font.draw(batch, description.get(i), x - 300, y + 290 - (i * 20));
        batch.draw(spells.get(index).getTexture(),x - 230 ,y + 330);
    }

    public void moveUp() {
        if (!inside) {
            index = index < 6 ? index : index - 6;
        }
    }

    public void moveDown() {
        if (!inside) {
            index = index > 29 ? index : index + 6;
        }
    }

    public void moveLeft() {
        if (!inside) {
            index = index != 0 ? index - 1 : index;
        } else {
            insideIndex = insideIndex == 0
                    ? insideIndex : insideIndex - 1;
        }
    }

    public void moveRight() {
        if (!inside) {
            index = index != 35 ? index + 1 : index;
        } else {
            insideIndex = insideIndex == 2
                    ? insideIndex : insideIndex + 1;
        }
    }

    public void enter() {
        if (!inside) {
            skill = spellBook.getSpells().get(index);
            if (!map.get(index)) {
                if (player.getSkillPoints() > 0) {
                    player.setSkillPoints(player.getSkillPoints() - 1);
                    map.put(index,true);

                    if (skill instanceof Passive) {
                        gainPassiveBonus((Passive) skill);
                    }
                }
            } else {
                if (skill instanceof Passive) {
                    return;
                }
                inside = true;
            }

        } else {
            for (int i = 0; i < 3; i++) {
                if (spellBook.getBindedSkill(i) != null && spellBook.getBindedSkill(i)
                        .equals(spellBook.getSpells().get(index))) {
                    spellBook.bindSkill(null, i);
                }
            }
            spellBook.bindSkill(spellBook.getSpells().get(index),insideIndex);
            inside = false;
        }
    }

    private void gainPassiveBonus(Passive skill) {
        skill.setActive(true);
        switch (skill.getType()) {
            case STRENGTH:
                player.setStrength(player.getStrength() + skill.getAmount());
                break;
            case DEXTERITY:
                player.setDexterity(player.getDexterity() + skill.getAmount());
                break;
            case VITALITY:
                player.setVitality(player.getVitality() + skill.getAmount());
                player.setMaxHealthPoints(player.getMaxHealthPoints() + (5) * skill.getAmount());
                break;
            case ENERGY:
                player.setEnergy(player.getEnergy() + skill.getAmount());
                player.setMaxStaminaPoints(player.getMaxStaminaPoints() + (2) * skill.getAmount());
                player.setMaxManaPoints(player.getMaxManaPoints() + skill.getAmount());
                break;
        }
    }
}
