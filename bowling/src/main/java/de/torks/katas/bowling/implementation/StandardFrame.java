package de.torks.katas.bowling.implementation;

import java.util.ArrayList;
import java.util.List;

public record StandardFrame(List<Integer> pinsRolled, int scoreFromNext) implements ModifiableFrame {

    private static final int FRAME_SIZE = 2;

    public StandardFrame withNextRoll(int pins) {

        checkAnotherRollCanBeAdded();

        List<Integer> newPinsRolled = new ArrayList<>(pinsRolled);
        newPinsRolled.add(pins);

        return new StandardFrame(List.copyOf(newPinsRolled), 0);
    }

    @Override
    public int firstRollPins() {

        if (pinsRolled.isEmpty())
            throw new IllegalStateException("No roll has been registered yet");

        return pinsRolled.get(0);
    }

    @Override
    public int allRollPins() {

        checkFrameIsFullWhenReadingFullScore();

        return pinsRolled.stream().mapToInt(i -> i).sum();
    }

    @Override
    public boolean isFull() {

        return isStrike() || pinsRolled.size() == FRAME_SIZE;
    }

    private void checkFrameIsFullWhenReadingFullScore() {

        if (!isFull())
            throw new IllegalStateException("The frame has only %d rolls and is not a strike, so the full score cannot be computed".formatted(pinsRolled.size()));
    }

    private void checkAnotherRollCanBeAdded() {

        if (pinsRolled.size() >= FRAME_SIZE)
            throw new IllegalStateException("The roll limit per frame is reached.");

        if (isStrike())
            throw new IllegalStateException("No Additional roll after a strike is possible.");
    }

    public StandardFrame withScoreFromNext(ModifiableFrame next) {

        checkFrameIsFullBeforeAddingScoreFromNext();

        int additionalScore = computeAdditionalScoreFromNext(next);
        return new StandardFrame(List.copyOf(pinsRolled), additionalScore);
    }

    private int computeAdditionalScoreFromNext(ModifiableFrame next) {

        if (isStrike())
            return next.allRollPins();
        else if (isSpare())
            return next.firstRollPins();
        else
            return 0;
    }

    private boolean isSpare() {

        return pinsRolled.size() == 2 && pinsRolled.stream().mapToInt(i -> i).sum() == 10;
    }

    private boolean isStrike() {

        return pinsRolled.size() == 1 && pinsRolled.get(0) == 10;
    }

    private void checkFrameIsFullBeforeAddingScoreFromNext() {

        if (!isFull())
            throw new IllegalStateException("Can only calculate add score from next frame if this frame is full, but it has %d instead of %d rolls and is no stirke.".formatted(pinsRolled.size(), FRAME_SIZE));
    }

    @Override
    public int score() {

        return pinsRolled.stream().mapToInt(i -> i).sum() + scoreFromNext;
    }
}

