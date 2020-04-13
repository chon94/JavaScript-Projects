package corbos.memorygame.models;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    private static final int SELECTED = -1;

    private final int[][] board;
    private final int gameId;

    private Card firstCard;
    private int movesRemaining;

    public Game(int rows, int columns, int gameId) {

        this.gameId = gameId;

        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("rows and columns must be positive integers");
        }

        movesRemaining = rows * columns;
        if (movesRemaining % 2 != 0) {
            throw new IllegalArgumentException("rows * columns must be an even number or one card will be unmatched");
        }
        movesRemaining = movesRemaining / 2;

        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < movesRemaining; i++) {
            values.add(i);
            values.add(i);
        }
        Collections.shuffle(values);

        board = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = values.get(row * columns + col);
            }
        }
    }

    public int getRows() {
        return board.length;
    }

    public int getColumns() {
        return board[0].length;
    }

    public int getGameId() {
        return gameId;
    }

    public MoveResponse move(MoveRequest request) {

        MoveResponse response = new MoveResponse();

        // done. don't proceed
        if (movesRemaining <= 0) {
            response.setStatus(MoveStatus.GAME_OVER);
            return response;
        }

        // off the end of the board
        if (request.getRow() < 0 || request.getRow() >= board.length
                || request.getColumn() < 0 || request.getColumn() >= board[0].length) {

            response.setStatus(MoveStatus.ERROR);
            response.setMessage("Invalid move.");
            return response;
        }

        // duplicate request
        if (firstCard != null
                && firstCard.getRow() == request.getRow()
                && firstCard.getColumn() == request.getColumn()) {
            response.setStatus(MoveStatus.ERROR);
            response.setMessage("Duplicate move.");
            return response;
        }

        response.setValue(board[request.getRow()][request.getColumn()]);

        // card was already selected and matched
        if (response.getValue() == SELECTED) {
            response.setStatus(MoveStatus.ERROR);
            response.setMessage("Your card is already part of a match.");
            return response;
        }

        if (firstCard == null) {

            firstCard = new Card(request.getRow(),
                    request.getColumn(),
                    response.getValue());

            response.setStatus(MoveStatus.NEXT);
            return response;
        }

        if (firstCard.getValue() == response.getValue()) { // match!

            movesRemaining--;
            board[firstCard.getRow()][firstCard.getColumn()] = SELECTED;
            board[request.getRow()][request.getColumn()] = SELECTED;

            if (movesRemaining <= 0) {
                response.setStatus(MoveStatus.GAME_OVER);
            } else {
                response.setStatus(MoveStatus.MATCH);
            }

        } else {
            response.setStatus(MoveStatus.NO_MATCH);
        }

        firstCard = null;

        return response;
    }

}
