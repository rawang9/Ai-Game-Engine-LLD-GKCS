package game;

public class GameInfo {
    boolean isOver;
    String winnner;
    boolean hasFork;
    Player player;
    public GameInfo(GameState gameState,boolean hasFork,Player player){
        this.isOver = gameState.checkIsOver();
        this.winnner = gameState.getWinner();
        this.hasFork = hasFork;
        this.player = player;
    }
}
