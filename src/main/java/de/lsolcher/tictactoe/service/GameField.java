package de.lsolcher.tictactoe.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameField {
    private List<Token> xAxis = new ArrayList<>();
    private List<Token> yAxis = new ArrayList<>();
}
