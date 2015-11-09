package Figures;

import Figures.Action.MapMoveImpl;
import Game.Field;
import Util.Position;

import java.util.List;

public class Pawn extends Figure {
    private Position _leftMove;
    private Position _rightMove;
    private Position _doubleMove;
    {
        _leftMove = new Position(-1,1);
        _rightMove = new Position(1,1);
        _doubleMove = new Position(0,2);
        _moveMap.add(new Position(0, 1));
        _moveMap.add(_doubleMove);
        _moveMap.add(_leftMove);
        _moveMap.add(_rightMove);

        possibleMovesGetter = new MapMoveImpl();
    }

    public Pawn(boolean black, Position position) {
        super(black, position);
        if (black){
            for (Position mapPosition : _moveMap) {
                mapPosition.setY((short) (mapPosition.getY() * -1));
            }
        }
    }


    @Override
    protected List<Position> getPossibleMoves() {
        Field field = Field.getInstance();
        List<Position> unchekedPositions = super.getPossibleMoves();
        Position leftPosition = new Position(
                this.getPosition().getX()+_leftMove.getX(),
                this.getPosition().getY()+_leftMove.getY()
        );
        Position rightPosition = new Position(
                this.getPosition().getX()+_rightMove.getX(),
                this.getPosition().getY()+_rightMove.getY()
        );
        Position doublePosition = new Position(
                this.getPosition().getX()+_doubleMove.getX(),
                this.getPosition().getY()+_doubleMove.getY()
        );

        for (int i = 0; i < unchekedPositions.size(); ) {
            Position position = unchekedPositions.get(i);
            if (position.equals(rightPosition) || position.equals(leftPosition)) {
                if (field.isPositionBusy(position) && isSameColor(field.getFigureByPosition(position))) {
                    unchekedPositions.remove(position);
                    continue;
                }
                if (!field.isPositionBusy(position)) {
                    unchekedPositions.remove(position);
                    continue;
                }
            }
            if (position.equals(doublePosition)) {
                if (this.isWasMove()){
                    unchekedPositions.remove(position);
                    continue;
                }
            }
            i++;
        }
        return unchekedPositions;
    }

}
