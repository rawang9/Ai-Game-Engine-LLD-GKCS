package api;

import game.*;
import boards.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameState getState(Board board){
        if (board instanceof TicTokToe){
            TicTokToe ticTokToeBoard = (TicTokToe) board;

            // #block start: spelling corrections for traversal and diagonal names
            GameState rowWin = outerTraversal((i,j)-> ticTokToeBoard.getCell(i,j));
            if (rowWin.checkIsOver()){
                return rowWin;
            }
            GameState colWin = outerTraversal((i,j)-> ticTokToeBoard.getCell(j,i));
            if (colWin.checkIsOver()){
                return colWin;
            }
            GameState diagonalWin = traverse(j-> ticTokToeBoard.getCell(j,j));
            if (diagonalWin.checkIsOver()){
                return diagonalWin;
            }
            GameState reverseDiagonalWin = traverse(j-> ticTokToeBoard.getCell(j,2-j));
            if (reverseDiagonalWin.checkIsOver()){
                return reverseDiagonalWin;
            }
            // #block end
            int countOfFilledCell = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if (ticTokToeBoard.getCell(i,j)!=null){
                        countOfFilledCell++;
                    }
                }
            }
            if (countOfFilledCell==9){
                return new GameState(true,"?");
            }else {
                return new GameState(false,"?");
            }
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
                        boolean canStillWin = false;
                        for(int k=0;k<3;k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = b.copy();
                                b1.move(new Move(new Cell(k,l),player.flip()));
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
                            return new GameInfo(gameState,true,player.flip());
                        }
                    }
                }
            }
            return new GameInfo(gameState,false,null);
        } else {
            throw new IllegalArgumentException();
        }

    }
    // #block start: corrected "outerTraversel" to "outerTraversal"
    private GameState outerTraversal(BiFunction<Integer,Integer,String> traversal){
        GameState result = new GameState(false,"-");
        for(int i=0;i<3;i++){
            final int ii = i;
            GameState innerTraversal = traverse(j-> traversal.apply(ii,j));
            if (innerTraversal.checkIsOver()) {
                result = innerTraversal;
                break;
            }
        }
        return result;
    }
    // #block end
    private GameState traverse(Function<Integer,String> traversal){
        GameState result = new GameState(false,"-");
        boolean possibleStreak=true;
        for(int j=0;j<3;j++){
            if(traversal.apply(j)==null || !traversal.apply(0).equals(traversal.apply(j))){
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true,traversal.apply(0));
        }
        return result;
    }
}
