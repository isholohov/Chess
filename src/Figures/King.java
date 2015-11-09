package Figures;

import Figures.Action.MapMoveImpl;
import Game.Field;
import Util.Position;

import java.util.LinkedList;
import java.util.List;

public class King extends Figure {
    private Position _longCastling;
    private Position _shortCastling;
    private Position _longCastlingForRook = new Position(3, 0);
    private Position _shortCastlingForRook = new Position(-2, 0);
    private List<Position> _unoccupiedSquaresForLongCastling = new LinkedList<>();
    private List<Position> _unoccupiedSquaresForShortCastling = new LinkedList<>();


    {
        _unoccupiedSquaresForLongCastling.add(new Position(-1, 0));
        _unoccupiedSquaresForLongCastling.add(new Position(-2, 0));
        _unoccupiedSquaresForLongCastling.add(new Position(-3, 0));
        _unoccupiedSquaresForShortCastling.add(new Position(1, 0));
        _unoccupiedSquaresForShortCastling.add(new Position(2, 0));
        _longCastling = new Position(-2, 0);
        _shortCastling = new Position(2, 0);
        _moveMap.add(new Position(-1, 1));
        _moveMap.add(new Position(0, 1));
        _moveMap.add(new Position(1, 1));
        _moveMap.add(new Position(1, 0));
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, -1));
        _moveMap.add(new Position(-1, -1));
        _moveMap.add(new Position(-1, 0));
        _moveMap.add(_shortCastling);
        _moveMap.add(_longCastling);

        possibleMovesGetter = new MapMoveImpl();
    }

    public King(boolean _black, Position _position) {
        super(_black, _position);
    }

    @Override
    public void move(Position newPosition) {
        Field field = Field.getInstance();

        Position positionLongCastling = new Position(
                this.getPosition().getX() + _longCastling.getX(),
                this.getPosition().getY() + _longCastling.getY()
        );
        Position positionShortCastling = new Position(
                this.getPosition().getX() + _shortCastling.getX(),
                this.getPosition().getY() + _shortCastling.getY()
        );
        if (newPosition.equals(positionLongCastling)) {
            for (Figure figure : field.getFigures()) {
                if (this.isSameColor(figure) && figure instanceof Rook && this.getPosition().righterThen(figure.getPosition())) {
                    figure.move(new Position(figure.getPosition().getX() + _longCastlingForRook.getX(),
                           figure.getPosition().getY() + _longCastlingForRook.getY()));
                    System.out.println("Long castling");
                }
            }
        } else if (newPosition.equals(positionShortCastling)){
            for (Figure figure : field.getFigures()) {
                if (this.isSameColor(figure) && figure instanceof Rook && this.getPosition().lefterThen(figure.getPosition())) {
                    figure.move(new Position(figure.getPosition().getX() + _shortCastlingForRook.getX(),
                            figure.getPosition().getY() + _shortCastlingForRook.getY()));
                    System.out.println("Short castling");
                }
            }
        }


        super.move(newPosition);

    }

    @Override
    protected List<Position> getPossibleMoves() {
        List<Position> positionsToCheck = super.getPossibleMoves();
        Field field = Field.getInstance();

        for (int i = 0; i < _unoccupiedSquaresForLongCastling.size(); i++) {
            _unoccupiedSquaresForLongCastling.set(i, new Position(
                            this.getPosition().getX() + _unoccupiedSquaresForLongCastling.get(i).getX(),
                            this.getPosition().getY() + _unoccupiedSquaresForLongCastling.get(i).getY())
            );
        }

        for (int i = 0; i < _unoccupiedSquaresForShortCastling.size(); i++) {
            _unoccupiedSquaresForShortCastling.set(i, new Position(
                            this.getPosition().getX() + _unoccupiedSquaresForShortCastling.get(i).getX(),
                            this.getPosition().getY() + _unoccupiedSquaresForShortCastling.get(i).getY())
            );
        }

        Position positionLongCastling = new Position(
                this.getPosition().getX() + _longCastling.getX(),
                this.getPosition().getY() + _longCastling.getY()
        );
        Position positionShortCastling = new Position(
                this.getPosition().getX() + _shortCastling.getX(),
                this.getPosition().getY() + _shortCastling.getY()
        );
        for (int i = 0; i < positionsToCheck.size(); ) {
            Position positionToCheckForCastling = positionsToCheck.get(i);
            if (positionToCheckForCastling.equals(positionLongCastling)) {
                for (Figure figure : field.getFigures()) {
                    if (this.isSameColor(figure) && figure instanceof Rook && this.getPosition().righterThen(figure.getPosition())) {
                        if (figure.isWasMove() || this.isWasMove()) {
                            for (Position position : _unoccupiedSquaresForLongCastling) {
                                if (field.isPositionBusy(position)) {
                                    positionsToCheck.remove(positionLongCastling);
                                    continue;
                                }
                            }
                        }
                    }
                }


            } else if (positionToCheckForCastling.equals(positionShortCastling)) {
                for (Figure figure : field.getFigures()) {
                    if (this.isSameColor(figure) && figure instanceof Rook && this.getPosition().lefterThen(figure.getPosition())) {
                        if (figure.isWasMove() || this.isWasMove()) {
                            for (Position position : _unoccupiedSquaresForShortCastling)
                                if (field.isPositionBusy(position)) {
                                    positionsToCheck.remove(positionLongCastling);
                                    continue;
                                }
                        }
                    }
                }


            }
            i++;
        }


        for (int i = 0; i < positionsToCheck.size(); ) {
            Position kingPositionToMove = positionsToCheck.get(i);
            if (field.getFigureByPosition(kingPositionToMove) != null) {
                field.getFigureByPosition(kingPositionToMove).setDead();
            }
            for (Figure figure : field.getFigures()) {
                if (!this.isSameColor(figure) && !(figure instanceof King)) {
                    for (Position enemyPosition : figure.getPossibleMoves()) {
                        if (enemyPosition.equals(kingPositionToMove)) {
                            positionsToCheck.remove(kingPositionToMove);
                            continue;
                        }
                    }
                }
            }
            if (field.getFigureByPosition(kingPositionToMove) != null) {
                field.getFigureByPosition(kingPositionToMove).setLife();
            }
            i++;
        }


        return positionsToCheck;
    }
}
