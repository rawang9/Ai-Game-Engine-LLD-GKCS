package api;

import game.*;
import boards.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    // #block start: retain the board type so RuleSet iteration yields typed Rules
    Map<String, RuleSet<TicTokToe>> ruleMap = new HashMap<>();
    // #block end
    public RuleEngine(){
        ruleMap.put(TicTokToe.class.getName(),TicTokToe.getRules());
    }
    public GameState getState(Board board){
        if (board instanceof TicTokToe){
            TicTokToe ticTokToeBoard = (TicTokToe) board;
            for(Rule rule:ruleMap.get(TicTokToe.class.getName())){
                GameState gameState = rule.condition.apply(ticTokToeBoard);
                if (gameState.checkIsOver()){
                    return gameState;
                }
            }
            return new GameState(false,"?");
        } else {
            throw new IllegalArgumentException();
        }
    }
    public GameInfo getInfo(Board board){
        if (board instanceof TicTokToe) {
            TicTokToe ticTokToeBoard = (TicTokToe) board;
            GameState gameState = getState(ticTokToeBoard);
            final String[] playerSymbol = new String[]{"X","O"};
            for(int index=0;index<2;index++){
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        Board b = ticTokToeBoard.copy();
                        Player player = new Player(playerSymbol[index]);
                        b.move(new Move(new Cell(i,j),player));
                        Cell forkCell = null;
                        boolean canStillWin = false;
                        for(int k=0;k<3;k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = b.copy();
                                forkCell = new Cell(k,l);
                                b1.move(new Move(forkCell,player.flip()));
                                if (getState(b1).getWinner().equals(player.flip().getSymbol())){
                                    canStillWin = true;
                                    break;

                                }
                            }
                            if (canStillWin){
                                break;
                            }
                        }
                        if(canStillWin){
                            return new GameInfoBuilder()
                                    .isOver(gameState.checkIsOver())
                                    .hasFork(true)
                                    .forkCell(forkCell)
                                    .player(player.flip())
                                    .winner(gameState.getWinner())
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.checkIsOver())
                    .winner(gameState.getWinner())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }

    }

}
