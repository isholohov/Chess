package Figures;

import Figures.Action.VectorMoveImpl;
import Util.Position;

public class Bishop extends Figure {
    {
        _moveMap.add(new Position(-1, 1));
        _moveMap.add(new Position(1, 1));
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(-1, -1));

        possibleMovesGetter = new VectorMoveImpl();
    }

    public Bishop(boolean _black, Position _position) {
        super(_black, _position);
    }
}
