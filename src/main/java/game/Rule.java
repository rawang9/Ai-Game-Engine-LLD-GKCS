package game;

import java.util.function.Function;

// #block start: fix the class keyword and import Function
public class Rule {
    public Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard,GameState> condition){
        this.condition = condition;
    }
    // #block end
}
