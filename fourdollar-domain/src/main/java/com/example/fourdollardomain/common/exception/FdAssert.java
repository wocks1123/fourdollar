package com.example.fourdollardomain.common.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FdAssert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new FdIllegalArgumentException(message);
        }
    }

    public static void hasText(String str, String message) {
        if (str == null || str.trim().isEmpty()) {
            throw new FdIllegalArgumentException(message);
        }
    }

    public static void notEmpty(String str, String message) {
        if (str == null || str.isEmpty()) {
            throw new FdIllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new FdIllegalArgumentException(message);
        }
    }

    public static void notEmpty(List<?> list, String message) {
        if (list == null || list.isEmpty()) {
            throw new FdIllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new FdIllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new FdIllegalArgumentException(message);
        }
    }

}
