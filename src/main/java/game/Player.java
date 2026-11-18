package game;
public class Player {
    private String playerSymbol;;
    public Player(String symbol){
        this.playerSymbol = symbol;
    }
    public Player flip(){
        return new Player(playerSymbol.equals("X") ? "O":"X");
    }
    public String getSymbol(){
        return playerSymbol;
    }
};
