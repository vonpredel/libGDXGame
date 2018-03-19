package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Entities.NonStatics.Player;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.Constants;

public class QuickInfoGUI extends AbstractGUI {

    private BitmapFont font;
    ShapeRenderer shapeRenderer;

    private Texture healthBar;
    private Texture staminaBar;
    private Texture manaBar;
    private Texture bottomBar;

    float healthPointsToDraw;
    float staminaPointsToDraw;
    float manaPointsToDraw;

    public QuickInfoGUI(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.QUICK_INFO);
        this.width = texture.getWidth()*2;
        this.height = texture.getHeight()*2;
        this.player = player;
        this.assets = assets;
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        healthBar = assets.manager.get(AssetsConstants.HEALTH_BAR);
        staminaBar = assets.manager.get(AssetsConstants.STAMINA_BAR);
        manaBar = assets.manager.get(AssetsConstants.MANA_BAR);
        bottomBar = assets.manager.get(AssetsConstants.BOTTOM_BAR);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
//        batch.draw(weaponTexture,x+11,y+11,64,64);
//        batch.draw(armorTexture,x+83,y+11,64,64);
        drawBars(batch);
        font.setColor(Color.WHITE);
        font.draw(batch,String.valueOf(player.getCurrentHealthPoints() + "/" + String.valueOf(player.getMaxHealthPoints())),x+150,y+110);
        font.setColor(Color.WHITE);
        font.draw(batch,String.valueOf(player.getCurrentStaminaPoints() + "/" + String.valueOf(player.getMaxStaminaPoints())),x+150,y+70);
        font.setColor(Color.WHITE);
        font.draw(batch,String.valueOf(player.getCurrentManaPoints() + "/" + String.valueOf(player.getMaxManaPoints())),x+150,y+30);
    }

    @Override
    public void update() {
        this.x = player.x - Constants.DEFAULT_RESOLUTION_WIDTH/2 + 45;
        this.y = player.y + Constants.DEFAULT_RESOLUTION_HEIGHT/2 - 110;

//        weaponTexture = player.getInventory().getEquipedWeapon()==null
//                ? assets.manager.get("items/noWeapon.png")
//                : player.getInventory().getEquipedWeapon().getTexture();
//        armorTexture = player.getInventory().getEquipedArmor()==null
//                ? assets.manager.get("items/noArmor.png")
//                : player.getInventory().getEquipedArmor().getTexture();

        healthPointsToDraw = player.getCurrentHealthPoints()<=0 ? 0
                : (float) player.getCurrentHealthPoints() * 100 / (float) player.getMaxHealthPoints() / 100;
        staminaPointsToDraw = player.getCurrentStaminaPoints()<=0 ? 0
                : (float) player.getCurrentStaminaPoints() * 100 / (float) player.getMaxStaminaPoints() / 100;
        manaPointsToDraw = player.getCurrentManaPoints()<=0 ? 0
                : (float) player.getCurrentManaPoints() * 100 / (float) player.getMaxManaPoints() / 100;
    }

    private void drawBars(SpriteBatch batch) {
        batch.draw(healthBar,x+145,y+96,(healthBar.getWidth()*2)*healthPointsToDraw,healthBar.getHeight()*2);
        batch.draw(staminaBar,x+145,y+56,(healthBar.getWidth()*2)*staminaPointsToDraw,healthBar.getHeight()*2);
        batch.draw(manaBar,x+145,y+16,(healthBar.getWidth()*2)*manaPointsToDraw,healthBar.getHeight()*2);
        batch.draw(bottomBar,x+10,y-870,bottomBar.getWidth()*2,bottomBar.getHeight()*2);
    }
}
