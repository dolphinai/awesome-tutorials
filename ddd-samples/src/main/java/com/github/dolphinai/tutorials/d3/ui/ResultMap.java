package com.github.dolphinai.tutorials.d3.ui;

import java.util.HashMap;
import java.util.Objects;

public final class ResultMap extends HashMap<String, Object> {

    private ResultMap(String returnCode, String returnMessage) {
        this.code(returnCode).message(returnMessage);
    }

    public ResultMap code(String returnCode) {
        return with("code", returnCode);
    }

    public ResultMap message(String returnMessage) {
        return with("message", returnMessage);
    }

    public ResultMap body(Object returnBody) {
        return with("body", returnBody);
    }

    public ResultMap with(String name, Object value) {
        Objects.requireNonNull(name);
        this.put(name, value);
        return this;
    }

    public static ResultMap success() {
        return new ResultMap("000", "OK");
    }

    public static ResultMap fail() {
        return fail("-1");
    }

    public static ResultMap fail(String returnCode) {
        return new ResultMap(returnCode, "Failed");
    }
}
