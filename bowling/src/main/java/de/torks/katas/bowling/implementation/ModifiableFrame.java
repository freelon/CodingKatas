package de.torks.katas.bowling.implementation;

import de.torks.katas.bowling.core.Frame;

public interface ModifiableFrame extends Frame {

    ModifiableFrame withNextRoll(int pins);

    int firstRollPins();

    int firstTwoRollPins();

    boolean isFull();

    default StandardFrame withScoreFromNext(ModifiableFrame next) {

        throw new UnsupportedOperationException("not implemented");
    }
}
