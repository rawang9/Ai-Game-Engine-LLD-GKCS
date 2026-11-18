package game;
public class Player {
    private int timeUsedInMillis;
    private User id;
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
    public void setTimeTaken(int timeInMillis){
        this.timeUsedInMillis+=timeInMillis;
    }
    public int getTimeUsedInMillis(){
        return timeUsedInMillis;
    }
};
