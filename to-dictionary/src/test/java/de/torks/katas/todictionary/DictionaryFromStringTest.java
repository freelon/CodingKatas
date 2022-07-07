package de.torks.katas.todictionary;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryFromStringTest {

    @Test
    void testEmpty() {

        assertTrue(DictionaryUtil.toDictionary("").isEmpty());
    }

    @Test
    void testSingle() {

        assertEquals(Map.of("a", "=1"), DictionaryUtil.toDictionary("a==1"));
    }

    @Test
    void testEmptyValue() {

        assertEquals(Map.of("a", ""), DictionaryUtil.toDictionary("a="));
    }

    @Test
    void testEmptyKey() {

        assertThrows(IllegalArgumentException.class, () -> DictionaryUtil.toDictionary("=123"));
    }
}
