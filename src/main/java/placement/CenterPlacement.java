package placement;
import java.util.Optional;

import boards.TicTokToe;
import game.Board;
import game.Cell;
import game.Player;
import utils.Utils;
public class CenterPlacement implements Placement{
    private static CenterPlacement centerPlacement;
    private CenterPlacement(){}
    public synchronized static Placement get(){
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement,CenterPlacement::new );
        return centerPlacement;
    }
    @Override 
    public Optional<Cell> place(TicTokToe board,Player player){
        Cell center = null;
        if (board.getCell(1,1)!=null){
            center = new Cell(1,1);
        }
        return Optional.ofNullable(center);
    };
    @Override 
    public Placement next(){
        return CornerPlacement.get();
    };     
}
