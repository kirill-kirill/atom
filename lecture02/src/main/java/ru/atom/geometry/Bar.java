package ru.atom.geometry;

/**
 * Template class for
 */
public class Bar implements Collider {

    private int x;
    private int y;
    private int x1;
    private int y1;

    public Bar(int x, int y, int x1, int y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Bar
        Bar bar = (Bar) o;

        return getX() == bar.getX() && getY() == bar.getY() && getX1() == bar.getX1() && getY1() == bar.getY1() ||
               getX() == bar.getX1() && getY() == bar.getY1() && getX1() == bar.getX() && getY1() == bar.getY() ||
               getX() == bar.getX1() && getY() == bar.getY() && getX1() == bar.getX() && getY1() == bar.getY1();
    }

    @Override
    public boolean isColliding(Collider other) {
        return this.equals(other) || this.intersect(other);
    }

    public boolean intersect(Collider other) {
        if (other instanceof Point) {
            Point point = (Point) other;
            return point.getY() >= getY() && point.getY() <= getY1() && point.getX() >= getX() && point.getX() <= getX1() ||
                   point.getY() >= getY1() && point.getY() <= getY() && point.getX() >= getX1() && point.getX() <= getX();
        } else if (other instanceof Bar) {
            Bar bar = (Bar) other;
            return !(getX() > bar.getX1() || getX1() < bar.getX() || getY1() < bar.getY() || getY() > bar.getY1()) ||
                   !(bar.getX() > getX1() || bar.getX1() < getX() || bar.getY1() < getY() || bar.getY() > getY1());
        }
        throw new UnsupportedOperationException();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }
}
