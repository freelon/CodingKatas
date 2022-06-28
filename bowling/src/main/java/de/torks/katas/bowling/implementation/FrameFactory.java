package de.torks.katas.bowling.implementation;

import java.util.List;

public class FrameFactory {

    ModifiableFrame createStandardFrame() {

        return new StandardFrame(List.of(), 0);
    }

    ModifiableFrame createFinalFrame() {

        return new FinalFrame(List.of());
    }
}
