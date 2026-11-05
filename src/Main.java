
import api.*;
import game.*;
import java.util.*;
// #block end


public class Main {
public static void main(String[] args){
    GameEngine gameEngine = new GameEngine();
    Board  board = gameEngine.start("TICTOKTOE");
    // make moves in the loop
    int row,col;
    Scanner scanner = new Scanner(System.in);
    Player computer = new Player("O"), player = new Player("X");
    while(!gameEngine.isComplete(board).checkIsOver()){
        System.out.println("\n" + board);
        System.out.println("Make your move");
        row = scanner.nextInt();
        col = scanner.nextInt();
        Move playerMove = new Move(new Cell(row,col));
        gameEngine.Move(board, player, playerMove);
        System.out.println("After making move \n" + board);
        if (!gameEngine.isComplete(board).checkIsOver()){
            Move computerMove = gameEngine.suggestMove(board);
            gameEngine.Move(board, computer, computerMove);
        } else {
            break;
        }
    }
    System.out.println("Game Result :" + gameEngine.isComplete(board));
    System.out.println("\n" + board);
}
};








