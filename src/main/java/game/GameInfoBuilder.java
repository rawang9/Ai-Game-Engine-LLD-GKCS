package game;

public class GameInfoBuilder {
    boolean isOver;
    String winner;
    boolean hasFork;
    Player player;
    Cell forkCell;
    public GameInfo build(){
        return new GameInfo(isOver,winner,hasFork,player,forkCell);
    }
    public GameInfoBuilder isOver(boolean isOver){
        this.isOver = isOver;
        return this;
    }
    public GameInfoBuilder winner(String winner){
        this.winner = winner;
        return this;
    }
    public GameInfoBuilder hasFork(boolean hasFork){
        this.hasFork = hasFork;
        return this;
    }
    public GameInfoBuilder player(Player player){
        this.player = player;
        return this;
    }
    public GameInfoBuilder forkCell(Cell forkCell){
        this.forkCell = forkCell;
        return this;
    }
}
