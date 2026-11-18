import game.*;
import api.*;
import java.util.*;
// #block end


public class Main {
public static void main(String[] args){
    GameEngine gameEngine = new GameEngine();
    AiEnigine aiEnigine = new AiEnigine();
    RuleEngine ruleEngine = new RuleEngine();
    Board  board = gameEngine.start("TICTOKTOE");
    // make moves in the loop
    int row,col;
    Scanner scanner = new Scanner(System.in);
    Player computer = new Player("O"), player = new Player("X");
    while(!ruleEngine.getState(board).checkIsOver()){
        System.out.println("\n" + board);
        System.out.println("Make your move");
        row = scanner.nextInt();
        col = scanner.nextInt();
        Move playerMove = new Move(new Cell(row,col),player);
        gameEngine.Move(board, playerMove);
        System.out.println("After making move \n" + board);
        if (!ruleEngine.getState(board).checkIsOver()){
            Move computerMove = aiEnigine.suggestMove(computer,board);
            gameEngine.Move(board, computerMove);
        } else {
            break;
        }
    }
    System.out.println("Game Result :" + ruleEngine.getState(board));
    System.out.println("\n" + board);
}
};








