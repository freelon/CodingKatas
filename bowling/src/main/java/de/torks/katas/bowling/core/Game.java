package de.torks.katas.bowling.core;

import java.util.List;

public interface Game {

    void addRoll(int pins);

    List<Frame> frames();

    int totalScore();

    boolean isOver();
}
