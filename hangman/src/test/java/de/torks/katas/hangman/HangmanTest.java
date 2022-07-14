package de.torks.katas.hangman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanTest {

    @Test
    void testInitialIsEmpty() {

        String word = "foobar";
        Hangman hangman = new Hangman(word);

        assertEquals("______", hangman.current());
    }

    @Test
    void testFittingCharRevealsItself() {

        String word = "foobar";
        Hangman hangman = new Hangman(word);

        assertEquals("_oo___", hangman.guess('o'));
        assertEquals("foo___", hangman.guess('f'));
    }

    @Test
    void testUnfittingCharDoesntReveal() {

        String word = "foobar";
        Hangman hangman = new Hangman(word);

        assertEquals("______", hangman.guess('x'));
    }

    @Test
    void testIgnoresCapitalization() {

        String word = "foobaR";
        Hangman hangman = new Hangman(word);

        assertEquals("___b__", hangman.guess('B'));
        assertEquals("___b_R", hangman.guess('r'));
    }
}
