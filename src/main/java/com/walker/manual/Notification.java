package com.walker.manual;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 领域类Notification收集错误信息
 * </p>
 *
 * @author mu qin
 * @date 2021/1/26
 */
public class Notification {

    private final List<String> errors = new ArrayList<>();

    public void addError(final String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return errors.toString();
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
