package de.torks.katas.todictionary;

import java.util.Map;

public class DictionaryUtil {

    public static Map<String, String> toDictionary(String input) {

        int splitIndex = input.indexOf("=");
        if (splitIndex >= 0) {

            String left = input.substring(0, splitIndex);
            String right = input.substring(splitIndex + 1);
            return Map.of(left, right);
        }

        return Map.of();
    }
}
