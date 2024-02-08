package com.am1goo.playdate4j;

import com.am1goo.playdate4j.sdk.Game;
import com.am1goo.playdate4j.engine.JGameEngine;
import com.am1goo.playdate4j.example.ExampleGameCycle;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game.engine(JGameEngine.class);
        Game.create(ExampleGameCycle.class);
        Game.init();
        for (int i = 0; i < 10; ++i) {
            Game.loop();
        }
        Game.shutdown();
    }
}