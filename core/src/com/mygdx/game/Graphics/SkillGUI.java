package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Skills.Skill;
import com.mygdx.game.Skills.SpellBook;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillGUI extends AbstractGUI {

    public boolean inside;

    private int index;
    private int insideIndex;

    private BitmapFont font;

    private Texture known;
    private Texture selectedSlot;
    private Texture selectedFrame;

    private SpellBook spellBook;

    private Map<Integer, Boolean> map;

    public SkillGUI(Player player, Assets assets) {
        super(player, assets);
        this.texture = assets.manager.get(AssetsConstants.SKILL_GUI);
        this.width = texture.getWidth() * 4;
        this.height = texture.getHeight() * 4;
        this.known = assets.manager.get(AssetsConstants.SKILL_ASSIGNED);
        this.selectedSlot = assets.manager.get(AssetsConstants.SKILL_SELECTED_SLOT);
        this.selectedFrame = assets.manager.get(AssetsConstants.SKILL_SELECTED_FRAME);
        font = new BitmapFont();
        this.spellBook = player.getSpellBook();
        map = new HashMap<>();
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < 16; i++) {
            map.put(i,false);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (isEnabled) {
            int scope = index / 4;
            final List<Skill> spells = spellBook.getSpells();
            for (int i = 0; i < spells.size(); i++) {
                batch.draw(spells.get(i).getTexture(),x + 40 + (i * 176) - (176 * 4 * (i/4)), y + 510 - ((i/4) * 132));
                font.draw(batch,spells.get(i).getName(),x + 90 + (i * 176) - (176 * 4 * (i/4)), y + 535 - ((i/4) * 132));
                if (map.get(i)) {
                    batch.draw(known, x + 36 + (i * 176) - (176 * 4 * (i/4)),
                            y + 440 - ((i/4) * 132), known.getWidth() * 4,
                            known.getHeight() * 4);
                }
            }
            batch.draw(selectedFrame, x + 36 + (index * 176) - (176 * 4 * scope),
                    y + 440 - (scope * 132), selectedFrame.getWidth() * 4,
                    selectedFrame.getHeight() * 4);
            if (inside) {
                batch.draw(selectedSlot,x + 424 + (insideIndex * 100),y + 576,
                        selectedSlot.getWidth() * 4, selectedSlot.getHeight() *4);
            }
            for (int i = 0; i < 3; i++) {
                final Skill bindedSkill = spellBook.getBindedSkill(i);
                if (bindedSkill != null) {
                    batch.draw(bindedSkill.getTexture(),x + 445 + (i*100), y + 600, 64,64);
                }
            }
        }
    }

    public void moveUp() {
        if (!inside) {
            index = index < 4 ? index : index - 4;
        }
    }

    public void moveDown() {
        if (!inside) {
            index = index > 11 ? index : index + 4;
        }
    }

    public void moveLeft() {
        if (!inside) {
            index = index == 0 || index == 4
                    || index == 8 || index == 12
                    ? index : index - 1;
        } else {
            insideIndex = insideIndex == 0
                    ? insideIndex : insideIndex - 1;
        }
    }

    public void moveRight() {
        if (!inside) {
            index = index == 3 ||index == 7 ||
                    index == 11 || index == 15
                    ? index : index + 1;
        } else {
            insideIndex = insideIndex == 2
                    ? insideIndex : insideIndex + 1;
        }
    }

    public void enter() {
        if (!inside) {
            if (!map.get(index)) {
                if (player.getSkillPoints() > 0) {
                    player.setSkillPoints(player.getSkillPoints() - 1);
                    map.put(index,true);
                }
            } else {
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
}
