package com.mygdx.game.quests;

import com.mygdx.game.Utils.BaseManager;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.quests.types.CollectQuest;
import com.mygdx.game.quests.types.KillQuest;

public class QuestsManager extends BaseManager<Quest, QuestType, QuestsContainer> {

    public QuestsManager(Assets assets, QuestsContainer container) {
        super(assets, container);
    }

    @Override
    protected Class<? extends Quest> enumToClass(QuestType typeEnum) {
        return typeEnum.getCls();
    }

    @Override
    public void loadDefinitions() {

        this.creators.put(KillQuest.class, values -> {
            final KillQuest killQuest = new KillQuest();

            return killQuest;
        });

        this.creators.put(CollectQuest.class, values -> {
            final CollectQuest collectQuest = new CollectQuest();

            return collectQuest;
        });

        // TODO !!!!!!
        this.loadFile("dataQuests/killQuests.csv", values -> values[0]);
        this.loadFile("dataQuests/collectQuests.csv", values -> values[0]);

    }


}
