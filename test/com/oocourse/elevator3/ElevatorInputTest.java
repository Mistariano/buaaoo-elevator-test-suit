package com.oocourse.elevator3;

import com.oocourse.TimableOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class ElevatorInputTest {
    public static void main(String[] args) {
        TimableOutput.initStartTimestamp();
        com.oocourse.elevator3.ElevatorInput input =
            new ElevatorInput(System.in);
        while (true) {
            PersonRequest nextInput = input.nextPersonRequest();
            if (nextInput == null) {
                break;
            }
            TimableOutput.println(nextInput);
        }
    }

    @Test
    public void floorRangeTest() {
        TimableOutput.initStartTimestamp();
        StringBuilder builder = new StringBuilder();
        builder.append("[0.0]1-FROM-1-TO--3\n");
        builder.append("[0.0]2-FROM--3-TO-1\n");
        builder.append("[0.0]3-FROM-1-TO-20\n");
        builder.append("[0.0]4-FROM-20-TO-1\n");
        com.oocourse.elevator3.ElevatorInput input =
            new ElevatorInput(new ByteArrayInputStream(
                builder.toString().getBytes()
            ));
        while (true) {
            PersonRequest nextInput = input.nextPersonRequest();
            if (nextInput == null) {
                break;
            }
        }
    }
}