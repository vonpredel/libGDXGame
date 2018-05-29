package com.mygdx.game.quests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Utils.BaseContainer;
import com.mygdx.game.quests.types.KillQuest;
import java.util.List;
import java.util.stream.Collectors;

public class QuestsContainer extends BaseContainer<Quest> {

    @Override
    public void draw(SpriteBatch batch) {

    }

    public List<KillQuest> getKillQuests() {
        return getAllItems().stream().filter(KillQuest.class::isInstance)
                .map(KillQuest.class::cast).collect(Collectors.toList());
    }
}
