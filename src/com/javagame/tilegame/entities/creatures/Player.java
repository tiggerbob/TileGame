package com.javagame.tilegame.entities.creatures;

import com.javagame.tilegame.Handler;
import com.javagame.tilegame.entities.Entity;
import com.javagame.tilegame.gfx.Animation;
import com.javagame.tilegame.gfx.Assets;
import com.javagame.tilegame.input.KeyManager;
import com.javagame.tilegame.inventory.Inventory;
import com.javagame.tilegame.states.GameState;
import com.javagame.tilegame.states.State;
import com.javagame.tilegame.tiles.Tile;
import com.javagame.tilegame.worlds.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;

public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    private Animation animDownMad, animUpMad, animLeftMad, animRightMad;
    private boolean hasWeapon = false;
    private boolean weaponActive = false;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 23;
        bounds.y = 31;
        bounds.width = 18;
        bounds.height = 32;

        //Animations
        animDown = new Animation(250, Assets.player_down);
        animUp = new Animation(250, Assets.player_up);
        animLeft = new Animation(250, Assets.player_left);
        animRight = new Animation(250, Assets.player_right);

        animDownMad = new Animation(250, Assets.player_down_atk);
        animUpMad = new Animation(250, Assets.player_up_atk);
        animLeftMad = new Animation(250, Assets.player_left_atk);
        animRightMad = new Animation(250, Assets.player_right_atk);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();

        animDownMad.tick();
        animUpMad.tick();
        animLeftMad.tick();
        animRightMad.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();
        checkWeapon();

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT))
            weaponActive = !weaponActive;

        moveNextWorld();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        if (inventory.isActive())
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if (handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                if ((!e.isCreature() && weaponActive) || (!weaponActive && e.isCreature()))
                    return;
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die() {
//        System.out.println("You lose :(");
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (inventory.isActive())
            return;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void postRender(Graphics g) {
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (hasWeapon && weaponActive){
            if (xMove < 0) {
                return animLeftMad.getCurrentFrame();
            } else if (xMove > 0) {
                return animRightMad.getCurrentFrame();
            } else if (yMove < 0) {
                return animUpMad.getCurrentFrame();
            } else {
                return animDownMad.getCurrentFrame();
            }
        } else {
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

    public Inventory getInventory() {
        return inventory;
    }

    private void checkWeapon() {
        if (inventory.getInventoryItems().size() != 0) {
            if (inventory.getInventoryItems().get(inventory.getSelectedItem()).getName().equalsIgnoreCase("Wood")) {
                hasWeapon = true;
            }
        }
    }

    public boolean isWeaponActive() {
        return weaponActive;
    }

    public void setWeaponActive(boolean weaponActive) {
        this.weaponActive = weaponActive;
    }

    public boolean isCreature() {
        return false;
    }

    public void moveNextWorld() {
        if (getCollisionBounds(0f, 0f).intersects(18 * Tile.TILEWIDTH, 18 * Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT)) {
            if (handler.getWorld() == GameState.world1) {
                handler.setWorld(GameState.world2);
            } else if (handler.getWorld() == GameState.world2) {
                handler.setWorld(GameState.world3);
            } else {
                handler.setWorld(GameState.world1);
            }
        }
    }
}
