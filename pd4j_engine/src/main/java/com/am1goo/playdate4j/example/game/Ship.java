package com.am1goo.playdate4j.example.game;

import com.am1goo.playdate4j.sdk.Game;
import com.am1goo.playdate4j.sdk.Graphics;
import com.am1goo.playdate4j.sdk.Sprite;

public class Ship extends Unit {

    private String id;
    private Sprite.LCDSprite sprite;
    private Graphics.LCDBitmap[] frames;

    public Ship(String id) {
        this.id = id;
        this.frames = new Graphics.LCDBitmap[8];
    }

    public String getId() {
        return id;
    }

    public void load() {
        for (Rotation r : Rotation.values()) {
            String bitmapName = id + "_" + r.value;
            String bitmapPath = "images/ships/" + bitmapName;
            frames[r.value] = Graphics.loadBitmap(bitmapPath);
        }
        sprite = Sprite.newSprite();
        sprite.setImage(frames[0], Graphics.LCDBitmapFlip.Unflipped);
        Sprite.addSprite(sprite);
    }

    public void free() {
        for (Graphics.LCDBitmap bitmap : frames) {
            if (bitmap == null)
                continue;

            bitmap.free();
        }
        Sprite.removeSprite(sprite);
        sprite.free();
        sprite = null;
    }

    public void addInput(int xDir, int yDir) {
        float deltaTime = Game.getDeltaTime();
        float dx = 100 * xDir * deltaTime;
        float dy = 100 * yDir * deltaTime;
        position().add(dx, dy);
    }

    @Override
    public void moveTo(float x, float y) {
        sprite.moveTo(x, y);
    }

    public enum Rotation {
        N(0),
        NE(1),
        E(2),
        SE(3),
        S(4),
        SW(5),
        W(6),
        NW(7);

        final int value;

        Rotation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
