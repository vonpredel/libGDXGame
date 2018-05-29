package com.mygdx.game.Entities.Npc;

import com.mygdx.game.quests.Quest;

public class Quester extends Npc {

    private Quest quest;

    @Override
    public void performAction() {
        switch (quest.getStatus()) {
            case NONTAKEN:
                quest.beginQuest();
                break;
            case TAKEN:
                if (quest.checkIsQuestCompleted()) {
                    quest.completeQuest();
                }
                break;
        }
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
