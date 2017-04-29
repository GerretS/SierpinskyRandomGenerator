package gsanders.generator;

import gsanders.Point;
import gsanders.World;

import java.util.List;
import java.util.Random;

/**
 * @author G. Sanders
 * @since 29-04-17.
 * <p>
 * License: CC BY-SA 4.0 <a href=https://creativecommons.org/licenses/by-sa/4.0/>https://creativecommons.org/licenses/by-sa/4.0/</a>
 */
public class Generator {

    final private World world;
    final private List<Point> polygonVertices;
    final private int steps;
    final private Random random = new Random();

    private Point currentPoint;
    private boolean started = false;

    Generator(World world, List<Point> polygonVertices, int steps) {
        this.world = world;
        this.polygonVertices = polygonVertices;
        this.steps = steps;
    }

    public World generate() {
        if (!started) {
            // Draw the polygon vertices
            for (Point point : polygonVertices) {
                world.placePixel(point);
            }

            // Choose a random spot on the world to start.
            currentPoint = new Point(random.nextInt(world.getXSize()), random.nextInt(world.getYSize()));
            world.placePixel(currentPoint);
            started = true;
        }

        // Here's the actual Sierpinsky algorithm.
        for(int i = 0; i < steps; i++) {
            currentPoint = currentPoint.halfWay(polygonVertices.get(random.nextInt(3)));
            world.placePixel(currentPoint);
        }

        return world;

    }

}
