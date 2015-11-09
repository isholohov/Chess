package Figures.Action;

import Figures.Figure;
import Game.Field;
import Util.Position;

import java.util.LinkedList;
import java.util.List;

public class MapMoveImpl implements Move {
    @Override
    public List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap) {
        List<Position> possibleMoves = new LinkedList<>();
        List<Position> readyMoves = new LinkedList<>();

        Field field = Field.getInstance();

        for(Position position : moveMap) {
            readyMoves.add(new Position(
                    (figureToCheck.getPosition().getX() + position.getX()),
                    (figureToCheck.getPosition().getY() + position.getY())
            ));
        }

        /*for (int i = 0; i < moveMap.size(); ) {
            Position position = new Position(
                    (figureToCheck.getPosition().getX() + moveMap.get(i).getX()),
                    (figureToCheck.getPosition().getY() + moveMap.get(i).getY())
            );
            readyMoves.add(position);
            i++;
        }*/

       /* for (Position position : readyMoves) {
            if (field.isOutOfBorder(position)) {
                readyMoves.remove(position);
            }
        }*/

        for (int i = 0; i < readyMoves.size(); ) {
            if (field.isOutOfBorder(readyMoves.get(i))) {
                readyMoves.remove(i);
            } else {
                i++;
            }
        }

        for (Position mapPosition : readyMoves) {

            if (field.isPositionBusy(mapPosition)) {
                final Figure figure = field.getFigureByPosition(mapPosition);
                if (figure != null || !figure.isDead()) {
                    if (!figureToCheck.isSameColor(figure)) {
                        possibleMoves.add(mapPosition);
                    }
                }
            } else {
                final Figure figure = field.getFigureByPosition(mapPosition);
                if (figure == null || figure.isDead()) {
                    possibleMoves.add(mapPosition);
                }
            }
        }




        return possibleMoves;
    }
}
