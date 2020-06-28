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
        if (handler.getWorld() == world1)
            world1.tick();
        else if (handler.getWorld() == world2)
            world2.tick();
        else
            System.out.println("world not found (tick)");
    }

    @Override
    public void render(Graphics g) {
        if (handler.getWorld() == world1)
            world1.render(g);
        else if (handler.getWorld() == world2)
            world2.render(g);
        else
            System.out.println("world not found (render)");
    }

    public static World getWorld1() {
        return world1;
    }

    public static World getWorld2() {
        return world2;
    }
}
