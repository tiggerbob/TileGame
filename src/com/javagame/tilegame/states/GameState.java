package com.javagame.tilegame.states;
import com.javagame.tilegame.Handler;
import com.javagame.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
