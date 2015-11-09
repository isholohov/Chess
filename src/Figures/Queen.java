package Figures;

import Figures.Action.VectorMoveImpl;
import Util.Position;

public class Queen extends Figure {
    {
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, -1));
        _moveMap.add(new Position(-1, -1));
        _moveMap.add(new Position(-1, 0));
        _moveMap.add(new Position(-1, 1));
        _moveMap.add(new Position(0, 1));
        _moveMap.add(new Position(1, 1));
        _moveMap.add(new Position(1, 0));

        possibleMovesGetter = new VectorMoveImpl();
    }

    public Queen(boolean _black, Position _position) {
        super(_black, _position);
    }
}
