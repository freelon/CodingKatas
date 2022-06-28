package de.torks.katas.bowling.implementation;

import de.torks.katas.bowling.core.Frame;
import de.torks.katas.bowling.core.Game;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {

    private static final int GAME_LENGTH = 10;
    private final List<StandardFrame> frames = new ArrayList<>(GAME_LENGTH);
    private StandardFrame current;

    @Override
    public void addRoll(int pins) {

        initializeFrame();
        addRollToFrame(pins);
        //updatePastFrame();
        finishFrameIfFull();
    }

    private void finishFrameIfFull() {

        if (current.isFull()) {

            frames.add(current);
            current = null;
        }
    }

    private void addRollToFrame(int pins) {

        current = current.withNextRoll(pins);
    }

    private void initializeFrame() {

        if (current == null) {

            current = new StandardFrame(List.of(), 0);
        }
    }

    @Override
    public List<Frame> frames() {

        return List.copyOf(frames);
    }

    @Override
    public int totalScore() {

        int currentFrameScore = current == null ? 0 : current.score();
        return frames.stream().mapToInt(Frame::score).sum() + currentFrameScore;
    }

    @Override
    public boolean isOver() {

        return frames.size() == GAME_LENGTH;
    }
}
