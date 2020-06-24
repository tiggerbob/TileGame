package com.javagame.tilegame.states;

import com.javagame.tilegame.Game;
import com.javagame.tilegame.Handler;
import com.javagame.tilegame.gfx.Assets;
import com.javagame.tilegame.ui.ClickListener;
import com.javagame.tilegame.ui.UIImageButton;
import com.javagame.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(250, 200, 128, 64, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
     }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
