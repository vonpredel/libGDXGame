package com.mygdx.game.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Characters.Player;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Utils.AssetsConstants;
import java.util.ArrayList;
import java.util.List;

public class InventoryGUI extends AbstractGUI {

    public InventoryGUI(Player player) {
        this.texture = new Texture(AssetsConstants.INVENTORY);
        this.x = 0;
        this.y = 0;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.player = player;
    }

    @Override
    public void update() {
        super.update();
        final List<Item> items = player.getInventory().getItems();

    }

    @Override
    public void draw(SpriteBatch batch, BitmapFont font) {
        super.draw(batch, font);
        final List<Item> items = player.getInventory().getItems();

        List<String> str = new ArrayList<>(items.size());

        items.forEach(i -> str.add(i.getName()));

        if(isEnabled)
        for (int i = 0; i < str.size(); i++) {
            font.draw(batch,str.get(i),x+500,y+200+(50*i));
        }
    }
}
