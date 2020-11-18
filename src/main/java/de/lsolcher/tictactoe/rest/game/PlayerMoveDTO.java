package de.lsolcher.tictactoe.rest.game;

import de.lsolcher.tictactoe.service.Token;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class PlayerMoveDTO {
    @NotNull
    @ApiModelProperty(notes = "The current player token, either CIRCLE or CROSS", example = "CIRCLE")
    Token token;

    @Min(0)
    @Max(2)
    @NotNull
    @ApiModelProperty(notes = "The x position where the token should be placed", example = "1")
    Integer positionX;

    @Min(0)
    @Max(2)
    @NotNull
    @ApiModelProperty(notes = "The y position where the token should be placed", example = "1")
    Integer positionY;
}
