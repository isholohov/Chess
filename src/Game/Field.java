package Game;

import Figures.Figure;
import Util.Position;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private short _width;
    private short _height;

    public List<Figure> getFigures() {
        return field;
    }

    public short getWidth() {
        return _width;
    }

    public short getHeight() {
        return _height;
    }

    private static class Holder {
        static Field instance = new Field();
    }

    public static Field getInstance() {
        return Holder.instance;
    }

    public final ArrayList<Figure> field = new ArrayList<>(32);

    public void init(short width, short height) {
        _width = width;
        _height = height;
        boolean black = true;
        boolean white = false;
        field.add(new Figures.King(black, new Position(5, 8)));
        field.add(new Figures.King(white, new Position(5, 1)));
        field.add(new Figures.Queen(black, new Position(4, 8)));
        field.add(new Figures.Queen(white, new Position(4, 1)));
        field.add(new Figures.Knight(black, new Position(2, 8)));
        field.add(new Figures.Knight(black, new Position(7, 8)));
        field.add(new Figures.Knight(white, new Position(2, 1)));
        field.add(new Figures.Knight(white, new Position(7, 1)));
        field.add(new Figures.Rook(black, new Position(1, 8)));
        field.add(new Figures.Rook(black, new Position(8, 8)));
        field.add(new Figures.Rook(white, new Position(1, 1)));
        field.add(new Figures.Rook(white, new Position(8, 1)));
        field.add(new Figures.Bishop(black, new Position(3, 8)));
        field.add(new Figures.Bishop(black, new Position(6, 8)));
        field.add(new Figures.Bishop(white, new Position(3, 1)));
        field.add(new Figures.Bishop(white, new Position(6, 1)));
        for (int i = 1; i <= _width; i++) {
            field.add(new Figures.Pawn(black, new Position(i, 7)));
        }
        for (int i = 1; i <= _width; i++) {
            field.add(new Figures.Pawn(white, new Position(i, 2)));
        }
    }

    private Field() {

    }


    public boolean isPositionBusy(Position position) {
        final Figure figureByPosition = getFigureByPosition(position);
        return !(figureByPosition == null || figureByPosition.isDead());
    }

    public boolean isOutOfBorder(Position position) {
//        return (((1 > position.getX()) && (position.getX() < getWidth()+1))
//                && ((1 > position.getY()) && (position.getY() < getHeight()+1)));

        return ((position.getX() < 1) || (position.getX() > getWidth()) || (position.getY() < 1) || (position.getY() > getHeight()));
    }

    public Figure getFigureByPosition(Position position) {
        for (Figure figure : field) {
            if (figure.getPosition().equals(position)) {
                return figure;
            }
        }
        return null;
    }

}
