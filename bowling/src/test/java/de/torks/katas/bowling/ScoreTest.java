package de.torks.katas.bowling;

import de.torks.katas.bowling.core.Game;
import de.torks.katas.bowling.implementation.FinalFrame;
import de.torks.katas.bowling.implementation.GameImpl;
import de.torks.katas.bowling.implementation.ModifiableFrame;
import de.torks.katas.bowling.implementation.StandardFrame;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void testThreeThrows() {

        Game game = new GameImpl();
        game.addRoll(5);
        game.addRoll(6);
        game.addRoll(7);
        assertEquals(18, game.totalScore());
        assertEquals(1, game.frames().size());
    }

    @Test
    void testNextFrameScoreWithStrike() {

        StandardFrame frame = new StandardFrame(List.of(10), 0);
        ModifiableFrame nextFrame = new StandardFrame(List.of(4, 4), 0);
        int scoreFromNextFrame = frame.withScoreFromNext(nextFrame).scoreFromNext();
        assertEquals(8, scoreFromNextFrame);
    }

    @Test
    void testNextFrameScoreWithSpare() {

        StandardFrame frame = new StandardFrame(List.of(9, 1), 0);
        ModifiableFrame nextFrame = new StandardFrame(List.of(4, 4), 0);
        int scoreFromNextFrame = frame.withScoreFromNext(nextFrame).scoreFromNext();
        assertEquals(4, scoreFromNextFrame);
    }

    @Test
    void testNoNextFrameScore() {

        StandardFrame frame = new StandardFrame(List.of(9, 0), 0);
        ModifiableFrame nextFrame = new StandardFrame(List.of(4, 4), 0);
        int scoreFromNextFrame = frame.withScoreFromNext(nextFrame).scoreFromNext();
        assertEquals(0, scoreFromNextFrame);
    }

    @Test
    void testNextFrameScoreTwoOfStandardFrame() {

        ModifiableFrame nextFrame = new StandardFrame(List.of(4, 4), 0);
        assertEquals(8, nextFrame.firstTwoRollPins());
    }

    @Test
    void testNextFrameScoreSingleOfStandardFrame() {

        ModifiableFrame nextFrame = new StandardFrame(List.of(4, 4), 0);
        assertEquals(4, nextFrame.firstRollPins());
    }

    @Test
    void testNextFrameScoreTwoOfFullFinalFrame() {

        ModifiableFrame nextFrame = new FinalFrame(List.of(4, 6, 2));
        assertEquals(10, nextFrame.firstTwoRollPins());
    }

    @Test
    void testNextFrameScoreTwoOfFinalFrame() {

        ModifiableFrame nextFrame = new FinalFrame(List.of(4, 4));
        assertEquals(8, nextFrame.firstTwoRollPins());
    }

    @Test
    void testNextFrameScoreSingleOfSFinalFrame() {

        ModifiableFrame nextFrame = new FinalFrame(List.of(4, 4));
        assertEquals(4, nextFrame.firstRollPins());
    }
}
