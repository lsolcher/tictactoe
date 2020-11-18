package de.lsolcher.tictactoe.rest.game;

import de.lsolcher.tictactoe.service.GameState;
import de.lsolcher.tictactoe.service.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(description = "Class representing a the tic tac toe game.")
public class GameResource {
    @ApiModelProperty(notes = "The game field of this game. The field consists of three rows and columns, represented by an two-dimensional array of length [2][2]", example = "[\n" +
            "            \"CROSS\",\n" +
            "            \"CIRCLE\",\n" +
            "            \"CROSS\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"EMPTY\",\n" +
            "            \"CROSS\",\n" +
            "            \"CIRCLE\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"EMPTY\",\n" +
            "            \"EMPTY\",\n" +
            "            \"CROSS\"\n" +
            "        ]")
    private Token[][] gameField;
    @ApiModelProperty(notes = "The current player. Can either be CIRCLE, CROSS or EMPTY, which indicates that either player can start", example = "CIRCLE")
    private Token currentPlayer;
    @ApiModelProperty(notes = "The current game state.", example = "STARTED")
    private GameState gameState;
}
