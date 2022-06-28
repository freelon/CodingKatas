package de.torks.katas.bowling.implementation;

import java.util.ArrayList;
import java.util.List;

public record FinalFrame(List<Integer> pinsRolled) implements ModifiableFrame {

    private static final int FRAME_SIZE = 3;

    public FinalFrame withNextRoll(int pins) {

        checkAnotherRollCanBeAdded();

        List<Integer> newPinsRolled = new ArrayList<>(pinsRolled);
        newPinsRolled.add(pins);

        return new FinalFrame(List.copyOf(newPinsRolled));
    }

    @Override
    public int firstRollPins() {

        if (pinsRolled.isEmpty())
            throw new IllegalStateException("No roll has been registered yet");

        return pinsRolled.get(0);
    }

    @Override
    public int firstTwoRollPins() {

        if (pinsRolled.isEmpty())
            return 0;
        if (pinsRolled.size() == 1)
            return pinsRolled.get(0);
        else
            return pinsRolled.get(0) + pinsRolled.get(1);
    }

    @Override
    public boolean isFull() {

        return pinsRolled.size() == FRAME_SIZE || firstTwoRolledAndNotASpare();
    }

    private boolean firstTwoRolledAndNotASpare() {

        if (pinsRolled.size() < 2)
            return false;

        int sum = pinsRolled.get(0) + pinsRolled.get(1);
        return sum < 10;
    }

    private void checkAnotherRollCanBeAdded() {

        if (isFull())
            throw new IllegalStateException("The roll limit per frame is reached.");
    }

    @Override
    public int score() {

        return pinsRolled.stream().mapToInt(i -> i).sum();
    }
}

