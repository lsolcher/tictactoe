package de.lsolcher.tictactoe.rest.game;

import de.lsolcher.tictactoe.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    @ApiOperation("Returns the current game state.")
    ResponseEntity<GameResource> getGameState() {
        GameResource gameResource = gameService.getCurrentGameState();
        return new ResponseEntity<>(gameResource, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation("Let's a player move.")
    ResponseEntity<GameResource> playerMove(@ApiParam("The move of the player.")
                                            @Valid @RequestBody PlayerMoveDTO move) throws Exception {
        GameResource gameResource = gameService.playerMove(move);
        return new ResponseEntity<>(gameResource, HttpStatus.OK);
    }
}
