package com.javagame.tilegame.tiles;

import com.javagame.tilegame.gfx.Assets;

public class RockTile extends Tile{

    public RockTile(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
