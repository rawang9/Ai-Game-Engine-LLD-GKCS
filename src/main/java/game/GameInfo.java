package game;

public class GameInfo {
    boolean isOver;
    String winner;
    boolean hasFork;
    Player player;
    public GameInfo(boolean isOver, String winner,boolean hasFork,Player player){
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
    }
}
