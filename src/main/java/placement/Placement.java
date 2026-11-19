package placement;

import game.Board;
import game.Cell;
import game.Player;

import java.util.Optional;

import boards.TicTokToe;

public interface Placement {


    public Optional<Cell> place(TicTokToe board,Player player);

    public Placement next();
}