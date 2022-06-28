package de.torks.katas.bowling;

import de.torks.katas.bowling.core.Game;
import de.torks.katas.bowling.implementation.GameImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
