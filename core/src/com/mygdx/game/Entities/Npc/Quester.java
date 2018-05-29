package com.mygdx.game.Entities.Npc;

import com.mygdx.game.quests.Quest;

public class Quester extends Npc {

    private Quest quest;

    @Override
    public void performAction() {

    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
