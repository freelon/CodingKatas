package de.torks.katas.todictionary;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DictionaryUtil {

    public static Map<String, String> toDictionary(String input) {

        return Arrays.stream(input.split(";"))
                .map(DictionaryUtil::parseKeyValuePair)
                .flatMap(Optional::stream)
                .collect(Collectors.toUnmodifiableMap(Pair::key, Pair::value));
    }

    private static Optional<Pair> parseKeyValuePair(String input) {

        if (input.isEmpty())
            return Optional.empty();

        int splitIndex = input.indexOf("=");
        if (splitIndex < 0)
            throw new IllegalArgumentException("'%s' doesn't contain to '=' separator".formatted(input));

        String left = input.substring(0, splitIndex);
        if (left.isEmpty())
            throw new IllegalArgumentException("'%s' doesn't contain a key".formatted(input));
        String right = input.substring(splitIndex + 1);
        return Optional.of(new Pair(left, right));
    }

    record Pair(String key, String value) {
    }
}
