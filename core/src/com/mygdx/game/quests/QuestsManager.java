package com.mygdx.game.quests;

import com.mygdx.game.Entities.EntityType;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.ItemType;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Utils.BaseManager;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.textFile.TextFile;
import com.mygdx.game.quests.types.CollectQuest;
import com.mygdx.game.quests.types.KillQuest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestsManager extends BaseManager<Quest, QuestType, QuestsContainer> {

    private final ItemsManager itemsManager;

    public QuestsManager(Assets assets, QuestsContainer container, ItemsManager itemsManager) {
        super(assets, container);
        this.itemsManager = itemsManager;
    }

    private List<Item> createItemsList(final String itemStringValue) {
        return Arrays.stream(itemStringValue.split("\\|"))
                .map(ItemType::valueOf)
                .map(itemsManager::create)
                .map(Item.class::cast)
                .collect(Collectors.toList());
    }

    private void setDescriptionAndDialogues(String path, Quest quest) {
        List<String> lines = new ArrayList<>(Arrays.asList(getLines(assets.manager.get(path, TextFile.class))));
        quest.setDescription(lines.get(0));
        lines.remove(0);
        quest.setDialogues(lines);
    }

    private void setQuestBasics(String[] values, Quest quest) {
        quest.setName(values[0]);
        quest.setGrade(Quest.Grade.valueOf(values[1]));
        quest.assignTexture();
        setDescriptionAndDialogues(values[2], quest);
        quest.setRewards(createItemsList(values[3]));
        quest.setExpReward(Integer.parseInt(values[4]));
    }

    @Override
    protected Class<? extends Quest> enumToClass(QuestType typeEnum) {
        return typeEnum.getCls();
    }

    @Override
    public void loadDefinitions() {

        this.creators.put(KillQuest.class, values -> {
            final KillQuest killQuest = new KillQuest();
            setQuestBasics(values, killQuest);
            killQuest.setType(EntityType.valueOf(values[5]));
            killQuest.setRequiredAmount(Integer.parseInt(values[6]));
            return killQuest;
        });

        this.creators.put(CollectQuest.class, values -> {
            final CollectQuest collectQuest = new CollectQuest();
            setQuestBasics(values, collectQuest);
            collectQuest.setItemType(ItemType.valueOf(values[5]));
            collectQuest.setRequiredAmount(Integer.parseInt(values[6]));
            return collectQuest;
        });

        this.loadFile("dataQuests/killQuests.csv", values -> values[0]);
        this.loadFile("dataQuests/collectQuests.csv", values -> values[0]);

    }


}
