package com.oocourse.elevator2;

import com.oocourse.TimableOutput;

public class ElevatorInputTest {

    public static void main(String[] args) {
        TimableOutput.initStartTimestamp();
        ElevatorInput input = new ElevatorInput(System.in);
        while (true) {
            PersonRequest nextInput = input.nextPersonRequest();
            if (nextInput == null) {
                break;
            }
            TimableOutput.println(nextInput);
        }
    }
}