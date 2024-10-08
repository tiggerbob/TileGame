package com.javagame.tilegame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static Font font28;

    public static BufferedImage dirt, grass, stone, exit, tree, rock, wood;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] player_down_atk, player_up_atk, player_left_atk, player_right_atk;
    public static BufferedImage[] zombie_down_atk, zombie_up_atk, zombie_left_atk, zombie_right_atk;
    public static BufferedImage[] btn_start;
    public static BufferedImage inventoryScreen;

    public static void init() {
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
        btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        player_down[0] = sheet.crop(width * 4, 0, width, height);
        player_down[1] = sheet.crop(width * 5, 0, width, height);
        player_up[0] = sheet.crop(width * 6, 0, width, height);
        player_up[1] = sheet.crop(width * 7, 0, width, height);
        player_right[0] = sheet.crop(width * 4, height, width, height);
        player_right[1] = sheet.crop(width * 5, height, width, height);
        player_left[0] = sheet.crop(width * 6, height, width, height);
        player_left[1] = sheet.crop(width * 7, height, width, height);

        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];

        zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
        zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
        zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
        zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
        zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
        zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
        zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
        zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);

        player_down_atk = new BufferedImage[2];
        player_up_atk = new BufferedImage[2];
        player_left_atk = new BufferedImage[2];
        player_right_atk = new BufferedImage[2];

        player_down_atk[0] = sheet.crop(0, height * 4, width, height);
        player_down_atk[1] = sheet.crop(width, height * 4, width, height);
        player_up_atk[0] = sheet.crop(width * 2, height * 4, width, height);
        player_up_atk[1] = sheet.crop(width * 3, height * 4, width, height);
        player_right_atk[0] = sheet.crop(0, height * 5, width, height);
        player_right_atk[1] = sheet.crop(width, height * 5, width, height);
        player_left_atk[0] = sheet.crop(width * 2, height * 5, width, height);
        player_left_atk[1] = sheet.crop(width * 3, height * 5, width, height);

        zombie_down_atk = new BufferedImage[2];
        zombie_up_atk = new BufferedImage[2];
        zombie_left_atk = new BufferedImage[2];
        zombie_right_atk = new BufferedImage[2];

        zombie_down_atk[0] = sheet.crop(0, height * 6, width, height);
        zombie_down_atk[1] = sheet.crop(width, height * 6, width, height);
        zombie_up_atk[0] = sheet.crop(width * 2, height * 6, width, height);
        zombie_up_atk[1] = sheet.crop(width * 3, height * 6, width, height);
        zombie_right_atk[0] = sheet.crop(0, height * 7, width, height);
        zombie_right_atk[1] = sheet.crop(width, height * 7, width, height);
        zombie_left_atk[0] = sheet.crop(width * 2, height * 7, width, height);
        zombie_left_atk[1] = sheet.crop(width * 3, height * 7, width, height);

        tree = sheet.crop(0, 0, width, height * 2);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);
        exit = sheet.crop(width * 2, height, width, height);
        rock = sheet.crop(0, height * 2, width, height);
        wood = sheet.crop(width, height, width, height);
    }

}
