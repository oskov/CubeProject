package com.warlodya.game;

import com.badlogic.gdx.Game;
import com.warlodya.screens.GameScreen;

public class GameClass extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
