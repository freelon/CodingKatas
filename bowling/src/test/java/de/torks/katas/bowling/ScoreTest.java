package de.torks.katas.bowling;

import de.torks.katas.bowling.core.Game;
import de.torks.katas.bowling.implementation.GameImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    @Test
    void testInitial() {

        Game game = new GameImpl();
        assertEquals(0, game.totalScore());
    }

    @Test
    void testSingleThrow() {

        Game game = new GameImpl();
        game.addRoll(5);
        assertEquals(5, game.totalScore());
    }

    @Test
    void testTwoThrows() {

        Game game = new GameImpl();
        game.addRoll(5);
        game.addRoll(6);
        assertEquals(11, game.totalScore());
        assertEquals(1, game.frames().size());
    }
}
