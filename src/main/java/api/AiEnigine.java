package api;

import game.*;
import boards.TicTokToe;

import java.sql.Array;

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
        Cell suggestedCell = offense(player,board);
        if (suggestedCell !=null) return suggestedCell;
        // block move
        suggestedCell = defence(player,board);
        if (suggestedCell !=null) return suggestedCell;
        return getBasicCell(board);
    }

    private Cell getOptimalMove(Player player, TicTokToe board){
        Cell best;
        // 1 check attacking move
        best = offense(player,board);
        if (best !=null) return best;
        // 2 check defensive move
        best = defence(player,board);
        if (best !=null) return best;
        // 3 check for fork
        RuleEngine ruleEngine  = new RuleEngine();
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if (gameInfo.hasAFork()){
            best = gameInfo.getForkCell();
            return best;
        }
        // 4 take center
        if (board.getCell(1,1)!=null){
            return new Cell(1,1);
        }
        // 5 take corneres
        final int corners[][] = new int[][]{{0,0},{0,2},{2,0},{2,2}};
        for(int i=0;i<4;i++){
            if (board.getCell(corners[i][0],corners[i][1])==null){
                return new Cell(corners[i][0],corners[i][1]);
            }
        }
        return getBasicCell(board);
    }
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
