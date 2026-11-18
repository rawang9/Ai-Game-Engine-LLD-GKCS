package boards;
import game.*;



public class TicTokToe implements Board {
    private String cells[][] = new String[3][3];
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