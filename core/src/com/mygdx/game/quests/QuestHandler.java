package com.mygdx.game.quests;

import java.util.List;

public class QuestHandler {

    private List<Quest> questList;

    public void addQuest(Quest quest) {
        questList.add(quest);
    }

    public List<Quest> getQuestList() {
        return questList;
    }
}
