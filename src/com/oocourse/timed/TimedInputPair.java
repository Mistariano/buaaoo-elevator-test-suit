package com.oocourse.timed;

public class TimedInputPair {
    private String inputLine;
    private double time;

    public TimedInputPair(String inputLine, double time) {
        this.inputLine = inputLine;
        this.time = time;
    }

    public String getInputLine() {
        return inputLine;
    }

    public double getTime() {
        return time;
    }
}
