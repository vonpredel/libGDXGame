package com.mygdx.game.Utils;

import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.textFile.TextFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BaseManager<CLS extends Updateable, ENUM extends Enum<ENUM>, CONTAINER extends BaseContainer<CLS>> {

    protected final Map<String, String[]> rawValues;
    protected final Map<Class<? extends CLS>, Function<String[], CLS>> creators;
    protected final Map<String, String> nameCache;

    protected final Assets assets;
    protected final CONTAINER container;

    public BaseManager(final Assets assets, final CONTAINER container) {
        this.assets = assets;
        this.container = container;

        this.rawValues = new HashMap<>();
        this.creators = new HashMap<>();
        this.nameCache = new HashMap<>();
    }

    protected String[] getLines(final TextFile textFile) {
        return textFile.getContent().split("\\n");
    }

    private String[] getValues(final String line) {
        return line.split(",");
    }

    private String enumToName(final ENUM typeEnum) {
        return this.nameCache.computeIfAbsent(typeEnum.name(), itemName -> Arrays
                .stream(itemName.toLowerCase().split("_"))
                .map(x -> Character.toUpperCase(x.charAt(0)) + x.substring(1))
                .collect(Collectors.joining(" ")));
    }

    protected void loadFile(final String fileName, final Function<String[], String> nameProvider) {
        final String[] fileLines = this.getLines(this.assets.manager.get(fileName, TextFile.class));
        for (final String fileLine : fileLines) {
            final String[] values = this.getValues(fileLine);
            for (int i = 1; i < values.length; i++) {
                values[i] = values[i].replace(" ","");
            }
            this.rawValues.put(nameProvider.apply(values), values);
        }
    }

    protected abstract Class<? extends CLS> enumToClass(final ENUM typeEnum);

    public abstract void loadDefinitions();

    @SuppressWarnings("unchecked")
    public final <T extends CLS> T create(final ENUM typeEnum) {
        return this.create(typeEnum, null, (Object[]) null);
    }

    @SuppressWarnings("unchecked")
    public final <T extends CLS> T create(final ENUM typeEnum,
                                           final BiConsumer<T, Object[]> afterCreate,
                                           final Object... params) {
        final String typeToName = this.enumToName(typeEnum);
        final String[] rawValues = this.rawValues.get(typeToName);
        final Function<String[], CLS> creator = this.creators.get(this.enumToClass(typeEnum));

        final T createdItem = (T) creator.apply(rawValues);
        this.container.addItem(createdItem);

        if (afterCreate != null && params != null) {
            afterCreate.accept(createdItem, params);
        }

        return createdItem;
    }

    public CONTAINER getContainer() {
        return this.container;
    }

}
