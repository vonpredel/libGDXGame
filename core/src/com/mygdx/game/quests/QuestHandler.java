package com.mygdx.game.quests;

import java.util.ArrayList;
import java.util.List;

public class QuestHandler {

    private List<Quest> questList;

    public QuestHandler() {
        this.questList = new ArrayList<>();
    }

    public void addQuest(Quest quest) {
        questList.add(quest);
    }

    public List<Quest> getQuestList() {
        return questList;
    }
}
