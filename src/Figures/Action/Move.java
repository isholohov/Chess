package Figures.Action;

import Figures.Figure;
import Util.Position;

import java.util.List;

public interface Move {

    List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap);

}
