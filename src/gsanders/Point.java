package gsanders;

import java.util.Objects;

/**
 * @author G. Sanders
 * @since 29-04-17.
 * <p>
 * License: CC BY-SA 4.0 <a href=https://creativecommons.org/licenses/by-sa/4.0/>https://creativecommons.org/licenses/by-sa/4.0/</a>
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates may not be negative");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return (other.x == this.x && other.y == this.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point halfWay(Point otherpoint) {
        int newX = (x + otherpoint.x) / 2;
        int newY = (y + otherpoint.y) / 2;
        return new Point(newX, newY);
    }

}
