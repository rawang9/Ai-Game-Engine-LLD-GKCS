package placement;

import java.util.Optional;

import api.RuleEngine;
import boards.TicTokToe;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import utils.Utils;

public class OffensivePlacement implements Placement{
    private static OffensivePlacement offensivePlacement;
    private OffensivePlacement(){}
    public synchronized static Placement get(){
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement,OffensivePlacement::new );
        return offensivePlacement;
    }
    @Override 
    public Optional<Cell> place(TicTokToe board,Player player){
        return Optional.ofNullable(offense(player, board));
    };
    private Cell offense(Player player, TicTokToe board){
        RuleEngine ruleEngine  = new RuleEngine();
        // make good move to close game
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    Move newMove = new Move(new Cell(i, j), player);
                    TicTokToe boardCopy = board.copy();
                    boardCopy.move(newMove);
                    if (ruleEngine.getState(boardCopy).checkIsOver()){
                        return newMove.getCell();
                    }
                }
            }
        }
        return null;
    }
    
    @Override 
    public Placement next(){
        return DefensivePlacement.get();
    }; 
    
    
}
