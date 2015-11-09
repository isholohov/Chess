package Util;

public class Position {
    private short _x;
    private short _y;

    public Position(int x, int y) {
        _x = (short) x;
        _y = (short) y;
    }

    public short getX() {
        return _x;
    }

    public void setX(short x) {
        _x = x;
    }

    public short getY() {
        return _y;
    }

    public void setY(short y) {
        _y = y;
    }

    public boolean lefterThen(Position position) {
        return (position.getX() >  this.getX());
    }

    public boolean righterThen(Position position) {
        return (position.getX() < this.getX());
    }

    public boolean lowerTher(Position position) {
        return (position.getY() > this.getY());
    }

    public boolean upperThen(Position position) {
        return (position.getY() < this.getY());
    }

    @Override
    public boolean equals(Object position) {
        short x = ((Position)position).getX();
        short y = ((Position)position).getY();

        return ((this._x == x) && (this._y == y));
    }

    @Override
    public String toString() {
        return _x + "\t" + _y;
    }
}
