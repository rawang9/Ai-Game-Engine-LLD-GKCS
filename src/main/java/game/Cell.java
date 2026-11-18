package game;
public class Cell {
    private int row,col;
    public Cell(int row,int col){
        this.row=row;
        this.col = col;
    }
    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }
    public void setCol(int updatedCol){
        col = updatedCol;
    }
    public void setRow(int updatedRow){
        row = updatedRow;
    }
};