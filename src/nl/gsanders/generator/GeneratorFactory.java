package nl.gsanders.generator;

import nl.gsanders.Point;
import nl.gsanders.World;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author G. Sanders
 * @since 29-04-17.
 * <p>
 * License: CC BY-SA 4.0 <a href=https://creativecommons.org/licenses/by-sa/4.0/>https://creativecommons.org/licenses/by-sa/4.0/</a>
 */
public class GeneratorFactory {


    public static Generator createGenerator() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("resources/generator.properties"));
        } catch (IOException e) {
            System.err.println("Could not open properties file. Using defaults.");
        }

        World world = new World(
                parsePropertyToInt(properties, "xSize", 2000),
                parsePropertyToInt(properties, "ySize", 2000));

        // Store triangle as a list because we need to retrieve entries by index number.
        List<Point> triangle = Collections.unmodifiableList(
                new ArrayList<>(createAndValidateTriangle(properties, world)));

        int steps = parsePropertyToInt(properties, "steps", 1_000_000);

        return new Generator(world, triangle, steps);

    }

    private static Set<Point> createAndValidateTriangle(Properties properties, World world) {
        Set<Point> triangle = new HashSet<>();
        triangle.add(new Point(
                parsePropertyToInt(properties, "point1X", 0),
                parsePropertyToInt(properties, "point1Y", 0)));
        triangle.add(new Point(
                parsePropertyToInt(properties, "point2X", 1000),
                parsePropertyToInt(properties, "point2Y", 1999)));
        triangle.add(new Point(
                parsePropertyToInt(properties, "point3X", 1999),
                parsePropertyToInt(properties, "point3Y", 0)));

        if (triangle.size() != 3) {
            throw new IllegalArgumentException("Exactly three sets of unique coordinates are required for the triangle");
        }

        for (Point point : triangle) {
            if (!world.isInWorld(point)) {
                throw new IllegalArgumentException("The vertices of the triangle need to be inside the world");
            }

        }

        return triangle;
    }

    private static int parsePropertyToInt(Properties properties, String property, int defaultValue) {
        String propertyValue = properties.getProperty(property);
        return propertyValue != null ? Integer.parseInt(propertyValue) : defaultValue;
    }
}
