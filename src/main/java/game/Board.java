package game;
public interface Board {
    public abstract void move(Move move);
    public abstract Board copy();
    public String getCell(int row,int col);
}
