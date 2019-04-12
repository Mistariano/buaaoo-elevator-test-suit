//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.oocourse.elevator3.exception;

public abstract class PersonRequestException extends Exception {
    private final String original;

    PersonRequestException(String original) {
        super(String.format("Person request parse failed! - \"%s\"", original));
        this.original = original;
    }

    public String getOriginal() {
        return this.original;
    }
}
