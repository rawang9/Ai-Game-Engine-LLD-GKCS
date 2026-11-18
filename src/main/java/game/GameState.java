package game;
public class GameState {
    private Boolean isOver=false;
    private String winner;
    public GameState(Boolean isOver,String winner){
        this.isOver = isOver;
        this.winner = winner;
    }
    public Boolean checkIsOver(){
        return isOver;
    }
    public String getWinner(){
        return winner;
    }
    @Override
    public String toString(){
        String output="Winner : " + winner +"\n Result: " + isOver;
        return output;
    }
}

