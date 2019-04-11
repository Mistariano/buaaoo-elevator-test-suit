package com.oocourse;


import java.io.PrintStream;

public abstract class TimableOutput {
    private static final double SECOND_TO_MILLISECOND = 1000.0D;
    private static final PrintStream DEFAULT_PRINT_STREAM;
    private static long startTimestamp = 0L;

    static {
        DEFAULT_PRINT_STREAM = System.out;
    }

    public TimableOutput() {
    }

    public static boolean initStartTimestamp() {
        startTimestamp = System.currentTimeMillis();
        return true;
    }

    private static long getRelativeTimestamp(long timestamp) {
        return timestamp - startTimestamp;
    }

    private static long getRelativeTimestamp() {
        return getRelativeTimestamp(System.currentTimeMillis());
    }

    public static long println(Object obj, PrintStream stream) {
        TimableOutput.ObjectWithTimestamp value =
            new TimableOutput.ObjectWithTimestamp(obj);
        stream.println(value.toEncryptedString());
        return value.getTimestamp();
    }

    public static long println(Object obj) {
        return println(obj, DEFAULT_PRINT_STREAM);
    }

    public static long println(int i, PrintStream stream) {
        return println((Object) String.valueOf(i), stream);
    }

    public static long println(int i) {
        return println(i, DEFAULT_PRINT_STREAM);
    }

    public static long println(boolean b, PrintStream stream) {
        return println((Object) String.valueOf(b), stream);
    }

    public static long println(boolean b) {
        return println(b, DEFAULT_PRINT_STREAM);
    }

    public static long println(char c, PrintStream stream) {
        return println((Object) String.valueOf(c), stream);
    }

    public static long println(char c) {
        return println(c, DEFAULT_PRINT_STREAM);
    }

    public static long println(long l, PrintStream stream) {
        return println((Object) String.valueOf(l), stream);
    }

    public static long println(long l) {
        return println(l, DEFAULT_PRINT_STREAM);
    }

    public static long println(float f, PrintStream stream) {
        return println((Object) String.valueOf(f), stream);
    }

    public static long println(float f) {
        return println(f, DEFAULT_PRINT_STREAM);
    }

    public static long println(char[] s, PrintStream stream) {
        return println((Object) String.valueOf(s), stream);
    }

    public static long println(char[] s) {
        return println(s, DEFAULT_PRINT_STREAM);
    }

    public static long println(double d, PrintStream stream) {
        return println((Object) String.valueOf(d), stream);
    }

    public static long println(double d) {
        return println(d, DEFAULT_PRINT_STREAM);
    }

    private static class ObjectWithTimestamp {
        private final long timestamp;
        private final Object object;

        ObjectWithTimestamp(long timestamp, Object object) {
            this.timestamp = timestamp;
            this.object = object;
        }

        ObjectWithTimestamp(Object object) {
            this(System.currentTimeMillis(), object);
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public long getRelativeTimestamp() {
            return TimableOutput.getRelativeTimestamp(this.getTimestamp());
        }

        public double getRelativeSecondTimestamp() {
            return (double) this.getRelativeTimestamp() / 1000.0D;
        }

        public Object getObject() {
            return this.object;
        }

        @Override
        public String toString() {
            return String.format("[%9.4f]%s",
                this.getRelativeSecondTimestamp(), this.getObject().toString());
        }

        public String toEncryptedString() {
            return this.toString();
        }
    }
}

