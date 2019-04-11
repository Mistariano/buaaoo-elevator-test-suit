package com.oocourse.elevator2;

import com.oocourse.timed.TimedMiddleware;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class ElevatorInput implements Closeable {
    private static final InputStream DEFAULT_INPUT_STREAM;

    static {
        DEFAULT_INPUT_STREAM = System.in;
    }

    private final Scanner scanner;
    private final HashSet<Integer> existedPersonId;

    public ElevatorInput(InputStream inputStream) {
        TimedMiddleware middleware = new TimedMiddleware(inputStream);
        this.scanner = new Scanner(middleware.getParsedInputStream());
        this.existedPersonId = new HashSet();
    }

    public ElevatorInput() {
        this(DEFAULT_INPUT_STREAM);
    }

    @Override
    public void close() throws IOException {
        this.scanner.close();
    }

    public PersonRequest nextPersonRequest() {
        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            try {
                PersonRequest request = PersonRequest.parse(line);
                if (this.existedPersonId.contains(request.getPersonId())) {
                    throw new Error(line);
                }

                this.existedPersonId.add(request.getPersonId());
                return request;
            } catch (Error var3) {
                var3.printStackTrace(System.err);
            }
        }

        return null;
    }
}

