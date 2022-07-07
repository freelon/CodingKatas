package de.torks.katas.todictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DictionaryFromStringTest {

    @Test
    void testEmpty() {

        assertTrue(DictionaryUtil.toDictionary("").isEmpty());
    }
}
