package de.lsolcher.tictactoe.service;

import de.lsolcher.tictactoe.rest.game.GameResource;
import de.lsolcher.tictactoe.rest.game.PlayerMoveDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameService {
    private final Token[][] gameField = new Token[3][3];
    private GameState state = GameState.PRE_START;
    private Token currentPlayer;

    public GameService() {
        for (Token[] array : gameField) {
            Arrays.fill(array, Token.EMPTY);
        }
        currentPlayer = Token.EMPTY;
    }

    private GameResource getGameResource() {
        return GameResource.builder().gameField(gameField).gameState(state).currentPlayer(currentPlayer).build();
    }

    public GameResource getCurrentGameState() {
        return getGameResource();
    }


    public GameResource playerMove(PlayerMoveDTO move) throws Exception {
        resetGameState();

        if (currentPlayer != Token.EMPTY && move.getToken() != currentPlayer) {
            throw new IllegalAccessException("Current player is not eligible to move");
        }
        if (gameField[move.getPositionX()][move.getPositionY()] != Token.EMPTY) {
            throw new IllegalAccessException("Field already occupied");
        }
        gameField[move.getPositionX()][move.getPositionY()] = move.getToken();
        calculateGameState();

        currentPlayer = move.getToken() == Token.CIRCLE ? Token.CROSS : Token.CIRCLE;
        return getGameResource();
    }

    private void resetGameState() {
        if (state == GameState.PRE_START) {
            state = GameState.STARTED;
            currentPlayer = Token.EMPTY;
        } else if (state == GameState.CIRCLE_WON || state == GameState.CROSS_WON || state == GameState.DRAW) {
            state = GameState.PRE_START;
            currentPlayer = Token.EMPTY;
            for (Token[] array : gameField) {
                Arrays.fill(array, Token.EMPTY);
            }
        }
    }

    private void calculateGameState() {
        state = GameState.STARTED;

        // calculate draw
        calculateDraw();

        // calculate winner
        // calculate rows and columns
        if (state == GameState.STARTED) {
            calculateRowsAndColumns();
        }

        // calculate diagonals
        if (state == GameState.STARTED) {
            calculateDiagonals();
        }
    }

    private void calculateDiagonals() {
        Token[] row1 = new Token[3];
        Token[] row2 = new Token[3];

        row1[0] = gameField[0][0];
        row1[1] = gameField[1][1];
        row1[2] = gameField[2][2];

        row2[0] = gameField[0][2];
        row2[1] = gameField[1][1];
        row2[2] = gameField[2][0];
        calculateRowWinner(row1, row2);
    }

    private void calculateRowsAndColumns() {
        Token[] row1 = new Token[3];
        Token[] row2 = new Token[3];

        for (int i = 0; i < 3; i++) {
            row1[0] = gameField[i][0];
            row1[1] = gameField[i][1];
            row1[2] = gameField[i][2];

            row2[0] = gameField[0][i];
            row2[1] = gameField[1][i];
            row2[2] = gameField[2][i];
            calculateRowWinner(row1, row2);
        }
    }

    private void calculateRowWinner(Token[] row1, Token[] row2) {
        if (Arrays.stream(row1).sequential().allMatch(el -> el == Token.CIRCLE) ||
                Arrays.stream(row2).sequential().allMatch(el -> el == Token.CIRCLE)) {
            state = GameState.CIRCLE_WON;
        }
        if (Arrays.stream(row1).sequential().allMatch(el -> el == Token.CROSS) ||
                Arrays.stream(row2).sequential().allMatch(el -> el == Token.CROSS)) {
            state = GameState.CROSS_WON;
        }
    }

    private void calculateDraw() {
        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (gameField[i][j] == Token.EMPTY)
                    count++;
        if (count == 9) {
            state = GameState.DRAW;
        }
    }
}
