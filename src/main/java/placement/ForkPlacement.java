package placement;
import java.util.Optional;

import api.RuleEngine;
import boards.TicTokToe;
import game.Board;
import game.Cell;
import game.GameInfo;
import game.Player;
import utils.Utils;
public class ForkPlacement implements Placement{
    private static ForkPlacement forkPlacement;
    private ForkPlacement(){}
    public synchronized static Placement get(){
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement,ForkPlacement::new );
        return forkPlacement;
    }
    @Override 
    public Optional<Cell> place(TicTokToe board,Player player){
        Cell best = null;
        RuleEngine ruleEngine  = new RuleEngine();
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasAFork()){
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    };
    @Override 
    public Placement next(){
        return CenterPlacement.get();
    };     
}
