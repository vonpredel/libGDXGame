package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.World.World;
import com.mygdx.game.quests.Quest;
import com.mygdx.game.quests.QuestHandler;
import java.util.List;

// TODO
public class QuestGUI extends AbstractGUI {

    private BitmapFont font;

    private QuestHandler questHandler;
    private int allowedPages;
    private int page = 1;

    int indexHelper;
    int rangeHelper;

    public QuestGUI(Player player, Assets assets) {
        super(player, assets);
        this.texture = assets.manager.get(AssetsConstants.QUEST_INFO);
        this.width = texture.getWidth() * 2;
        this.height = texture.getHeight() * 2;
        questHandler = player.getQuestHandler();
        font = new BitmapFont();
    }

    public void changePageRight() {
        if (page < allowedPages) {
            page++;
        }
    }

    public void changePageLeft() {
        if (page == 1) {
            return;
        }
        page--;
    }


    // TODO TODO
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        final List<Quest> questList = questHandler.getQuestList();
        if (!isEnabled || questList.isEmpty()) {
            return;
        }

        font.draw(batch, page + " / " + allowedPages, x + 240, y + 80);
        for (int i = indexHelper; i < rangeHelper; i++) {
            Quest quest = questList.get(i);
            float yPos = y + 54 - ((i - (page * 6)) * 100);
            batch.draw(quest.getTexture(), x + 54,
                    yPos);
            font.draw(batch, quest.getName(), x + 140, yPos + 75);
            font.draw(batch, quest.getDescription(), x + 140, yPos + 55);
            // OPTIONAL
            font.draw(batch, quest.getProgressStatus(), x + 140, yPos + 35);
        }
    }

    @Override
    public void update() {
        this.x = player.x + Constants.DEFAULT_RESOLUTION_WIDTH / 5;
        this.y = player.y - Constants.DEFAULT_RESOLUTION_HEIGHT / 3;

        indexHelper = (page - 1) * 6;
        rangeHelper
                = questHandler.getQuestList().size() - 6 > indexHelper
                ? indexHelper + 6 : questHandler.getQuestList().size();

        allowedPages = questHandler.getQuestList().size() / 6 + 1;
        allowedPages = questHandler.getQuestList().size() % 6 == 0
                && allowedPages > 1
                ? allowedPages - 1 : allowedPages;
    }
}
