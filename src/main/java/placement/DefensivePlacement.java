package placement;
import java.util.Optional;

import api.RuleEngine;
import boards.TicTokToe;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import utils.Utils;
public class DefensivePlacement implements Placement{
    private static DefensivePlacement defensivePlacement;
    private DefensivePlacement(){}
    public synchronized static Placement get(){
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement,DefensivePlacement::new );
        return defensivePlacement;
    }
    @Override 
    public Optional<Cell> place(TicTokToe board,Player player){
        return Optional.ofNullable(defence(player, board));
    };
    private Cell defence(Player player, TicTokToe board){
        RuleEngine ruleEngine  = new RuleEngine();
        // block opponent chance
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    Move newMove = new Move(new Cell(i, j), player.flip());
                    TicTokToe boardCopy = board.copy();
                    boardCopy.move(newMove);
                    if (ruleEngine.getState(boardCopy).checkIsOver()){
                        newMove = new Move(new Cell(i,j),player);
                        return newMove.getCell();
                    }
                }
            }
        }
        return null;
    }
    @Override 
    public Placement next(){
        return ForkPlacement.get();
    };     
}
