package com.am1goo.playdate4j;

import com.am1goo.playdate4j.sdk.Game;
import com.am1goo.playdate4j.example.ExampleGameCycle;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String className = ExampleGameCycle.class.getName();
        Game.create(className);
        Game.init();
        for (int i = 0; i < 10; ++i) {
            Game.loop();
        }
        Game.shutdown();
    }
}