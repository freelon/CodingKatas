package de.torks.katas.bowling;

import de.torks.katas.bowling.core.Game;
import de.torks.katas.bowling.implementation.FrameFactory;
import de.torks.katas.bowling.implementation.GameImpl;

public class GameFactory {

    static Game createGame() {

        return new GameImpl(new FrameFactory());
    }
}
