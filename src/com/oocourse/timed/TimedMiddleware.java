package com.oocourse.timed;

import com.oocourse.TimableOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimedMiddleware {
    private static final String TIMED_PATTERN_STRING
        = "^\\[([0-9]+[.][0-9]*)](.*)$";
    private PipedInputStream inputStream;

    public TimedMiddleware(InputStream stream) {
        List<TimedInputPair> pairs = getInputPairs(stream);
        PipedOutputStream outputStream = new PipedOutputStream();
        try {
            inputStream = new PipedInputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TimedWriter writer = new TimedWriter(pairs, outputStream);
        TimableOutput.initStartTimestamp();
        new Thread(writer).start();
    }

    private static List<TimedInputPair> getInputPairs(InputStream stream) {
        List<TimedInputPair> pairs = new LinkedList<>();
        Scanner scanner = new Scanner(stream);
        Pattern pattern = Pattern.compile(TIMED_PATTERN_STRING);
        while (scanner.hasNext()) {
            String next = scanner.next();
            Matcher matcher = pattern.matcher(next);
            if (matcher.find()) {
                if (!matcher.group(0).equals(next)) {
                    throw new Error(
                        String.format("input %s not matched", next));
                }
                double time = Double.valueOf(matcher.group(1));
                String inputLine = matcher.group(2);
                pairs.add(new TimedInputPair(inputLine, time));
            } else {
                throw new Error(
                    String.format("input %s not matched", next));
            }
        }
        return pairs;
    }

    public InputStream getParsedInputStream() {
        return inputStream;
    }
}
