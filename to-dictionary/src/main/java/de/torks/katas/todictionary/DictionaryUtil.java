package de.torks.katas.todictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DictionaryUtil {

    public static Map<String, String> toDictionary(String input) {

        return Arrays.stream(input.split(";"))
                .map(DictionaryUtil::parseKeyValuePair)
                .reduce(new HashMap<>(), (acc, it) -> {
                    var result = new HashMap<>(acc);
                    result.putAll(it);
                    return result;
                });
    }

    private static Map<String, String> parseKeyValuePair(String input) {

        int splitIndex = input.indexOf("=");
        if (splitIndex >= 0) {

            String left = input.substring(0, splitIndex);
            if (left.isEmpty())
                throw new IllegalArgumentException("'%s' doesn't contain a key".formatted(input));
            String right = input.substring(splitIndex + 1);
            return Map.of(left, right);
        }

        return Map.of();
    }
}
