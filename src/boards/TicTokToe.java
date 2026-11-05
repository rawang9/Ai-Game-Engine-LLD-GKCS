package boards;
import game.Board;
import game.Cell;


public class TicTokToe implements  Board {
    private String cells[][] = new String[3][3];
    public String getCell(int row,int col){
        return cells[row][col];
    }
    public void setCell(Cell cell, String playerSymbol){
        cells[cell.getRow()][cell.getCol()] = playerSymbol;

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