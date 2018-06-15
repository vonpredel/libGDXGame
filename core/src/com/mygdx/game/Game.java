package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.EntitiesContainer;
import com.mygdx.game.Entities.EntitiesManager;
import com.mygdx.game.Entities.EntityType;
import com.mygdx.game.Items.ItemsContainer;
import com.mygdx.game.Items.ItemsManager;
import com.mygdx.game.Skills.SkillsContainer;
import com.mygdx.game.Skills.SkillsManager;
import com.mygdx.game.Tiles.Tile;
import com.mygdx.game.Utils.Debugger;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.World.World;
import com.mygdx.game.Zones.WorldGenerator;
import com.mygdx.game.quests.QuestsContainer;
import com.mygdx.game.quests.QuestsManager;

import sun.java2d.opengl.WGLSurfaceData;

public class Game extends ApplicationAdapter {

    private Assets assets;

    @Override
    public void create() {
        assets = new Assets();
        assets.load();
        assets.manager.finishLoading();
        if (assets.manager.update()) {
            worldInit();
            loadEntities();
        }
    }

    private void worldInit() {
        Debugger.setDebug(true);

        SpriteBatch batch = new SpriteBatch();
        ItemsManager itemsManager = new ItemsManager(assets, new ItemsContainer());
        SkillsManager skillsManager = new SkillsManager(assets, new SkillsContainer());
        QuestsManager questsManager = new QuestsManager(assets, new QuestsContainer(), itemsManager);
        World.init(batch, assets, itemsManager, skillsManager, questsManager, new EntitiesManager(assets, new EntitiesContainer(), itemsManager, questsManager));

        new WorldGenerator().generate(10, 10);
        World.setWorldDimensions(10, 10);
        World.getZoneRenderer().prepareAllTiles();
        World.sortTilesList();
        World.setCurrentZone(World.getZoneContainer().getZoneList().get(0));
    }

    private void loadEntities() {
        World.getEntitiesManager().create(EntityType.PLAYER, (entity, objects)
                -> entity.warp(256, 256));
        World.getEntitiesManager().create(EntityType.NIGGA, (entity, objects)
                -> entity.warp(600, 600));
        World.getEntitiesManager().create(EntityType.GOBLIN, ((entity, objects)
                -> entity.warp(1200,600)));



        World.getEntitiesManager().create(EntityType.CHEST, (entity, objects)
                -> entity.warp(256, 512));

        World.getEntitiesManager().create(EntityType.CHEST_IRON, (entity, objects)
                -> entity.warp(256, 2048));
        World.getEntitiesManager().create(EntityType.CHEST_STEEL, (entity, objects)
                -> entity.warp(256+(64), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_GOLD, (entity, objects)
                -> entity.warp(256+(64*2), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_BRASS, (entity, objects)
                -> entity.warp(256+(64*3), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_ELVEN, (entity, objects)
                -> entity.warp(256+(64*4), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_MOONSTONE, (entity, objects)
                -> entity.warp(256+(64*5), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_OBSIDIAN, (entity, objects)
                -> entity.warp(256+(64*6), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_DRAGON, (entity, objects)
                -> entity.warp(256+(64*7), 2048));
        World.getEntitiesManager().create(EntityType.CHEST_SPEARS, (entity, objects)
                -> entity.warp(256+(64*8), 2048));


        World.getEntitiesManager().create(EntityType.FOUNTAIN, (entity, objects)
                -> entity.warp(256+64, 512));
        World.getEntitiesManager().create(EntityType.DOOR, (entity, objects)
                -> entity.warp(256+64+64, 512));
        World.getEntitiesManager().create(EntityType.CHEST_FURS, (entity, objects)
                -> entity.warp(256, 1024));



        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64, 256));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*2, 256));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*3, 256));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*4, 256));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*5, 256));

        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*7, 256+64));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*7, 256+64*2));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*7, 256+64*3));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*7, 256+64*4));
        World.getEntitiesManager().create(EntityType.TESTER, (entity, objects)
                -> entity.warp(256+64*7, 256+64*5));

        World.getEntitiesManager().create(EntityType.QUESTER, (entity, objects)
                -> entity.warp(1024, 1024));
        World.getEntitiesManager().create(EntityType.QUESTER2, (entity, objects)
                -> entity.warp(1024, 1024+128));
        World.getEntitiesManager().create(EntityType.MERCHANT, (entity, objects)
                -> entity.warp(1024, 1024+256));

        World.configureCameraAndGUI();

        // Test
        World.getPlayer().getSpellBook().temporaryInit();
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(124/255.0f, 196/255.0f, 63/255.0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final SpriteBatch batch = World.getBatch();
        batch.begin();
        World.getZoneRenderer().renderZone();
        World.getItemsContainer().draw(batch);
        World.getEntitiesContainer().draw(batch);
        World.getControlsAndGUIsHandler().draw(batch);
        batch.end();
    }

    private void update() {
        Debugger.update();

        World.getTileList().forEach(Tile::update);
        World.updateCurrentZone();
        World.getCameraHandler().update();
        World.getControlsAndGUIsHandler().update();
        World.getItemsContainer().update();
        World.getEntitiesContainer().update();
        World.getSkillsContainer().update();
    }


    @Override
    public void dispose() {
        assets.dispose();
        World.getBatch().dispose();
    }
}