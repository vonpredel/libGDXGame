package com.mygdx.game.quests;
import com.mygdx.game.quests.types.KillQuest;
import com.mygdx.game.quests.types.CollectQuest;

public enum QuestType {
	TEST_KILL_QUEST(KillQuest.class),
	TEST_COLLECT_QUEST(CollectQuest.class);

    private final Class<? extends Quest> cls;

    private QuestType(Class<? extends Quest> cls) {
        this.cls = cls;
    }

    public Class<? extends Quest> getCls() {
        return cls;
    }
}