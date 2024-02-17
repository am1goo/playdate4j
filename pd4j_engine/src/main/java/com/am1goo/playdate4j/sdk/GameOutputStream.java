package com.am1goo.playdate4j.sdk;

import java.io.IOException;
import java.io.OutputStream;

public class GameOutputStream extends OutputStream {

    private final StringBuilder sb  = new StringBuilder();

    private final boolean error;

    public GameOutputStream(boolean error) {
        this.error = error;
    }

    @Override
    public void write(int b) throws IOException {
        sb.append((char)b);
    }

    @Override
    public void flush() {
        String str = sb.toString();
        sb.setLength(0);
        if (error)
            Sys.logError(str);
        else
            Sys.log(str);
    }
}
