package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Entities.NonStatics.Characters.Player;
import com.mygdx.game.Utils.assets.Assets;
import com.mygdx.game.Utils.assets.AssetsConstants;
import com.mygdx.game.Utils.Constants;
import java.awt.Rectangle;

public class QuickInfoGUI extends AbstractGUI {

    private BitmapFont font;
    ShapeRenderer shapeRenderer;

    private Texture weaponTexture;
    private Texture armorTexture;

    public QuickInfoGUI(Player player, Assets assets) {
        this.texture = assets.manager.get(AssetsConstants.QUISC_INFO);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
        this.assets = assets;
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(weaponTexture,x+11,y+11,64,64);
        batch.draw(armorTexture,x+83,y+11,64,64);
        font.setColor(Color.RED);
        font.draw(batch,String.valueOf(player.getCurHP() + "/" + String.valueOf(player.getMaxHP())),x+80,y+110);
    }

    @Override
    public void update() {
        this.x = player.x - Constants.DEFAULT_RESOLUTION_WIDTH/2 + 45;
        this.y = player.y + Constants.DEFAULT_RESOLUTION_HEIGHT/2 - 105;

        weaponTexture = player.getInventory().getEquipedWeapon()==null
                ? assets.manager.get("items/noWeapon.png")
                : player.getInventory().getEquipedWeapon().getTexture();
        armorTexture = player.getInventory().getEquipedArmor()==null
                ? assets.manager.get("items/noArmor.png")
                : player.getInventory().getEquipedArmor().getTexture();
    }

    public void drawShapes(SpriteBatch batch) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(x+7,y+80,143,16);
        shapeRenderer.setColor(Color.RED);
        float maxHP = player.getMaxHP();
        float currHP = player.getCurHP();
        float healthFillPoints;
        if(currHP<=0) healthFillPoints = 0;
        else {
            healthFillPoints = (currHP*100)/maxHP;
            healthFillPoints/=100;
        }
        shapeRenderer.rect(x+7,y+80,143*healthFillPoints,16);
        shapeRenderer.end();
    }
}
