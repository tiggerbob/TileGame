package com.javagame.tilegame.entities.creatures;

import com.javagame.tilegame.Handler;
import com.javagame.tilegame.gfx.Animation;
import com.javagame.tilegame.gfx.Assets;
import com.javagame.tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zombie extends Creature{

    private Animation animUp, animDown, animLeft, animRight;
    private int rand = (int) Math.floor(Math.random() * 4), count = 0;
    private int seeingDist = 100;

    public Zombie(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        speed = 1.5f;
        health = 10;

        bounds.x = 23;
        bounds.y = 31;
        bounds.width = 18;
        bounds.height = 32;

        animDown = new Animation(250, Assets.zombie_down);
        animUp = new Animation(250, Assets.zombie_up);
        animLeft = new Animation(250, Assets.zombie_left);
        animRight = new Animation(250, Assets.zombie_right);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();

        count++;
        if (count >= 30) {
            rand = (int) Math.floor(Math.random() * 4);
            count = 0;
        }

        if(Math.abs(x - handler.getWorld().getEntityManager().getPlayer().getX()) < seeingDist ||
        Math.abs(y - handler.getWorld().getEntityManager().getPlayer().getY()) < seeingDist) {
            speed = 2.0f;
            moveToward((int) handler.getWorld().getEntityManager().getPlayer().getX(), (int) handler.getWorld().getEntityManager().getPlayer().getY());
            move();
        } else {
            speed = 1.0f;
            moveDirection();
            move();
        }

        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(new Rectangle((int) (22 + x), (int) y + 30, 20, 35))) {
            if ((xMove > 0 && handler.getWorld().getEntityManager().getPlayer().getX() > x)
            || (xMove < 0 && handler.getWorld().getEntityManager().getPlayer().getX() < x)
            || (yMove > 0 && handler.getWorld().getEntityManager().getPlayer().getY() > y)
            || (yMove < 0 && handler.getWorld().getEntityManager().getPlayer().getY() < y))  {
                handler.getWorld().getEntityManager().getPlayer().die();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        System.out.println("You killed a zombie and now you have rAbIeS X|");
    }

    public void moveToward(int x, int y) {
        if (x > this.x) {
            xMove = speed;
        } else if (x < this.x) {
            xMove = -speed;
        }

        if (y > this.y) {
            yMove = speed;
        } else if (y < this.y) {
            yMove = -speed;
        }
    }

    public void moveDirection() {
        xMove = 0;
        yMove = 0;

        if (rand == 0) {
            yMove = speed;
        } else if (rand == 1) {
            yMove = -speed;
        } else if (rand == 2) {
            xMove = speed;
        } else if (rand == 3) {
            xMove = -speed;
        }
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }
}
