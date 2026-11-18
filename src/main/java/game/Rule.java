package game;

import java.util.function.Function;

// #block start: fix the class keyword and import Function
public class Rule<T extends Board> {
    public Function<T, GameState> condition;

    public Rule(Function<T,GameState> condition){
        this.condition = condition;
    }
    // #block end
}
