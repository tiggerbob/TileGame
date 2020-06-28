package com.javagame.tilegame.states;
import com.javagame.tilegame.Handler;
import com.javagame.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    public static World world1, world2;

    public GameState(Handler handler) {
        super(handler);
        world1 = new World(handler, "res/worlds/world1.txt");
        world2 = new World(handler, "res/worlds/world2.txt");
        handler.setWorld(world1);
    }

    @Override
    public void tick() {
        world1.tick();
    }

    @Override
    public void render(Graphics g) {
        world1.render(g);
    }

//    public static World getWorld1() {
//        return world1;
//    }
//
//    public static World getWorld2() {
//        return world2;
//    }
}
