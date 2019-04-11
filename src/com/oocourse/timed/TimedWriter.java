package com.oocourse.timed;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class TimedWriter implements Runnable {
    private final List<TimedInputPair> pairs;
    private final OutputStream stream;

    private double curTime;

    TimedWriter(List<TimedInputPair> pairs, OutputStream stream) {
        this.pairs = pairs;
        this.stream = stream;
        curTime = 0.;
    }

    @Override
    public void run() {
        for (TimedInputPair pair :
            pairs) {
            double time = pair.getTime();
            double delta = time - curTime;
            if (delta < 0) {
                throw new Error("delta < 0");
            }
            try {
                Thread.sleep(Math.round(delta * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            try {
                stream.write(pair.getInputLine().getBytes());
                stream.write('\n');
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            curTime = time;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
