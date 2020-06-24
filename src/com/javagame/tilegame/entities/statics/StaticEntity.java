package com.javagame.tilegame.entities.statics;

import com.javagame.tilegame.Handler;
import com.javagame.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
