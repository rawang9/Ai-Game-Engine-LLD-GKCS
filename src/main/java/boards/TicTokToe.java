package boards;
import game.*;

import java.util.function.BiFunction;
import java.util.function.Function;


public class TicTokToe implements Board {
    private String cells[][] = new String[3][3];

    // #block start: return and create a RuleSet typed for TicTokToe
    public static RuleSet<TicTokToe> getRules(){
        RuleSet<TicTokToe> rules = new RuleSet<>();
    // #block end
        rules.add(new Rule<TicTokToe>(board -> outerTraversal((i,j)-> board.getCell(i,j))));
        rules.add(new Rule<TicTokToe>(board -> outerTraversal((i,j)-> board.getCell(j,i))));
        rules.add(new Rule<TicTokToe>(board -> traverse(j-> board.getCell(j,j))));
        rules.add(new Rule<TicTokToe>(board -> traverse(j-> board.getCell(j,2-j))));
        rules.add(new Rule<TicTokToe>(board -> {
            int countOfFilledCell = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if (board.getCell(i,j)!=null){
                        countOfFilledCell++;
                    }
                }
            }
            if (countOfFilledCell==9){
                return new GameState(true,"?");
            }
            return new GameState(false,"?");
        }));

        return rules;
    }

    private static GameState outerTraversal(BiFunction<Integer,Integer,String> traversal){
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
    private static GameState traverse(Function<Integer,String> traversal){
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

    public String getCell(int row,int col) {
        return cells[row][col];
    }
    @Override
    public void move(Move move){
        setCells(move.getCell(),move.getPlayer().getSymbol());
    }
    public void setCells(Cell cell,String symbol){
        if (cells[cell.getRow()][cell.getCol()]==null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public TicTokToe copy(){
        TicTokToe ticTokToeBoard = new TicTokToe();
        for(int i=0;i<3;i++){
            System.arraycopy(cells[i],0,ticTokToeBoard.cells[i],0,3);
        }
        return ticTokToeBoard;
    }
    @Override
    public String toString(){
        String output="";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (cells[i][j]==null){
                    output+= "-";
                }else {
                    output+= cells[i][j];
                }
                if (j!=2){
                    output+='|';
                }
            }
            output+='\n';
        }
        return output;
    }
};
