package de.torks.katas.hangman;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Hangman {

    private final String word;
    private final Set<Character> guessed = new HashSet<>();

    public Hangman(String word) {

        this.word = word;
    }

    public String current() {
        return reveal();
    }

    public String guess(char character) {
        guessed.add(character);
        return reveal();
    }

    private String reveal() {
        return word.chars()
                .map(this::revealedWord)
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    private int revealedWord(int c) {
        char character = (char) c;
        if (guessed.contains(Character.toLowerCase(character)) || guessed.contains(Character.toUpperCase(character)))
            return c;
        else
            return '_';
    }
}
