package de.torks.katas.bowling;

import de.torks.katas.bowling.core.Game;
import de.torks.katas.bowling.implementation.GameImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void shortGame() {

        Game game = new GameImpl();

        for (int i = 0; i < 20; i++) {

            game.addRoll(1);
        }

        assertEquals(20, game.totalScore());
        assertEquals(10, game.frames().size());

        assertThrows(IllegalStateException.class, () -> game.addRoll(0));
    }

    @Test
    void longGame() {

        Game game = new GameImpl();

        for (int i = 0; i < 19; i++) {

            game.addRoll(1);
        }
        game.addRoll(9);

        assertEquals(28, game.totalScore());
        assertEquals(9, game.frames().size());

        game.addRoll(1);

        assertEquals(29, game.totalScore());
        assertEquals(10, game.frames().size());

        assertThrows(IllegalStateException.class, () -> game.addRoll(0));
    }

    @Test
    void completeGame() {

        List<Integer> rolls = List.of(1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6);
        List<Integer> scoreAfterRoll = List.of(1, 5, 9, 14, 20, 24, 34, 39, 59, 59, 61, 68, 71, 83, 87, 107, 111, 127, 133);

        Game game = new GameImpl();
        for (int i = 0; i < rolls.size(); i++) {

            assertFalse(game.isOver());
            game.addRoll(rolls.get(i));
            assertEquals(scoreAfterRoll.get(i), game.totalScore());
        }

        assertTrue(game.isOver());
    }
}
