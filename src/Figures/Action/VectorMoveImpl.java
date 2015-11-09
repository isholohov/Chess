package Figures.Action;

import Figures.Figure;
import Game.Field;
import Util.Position;

import java.util.LinkedList;
import java.util.List;

public class VectorMoveImpl implements Move {

    private Move possibleMovesByMap;

    {
        possibleMovesByMap = new MapMoveImpl();
    }

    @Override
    public List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap) {
        Field field = Field.getInstance();
        List<Position> mapMoves = possibleMovesByMap.getPossibleMoves(figureToCheck, moveMap);
        List<Position> vectorMoves = new LinkedList<>();

        Next:
        for (Position mapPosition : mapMoves) {
            //final short dx = (short) (Math.max(position.getX(), figureToCheck.getPosition().getX()) + Math.min(position.getX(), figureToCheck.getPosition().getX()));
            //final short dy = (short) (Math.max(position.getY(), figureToCheck.getPosition().getY()) + Math.min(position.getY(), figureToCheck.getPosition().getY()));

            short dx = 0;
            short dy = 0;

            if(mapPosition.lefterThen(figureToCheck.getPosition())) {
                dx = -1;
            } else if(mapPosition.righterThen(figureToCheck.getPosition())) {
                dx = 1;
            }

            if(mapPosition.lowerTher(figureToCheck.getPosition())) {
                dy = -1;
            } else if(mapPosition.upperThen(figureToCheck.getPosition())) {
                dy = 1;
            }

            for (short x = (short) (figureToCheck.getPosition().getX() + dx); x <= field.getWidth(); ) {
                for (short y = (short) (figureToCheck.getPosition().getY() + dy); y <= field.getHeight(); ) {

                    Position currentCheckPosition = new Position(x, y);

                    if (field.isOutOfBorder(currentCheckPosition)) {
                        continue Next;
                    }

                    if(field.isPositionBusy(currentCheckPosition)) {
                        if (field.getFigureByPosition(currentCheckPosition).isSameColor(figureToCheck)) {
                            continue Next;
                        } else {
                            vectorMoves.add(currentCheckPosition);
                            continue Next;
                        }
                    }

                    vectorMoves.add(currentCheckPosition);
                    x += dx;
                    y += dy;


                    /*Figure checkFigure = field.getFigureByPosition(currentCheckPosition);
                    if (checkFigure == null || (checkFigure.isDead())) {
                        mapMoves.add(currentCheckPosition);
                    } else if (!checkFigure.isSameColor(figureToCheck)) {
                        mapMoves.add(currentCheckPosition);
                        continue Next;
                    } else {
                        continue Next;
                    }*/
                }
            }
        }

        return vectorMoves;
    }
}
