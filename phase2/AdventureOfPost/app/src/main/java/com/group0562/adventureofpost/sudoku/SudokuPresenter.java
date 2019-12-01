package com.group0562.adventureofpost.sudoku;

import android.content.Context;

import java.io.InputStream;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import com.group0562.adventureofpost.database.DatabaseHelper;

public class SudokuPresenter {

    private Board gameBoard;
    private SudokuView view;
    private SudokuStats gameStats;

    private int currRow = -1;
    private int currCol = -1;

    public SudokuPresenter(SudokuView view, SudokuStats gameStats, int gridSize, String difficulty, String savedGameState) {
        this.gameStats = gameStats;
        this.view = view;

        if(savedGameState.equals("")){
            int[][] parsedBoard = getRandomPuzzle(view.getPresetBoardFile(difficulty, gridSize), gridSize);
            this.gameBoard = new Board(parsedBoard, gridSize, gridSize);

        } else {
            int[][] savedBoard = getPuzzleFromString(savedGameState, gridSize);
            int[][] lockedBoard = getLockedFromString(savedGameState, gridSize);
            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    System.out.println(lockedBoard[row][col]);
                }
            }
            this.gameBoard = new Board(savedBoard, lockedBoard, gridSize, gridSize);
        }

    }

    /**
     * Randomly selects a puzzle from the puzzles
     *
     * @return a 2-D integer array puzzle.
     */

    public void saveBoard(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        db.insertSudokuState(gameBoard.getBoardData());
    }

    private int[][] getLockedFromString(String gameState, int gridSize){
        int[][] locked = new int[gridSize][gridSize];
        String lockedString = gameState.substring(gridSize*gridSize);
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                locked[row][col] = Character.getNumericValue(lockedString.charAt(row*gridSize + col));
            }
        }
        return locked;
    }

    private int[][] getPuzzleFromString(String gameState, int gridSize){
        int[][] puzzle = new int[gridSize][gridSize];
        String puzzleString = gameState.substring(0, gridSize*gridSize);
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                puzzle[row][col] = Character.getNumericValue(puzzleString.charAt(row*gridSize + col));
            }
        }
        return puzzle;
    }
    private int[][] getRandomPuzzle(InputStream file, int gridSize) {
        // Read file
        Random rand = new Random();
        Scanner scanner = new Scanner(file);

        // Randomly select a line from the puzzles
        String result = scanner.nextLine();
        int lineNum = rand.nextInt(10);
        for (int i = 0; i < lineNum; i++) {
            result = scanner.nextLine();
        }

        // Convert string to 2D int array
        int[][] puzzle = new int[gridSize][gridSize];
        int index = 1;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                puzzle[row][col] = Integer.parseInt(result.split("")[index]);
                index++;
            }
        }

        return puzzle;
    }

    public void addObserver(Observer observer) {
        gameStats.addObserver(observer);
        gameBoard.addObserver(observer);
    }

    /**
     * This method returns the state of the board/game as a String, so that the state of the game
     * can be stored in the database.
     */
    private String getBoardString() {
        return gameBoard.getBoardData();
    }

    /* Getter and setters for presenter class. */
    public int getCurrCol() {
        return currCol;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    /* Methods for UI to access the Board class without jumping architectural layers. */
    public void removeNum() {
        gameBoard.insertNum(currRow, currCol, 0);
    }

    public boolean addNum(int input) {
        boolean insertSuccess = gameBoard.insertNum(currRow, currCol, input);
        if (insertSuccess && gameBoard.checkFull()) {
            view.onGameComplete();
        }
        return insertSuccess;
    }

    public int getCellValue(int row, int col) {
        return gameBoard.getCell(row, col).getValue();
    }

    public boolean getCellLocked(int row, int col) {
        return gameBoard.getCell(row, col).isLocked();
    }

    public void resetGameBoard() {
        gameStats.reset();
        gameBoard.resetBoard();
    }

    public int getDim() {
        return gameBoard.getDim();
    }

    /* Methods for UI to access the SudokuStats class without jumping architectural layers. */
    public int getMoves() {
        return gameStats.getMoves();
    }

    public int getConflicts() {
        return gameStats.getConflicts();
    }

    public long getTime() {
        return gameStats.getGameTime();
    }

    public void addMoves() {
        gameStats.addMoves();
    }

    public void addConflicts() {
        gameStats.addConflicts();
    }

    public void saveStats(Context context) {
        gameStats.saveStats(context);
    }
}
