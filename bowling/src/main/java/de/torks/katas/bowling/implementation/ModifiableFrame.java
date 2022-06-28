package de.torks.katas.bowling.implementation;

import de.torks.katas.bowling.core.Frame;

public interface ModifiableFrame extends Frame {

    StandardFrame withNextRoll(int pins);

    int firstRollPins();

    int allRollPins();

    boolean isFull();
}
