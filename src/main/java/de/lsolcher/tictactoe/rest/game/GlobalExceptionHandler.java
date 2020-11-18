package de.lsolcher.tictactoe.rest.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalAccessException.class)
    protected ResponseEntity<Object> handleIllegalAccessException(IllegalAccessException e) {
        JSONObject jsonObject = new JSONObject(Map.of("error", e.getMessage()));
        return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
    }

}
