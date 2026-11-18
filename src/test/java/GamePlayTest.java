import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import api.AiEnigine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class GamePlayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;
    @BeforeEach
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }
    @Test
    public void rowWin(){
        // make moves in the loop
        int moves[][] = new int[][]{{1,0},{1,1},{1,2}};
        int secondPlayerMoves[][] = new int[][]{{0,0},{0,1},{0,2}};
        Board board = gameEngine.start("TICTOKTOE");
        play(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).checkIsOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void colWin(){
        // make moves in the loop
        int moves[][] = new int[][]{{0,2},{1,2},{2,2}};
        int secondPlayerMoves[][] = new int[][]{{0,0},{0,1},{1,1}};
        Board board = gameEngine.start("TICTOKTOE");
        play(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).checkIsOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void digWin(){
        // make moves in the loop
        int moves[][] = new int[][]{{0,0},{1,1},{2,2}};
        int secondPlayerMoves[][] = new int[][]{{0,2},{1,2},{2,1}};
        Board board = gameEngine.start("TICTOKTOE");
        play(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).checkIsOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void revDigWin(){
        // make moves in the loop
        int moves[][] = new int[][]{{0,2},{1,1},{2,0}};
        int secondPlayerMoves[][] = new int[][]{{0,1},{1,2},{2,1}};
        Board board = gameEngine.start("TICTOKTOE");
        play(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).checkIsOver());
        assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void computerWin(){
        // make moves in the loop
        int moves[][] = new int[][]{{1,1},{2,1},{2,2}};
        int secondPlayerMoves[][] = new int[][]{{0,0},{1,0},{2,0}};
        Board board = gameEngine.start("TICTOKTOE");
        play(board,moves,secondPlayerMoves);
        assertTrue(ruleEngine.getState(board).checkIsOver());
        assertEquals("O", ruleEngine.getState(board).getWinner());
    }

    private void play(Board board,int moves[][],int secondPlayerMoves[][]){
        int row,col,sRow,sCol;
        int next = 0;
        Player computer = new Player("O"), player = new Player("X");
        while(!ruleEngine.getState(board).checkIsOver()){
            System.out.println("\n" + board);
            System.out.println("Make your move");
            row = moves[next][0];
            col = moves[next][1];
            sRow = secondPlayerMoves[next][0];
            sCol = secondPlayerMoves[next][1];
            next++;
            Move playerMove = new Move(new Cell(row,col),player);
            gameEngine.Move(board, playerMove);
            System.out.println("After making move \n" + board);
            if (!ruleEngine.getState(board).checkIsOver()){
                Move computerMove = new Move(new Cell(sRow,sCol),computer);
                gameEngine.Move(board, computerMove);
            } else {
                break;
            }
        }
    }
}
