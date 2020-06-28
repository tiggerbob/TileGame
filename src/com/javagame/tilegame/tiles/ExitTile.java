package com.javagame.tilegame.tiles;

import com.javagame.tilegame.gfx.Assets;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ExitTile extends Tile{
    public ExitTile(int id) {
        super(Assets.exit, id);
    }
}
