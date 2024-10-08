package com.javagame.tilegame.worlds;

import com.javagame.tilegame.Handler;
import com.javagame.tilegame.entities.EntityManager;
import com.javagame.tilegame.entities.creatures.Player;
import com.javagame.tilegame.entities.creatures.Zombie;
import com.javagame.tilegame.entities.statics.Rock;
import com.javagame.tilegame.entities.statics.Tree;
import com.javagame.tilegame.items.ItemManager;
import com.javagame.tilegame.states.GameState;
import com.javagame.tilegame.tiles.Tile;
import com.javagame.tilegame.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    //Entities
    private EntityManager entityManager;
    //Item
    private ItemManager itemManager;

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);

        if (handler.getWorld() == GameState.world1) {
            world1Entities();
        } else if (handler.getWorld() == GameState.world2) {
            world2Entities();
        } else if (handler.getWorld() == GameState.world3) {
            world3Entities();
        }

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    private void world1Entities() {
        entityManager.addEntity(new Tree(handler, 132, 350));
        entityManager.addEntity(new Rock(handler, 132, 450));
        entityManager.addEntity(new Rock(handler, 350, 300));
        entityManager.addEntity(new Rock(handler, 450, 450));
        entityManager.addEntity(new Tree(handler, 625, 605));
        entityManager.addEntity(new Tree(handler, 100, 650));
        entityManager.addEntity(new Rock(handler, 100, 850));
        entityManager.addEntity(new Zombie(handler, 200, 350));
        entityManager.addEntity(new Zombie(handler, 400, 450));
        entityManager.addEntity(new Zombie(handler, 400, 750));
        entityManager.addEntity(new Zombie(handler, 700, 850));
    }

    private void world2Entities() {
        entityManager.addEntity(new Rock(handler, 350, 300));
        entityManager.addEntity(new Tree(handler, 500, 900));
        entityManager.addEntity(new Zombie(handler, 200, 350));
        entityManager.addEntity(new Zombie(handler, 400, 450));
        entityManager.addEntity(new Zombie(handler, 400, 750));
        entityManager.addEntity(new Zombie(handler, 700, 850));
        entityManager.addEntity(new Zombie(handler, 900, 1000));
    }

    private void world3Entities() {
        entityManager.addEntity(new Tree(handler, 132, 350));
        entityManager.addEntity(new Rock(handler, 132, 450));
        entityManager.addEntity(new Rock(handler, 350, 300));
        entityManager.addEntity(new Zombie(handler, 200, 350));
        entityManager.addEntity(new Zombie(handler, 400, 450));
        entityManager.addEntity(new Zombie(handler, 400, 750));
        entityManager.addEntity(new Zombie(handler, 700, 850));
        entityManager.addEntity(new Zombie(handler, 900, 1000));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
