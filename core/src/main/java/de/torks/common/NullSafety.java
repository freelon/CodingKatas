package de.torks.common;

import java.util.function.Supplier;

public class NullSafety {

    private NullSafety() {
    }

    public static <T> T getOrNull(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException ignored) {
            return null;
        }
    }

    public static <T> T getOrDefault(Supplier<T> supplier, T defaultValue) {
        try {
            return supplier.get();
        } catch (NullPointerException ignored) {
            return defaultValue;
        }
    }
}
