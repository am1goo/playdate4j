# playdate4j
Java framework to create games on `Playdate` console. \
*ATTENTION: it is an experimental version, I don't sure that you should to use it in production purposes right now.*

## What inside?
- full support of `Playdate API`
- pretty good works in `Playdate Simulator`
- easy-to-use `Localization API` for system languages and any others

## C API support
=100%= `Logging API` \
=100%= `System Menu API` \
=100%= `Time and Date API` \
=100%= `Miscellaneous API` \
=100%= `Audio API` \
=100%= `Display API` \
=100%= `Graphics API` \
=100%= `Video API` \
=100%= `Input API` \
=100%= `Device Auto Lock API` \
=100%= `System Sounds API` \
=100%= `Sprites API` \
=100%= `Sprite Collisions API` \
BUT \
=000%= `JSON API` (skipped API, package [com.google.code.gson](https://github.com/google/gson) would be better)

## Let me explain what it looks like:
```java
public class ExampleGameCycle implements GameCycle {

    private Graphics.LCDBitmap playerBitmap;
    private Sprite.LCDSprite player;

    @Override
    public void start() {
        playerBitmap = Graphics.loadBitmap("images/player");
        player = Sprite.newSprite();
        player.setPosition(lcd_columns / 2, lcd_rows / 2);
        player.setImage(playerBitmap, Graphics.LCDBitmapFlip.Unflipped);
        Sprite.addSprite(player);
    }

    @Override
    public void loop() {
        Graphics.clear(Graphics.LCDSolidColor.White);
        int xDir = 0;
        int yDir = 0;
        if (Input.isButton(Input.PDButtons.Up)) {
            yDir--;
        }
        if (Input.isButton(Input.PDButtons.Down)) {
            yDir++;
        }
        if (Input.isButton(Input.PDButtons.Left)) {
            xDir--;
        }
        if (Input.isButton(Input.PDButtons.Right)) {
            xDir++;
        }

        float deltaTime = Game.getDeltaTime();
        float dx = 100 * xDir * deltaTime;
        float dy = 100 * yDir * deltaTime;
        player.moveBy(dx, dy);

        Sprite.updateAndDrawSprites();
    }

    @Override
    public void stop() {
        if (player != null) {
            Sprite.removeSprite(player);
            Sprite.freeSprite(player);
            player = null;
        }
        if (playerBitmap != null) {
            Graphics.freeBitmap(playerBitmap);
            playerBitmap = null;
        }
    }
}
```

## Roadmap
- [X] full `C API` coverage
- [ ] well-automated app creation process
- [ ] using `Java-to-C` code conversion instead of JavaVM on device

## Requirements
- `Playdate SDK` 2.5.x version or higher from [official site](https://play.date/dev/)
- `g++` compiler, like [mingw64](https://github.com/niXman/mingw-builds-binaries/releases)
- `Java Development Kit` 1.6 version or higher

## Contribute
Contribution in any form is very welcome. Bugs, feature requests or feedback can be reported in form of Issues.
