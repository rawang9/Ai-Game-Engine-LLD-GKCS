package api;
// #block start: imports use declared package names, not the src directory name
import boards.*;
import game.*;
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
    public GameResult isComplete(Board board){
        if (board instanceof TicTokToe){
            TicTokToe board1 = (TicTokToe) board;
            boolean rowComplete = true;
            String firstCharater = "";
            for(int i=0;i<3;i++){
                firstCharater = board1.getCell(i, 0);
                rowComplete = firstCharater!=null;
                if (firstCharater!=null){
                    for(int j=1;j<3;j++){
                        if (!firstCharater.equals(board1.getCell(i, j))){
                            rowComplete = false;
                            break;
                        }

                    }    
                } 
                if (rowComplete){break;}
            }
            if (rowComplete){
                return new GameResult(true,firstCharater);
            }  
            boolean colComplete = true;
            for(int i=0;i<3;i++){
                firstCharater = board1.getCell(0, i);
                colComplete = firstCharater!=null;
                if (firstCharater!=null){
                    for(int j=1;j<3;j++){
                        if (!firstCharater.equals(board1.getCell(j, i))){
                            colComplete = false;
                            break;
                        }

                    }    
                }
                if (colComplete){break;} 
            }
            if (colComplete){
                return new GameResult(true,firstCharater);
            }  

            firstCharater = board1.getCell(0, 0);
            boolean digonalComplete = firstCharater!=null;
            for(int i=1;i<3;i++){
                if (firstCharater!=null && !firstCharater.equals(board1.getCell(i, i))){
                        digonalComplete = false;
                        break;
                } 
            }
            if (digonalComplete){
                return new GameResult(true,firstCharater);
            }  
            firstCharater = board1.getCell(0, 2);
            boolean revDigonalComplete = firstCharater!=null;
            for(int i=1;i<3;i++){                
                if (firstCharater!=null && !firstCharater.equals(board1.getCell(i, 2-i))){
                        revDigonalComplete = false;
                        break;
                } 
            }
            if (revDigonalComplete){
                return new GameResult(true,firstCharater);
            }  

            return new GameResult(false,"?");
        } else {
            throw new IllegalArgumentException();
        }
    }
    public Move suggestMove(Board board){
        if (board instanceof TicTokToe){
            TicTokToe board1 = (TicTokToe) board;
            Move newMove = null;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getCell(i, j)==null){
                        newMove = new Move(new Cell(i,j));
                        return newMove;
                    };
                }
            }
            return newMove;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void Move(Board board,Player player,Move move){
        if (board instanceof TicTokToe){
            TicTokToe board1 = (TicTokToe) board;
            board1.setCell(move.getCell(), player.getSymbol());
        } else {
            throw new IllegalArgumentException();
        }
    }

};
