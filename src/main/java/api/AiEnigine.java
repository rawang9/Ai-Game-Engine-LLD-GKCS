package api;

import game.*;
import placement.DefensivePlacement;
import placement.OffensivePlacement;
import placement.Placement;
import boards.TicTokToe;

import java.sql.Array;
import java.util.Optional;

public class AiEnigine {
    public Move suggestMove(Player player, Board board){
        if (board instanceof TicTokToe){
            TicTokToe ticTokToeBoard = (TicTokToe) board;
            Cell suggestedCell = null;
            int threshhold=3;
            if (countMoves(ticTokToeBoard)<threshhold){
                suggestedCell =  getBasicCell(ticTokToeBoard);
            } else if (countMoves(ticTokToeBoard)<threshhold+1){
                suggestedCell = getSmartCell(player,ticTokToeBoard);
            } else {
                suggestedCell = getOptimalMove(player,ticTokToeBoard);
            }

            if (suggestedCell !=null){
                return new Move(suggestedCell,player);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    private Cell getBasicCell(TicTokToe board){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    return new Cell(i,j);
                }
            }
        }
        return null;
    }

    private  Cell getSmartCell(Player player, TicTokToe board){
        // make good move to close game
        Optional<Cell> suggestedCell;
        Placement placement = OffensivePlacement.get();
        suggestedCell = placement.place(board, player);
        if (suggestedCell!=null) return suggestedCell.get();
        placement = DefensivePlacement.get();
        // block move
        suggestedCell = placement.place(board, player);
        if (suggestedCell !=null) return suggestedCell.get();
        return getBasicCell(board);
    }

    private Cell getOptimalMove(Player player, TicTokToe board){
        Placement placement = OffensivePlacement.get();
        Optional<Cell> finding;
        while(placement!=null){
            finding = placement.place(board, player);
            if (finding!=null){
                return finding.get();
            }
            placement = placement.next();
        }
        return null;
    }
    
    private Integer countMoves(TicTokToe board){
        Integer count  = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCell(i, j)!=null){
                    count++;
                };
            }
        }
        return count;
    }
}
