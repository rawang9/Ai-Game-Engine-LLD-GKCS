package api;
// #block start: imports use declared package names, not the src directory name
import game.*;
import boards.*;
// #block end

public class GameEngine {
    public Board start(String boardType){
        // #block start: compare string contents rather than object identity
        if ("TICTOKTOE".equals(boardType)){
            return  new TicTokToe();
        } else {
            throw new IllegalArgumentException();
        }
        // #block end
    }

    public void Move(Board board,Move move){
        if (board instanceof TicTokToe){
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }

};
