package de.torks.katas.todictionary;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DictionaryFromStringTest {

    @Test
    void testEmpty() {

        assertTrue(DictionaryUtil.toDictionary("").isEmpty());
    }

    @Test
    void testSingle() {

        assertEquals(Map.of("a", "=1"), DictionaryUtil.toDictionary("a==1"));
    }
}
