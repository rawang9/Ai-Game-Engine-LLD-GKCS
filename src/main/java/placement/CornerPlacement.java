package placement;
import java.util.Optional;

import boards.TicTokToe;
import game.Board;
import game.Cell;
import game.Player;
import utils.Utils;
public class CornerPlacement implements Placement{
    private static CornerPlacement cornerPlacement;
    private CornerPlacement(){}
    public synchronized static Placement get(){
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement,CornerPlacement::new );
        return cornerPlacement;
    }
    @Override 
    public Optional<Cell> place(TicTokToe board,Player player){
        return Optional.ofNullable(placeInCorner(board));
    };
    public Cell placeInCorner(TicTokToe board) {
        final int corners[][] = new int[][]{{0,0},{0,2},{2,0},{2,2}};
        for(int i=0;i<4;i++){
            if (board.getCell(corners[i][0],corners[i][1])==null){
                return new Cell(corners[i][0],corners[i][1]);
            }
        }
        return null;
    }
    @Override 
    public Placement next(){
        return null;
    };     
}
