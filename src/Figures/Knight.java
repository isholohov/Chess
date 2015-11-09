package Figures;

import Figures.Action.MapMoveImpl;
import Util.Position;

public class Knight extends Figure {

    {
        _moveMap.add(new Position(-2, 1));
        _moveMap.add(new Position(-1, 2));
        _moveMap.add(new Position(1, 2));
        _moveMap.add(new Position(2, 1));
        _moveMap.add(new Position(2, -1));
        _moveMap.add(new Position(1, -2));
        _moveMap.add(new Position(-1, -2));
        _moveMap.add(new Position(-2, -1));

        possibleMovesGetter = new MapMoveImpl();
    }

    public Knight(boolean _black, Position _position) {
        super(_black, _position);
    }

}
