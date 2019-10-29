package com.group0562.adventureofpost;

import com.group0562.adventureofpost.model.PuzzleStats;

import java.util.Observable;

public abstract class Puzzles extends Observable {

    public PuzzleStats puzzleStats;
    private boolean puzzleComplete = false;

    public Puzzles(PuzzleStats statsInst) {
        puzzleStats = statsInst;
    }

    // TODO: add pause and resume

    public abstract void checkComplete();

    public void update() {
        checkComplete();
        notifyObservers();

        if (puzzleStats.getTime() == 0 | puzzleComplete) {
            onStop();
        }
    }

    public void onStop() {
        if (!puzzleComplete) {
            puzzleStats.setLives(0);
        }

        // TODO: display live points in phase 2
    }

    public void setPuzzleComplete(boolean b) {
        this.puzzleComplete = b;
    }

    public boolean getPuzzleComplete() {
        return this.puzzleComplete;
    }
}