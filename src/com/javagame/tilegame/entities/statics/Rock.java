package com.javagame.tilegame.entities.statics;

import com.javagame.tilegame.Handler;
import com.javagame.tilegame.gfx.Assets;
import com.javagame.tilegame.items.Item;
import com.javagame.tilegame.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity{

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
    }

    public boolean isCreature() {
        return false;
    }
}
