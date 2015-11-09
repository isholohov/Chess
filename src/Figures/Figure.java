package Figures;

import Core.Exceptions.KingCantMoveException;
import Figures.Action.Move;
import Game.Field;
import Util.Position;

import java.util.LinkedList;
import java.util.List;

public abstract class Figure {
    private boolean _black;
    protected Position _position;
    private boolean _dead;
    private boolean wasMove = false;

    public boolean isDead() {
        return _dead;
    }

    public void setDead() {
        _dead = true;
    }

    public void setLife() { _dead = false; }

    public boolean isWasMove() {
        return wasMove;
    }

    protected List<Position> _moveMap = new LinkedList<>();

    protected Move possibleMovesGetter;

    public Figure(boolean _black, Position _position) {
        this._black = _black;
        this._position = _position;
        this._dead = false;
    }

    public void move(Position newPosition) {
        _position = newPosition;
        wasMove = true;
    }

    protected boolean checkMoveFigure(Position position) throws KingCantMoveException {
        List<Position> possibleMoves = getPossibleMoves();
        if (this instanceof King && possibleMoves.isEmpty()) {
            throw new KingCantMoveException();
        }
        for (Position checkPosition : possibleMoves) {
            if (checkPosition.equals(position)) {
                return true;
            }
        }
        return false;
    }

    protected List<Position> getPossibleMoves() {
        return possibleMovesGetter.getPossibleMoves(this, _moveMap);
    }

    public boolean canMove(Position position) throws KingCantMoveException {
        boolean result = false;
        Field field = Field.getInstance();
        if (!field.isOutOfBorder(position)) {
            result = checkMoveFigure(position);
        }
        return result;
    }


    public Position getPosition() {
        return _position;
    }

    public boolean isBlack() {
        return _black;
    }

    public boolean isSameColor(Figure figure) {
        return this.isBlack() == figure.isBlack();
    }
}
