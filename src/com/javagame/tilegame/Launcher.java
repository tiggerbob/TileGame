package com.javagame.tilegame;

import com.javagame.tilegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Tile Game!", 640, 480);
        game.start();
    }

}
