package de.torks.katas.bowling.implementation;

import de.torks.katas.bowling.core.Frame;
import de.torks.katas.bowling.core.Game;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {

    private static final int GAME_LENGTH = 10;
    private final FrameFactory frameFactory;
    private final List<ModifiableFrame> frames = new ArrayList<>(GAME_LENGTH);
    private ModifiableFrame current;

    public GameImpl(FrameFactory frameFactory) {

        this.frameFactory = frameFactory;
    }

    @Override
    public void addRoll(int pins) {

        checkGameNotOver();

        initializeFrame();
        addRollToFrame(pins);
        updatePastFrame();
        finishFrameIfFull();
    }

    private void checkGameNotOver() {

        if (isOver())
            throw new IllegalStateException("Cannot add rolls to a game that is over.");
    }

    private void updatePastFrame() {

        if (!frames.isEmpty()) {

            ModifiableFrame lastFrame = frames.remove(frames.size() - 1);
            StandardFrame updatedLastFrame = lastFrame.withScoreFromNext(current);
            frames.add(updatedLastFrame);
        }
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

            if (frames.size() < GAME_LENGTH - 1)
                current = frameFactory.createStandardFrame();
            else if (frames.size() == GAME_LENGTH - 1)
                current = frameFactory.createFinalFrame();
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
