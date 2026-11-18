package api;

import game.Board;
import game.Move;
import game.Player;
import game.Cell;
import boards.TicTokToe;

public class AiEnigine {
    public Move suggestMove(Player computerPlayer, Board board){
        if (board instanceof TicTokToe){
            TicTokToe ticTokToeBoard = (TicTokToe) board;
            Move suggestedMove = null;
            int threshhold=3;
            if (countMoves(ticTokToeBoard)<threshhold){
                suggestedMove =  getBasicMove(computerPlayer,ticTokToeBoard);
            } else {
                suggestedMove = getSmartMove(computerPlayer,ticTokToeBoard);
            }
            if (suggestedMove !=null){
                return suggestedMove;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    private Move getBasicMove(Player computerPlayer, TicTokToe board){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    Move newMove = new Move(new Cell(i, j), computerPlayer);
                    return newMove;
                }
            }
        }
        return null;
    }
    private  Move getSmartMove(Player computerPlayer, TicTokToe board){
        RuleEngine ruleEngine  = new RuleEngine();
        // make good move to close game
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    Move newMove = new Move(new Cell(i, j), computerPlayer);
                    TicTokToe boardCopy = board.copy();
                    boardCopy.move(newMove);
                    if (ruleEngine.getState(boardCopy).checkIsOver()){
                        return newMove;
                    }
                }
            }
        }
        // block opponent chance
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    Move newMove = new Move(new Cell(i, j), computerPlayer.flip());
                    TicTokToe boardCopy = board.copy();
                    boardCopy.move(newMove);
                    if (ruleEngine.getState(boardCopy).checkIsOver()){
                        newMove = new Move(new Cell(i,j),computerPlayer);
                        return newMove;
                    }
                }
            }
        }
        return getBasicMove(computerPlayer,board);
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
