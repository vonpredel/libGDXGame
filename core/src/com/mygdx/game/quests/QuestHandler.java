package com.mygdx.game.quests;

import java.util.List;

public class QuestHandler {

    private List<Quest> questList;

    public QuestHandler(List<Quest> questList) {
        this.questList = questList;
    }

    public void addQuest(Quest quest) {
        questList.add(quest);
    }

    public List<Quest> getQuestList() {
        return questList;
    }
}
