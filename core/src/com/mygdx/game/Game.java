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
        World.init(batch, assets, itemsManager, skillsManager, new EntitiesManager(assets, new EntitiesContainer(), itemsManager));

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
        World.getEntitiesManager().create(EntityType.FOUNTAIN, (entity, objects)
                -> entity.warp(256+64, 512));
        World.getEntitiesManager().create(EntityType.DOOR, (entity, objects)
                -> entity.warp(256+64+64, 512));



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

        World.configureCameraAndGUI();

        // Test
        World.getPlayer().getSpellBook().temporaryInit();
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
    }


    @Override
    public void dispose() {
        assets.dispose();
        World.getBatch().dispose();
    }
}