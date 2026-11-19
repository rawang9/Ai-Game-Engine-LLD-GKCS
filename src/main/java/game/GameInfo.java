package game;

public class GameInfo {
    boolean isOver;
    String winner;
    boolean hasFork;
    Cell forkCell;
    Player player;
    public GameInfo(boolean isOver, String winner,boolean hasFork,Player player,Cell forkCell){
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.forkCell = forkCell;
    }
    public boolean hasAFork(){
        return hasFork;
    }
    public Cell getForkCell(){
        return forkCell;
    }
}
