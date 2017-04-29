package gsanders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author G. Sanders
 * @since 29-04-17.
 * <p>
 * License: CC BY-SA 4.0 <a href=https://creativecommons.org/licenses/by-sa/4.0/>https://creativecommons.org/licenses/by-sa/4.0/</a>
 */
public class World {

    final private int[][] pixelArray;

    public World(int xSize, int ySize) {
        if (xSize <= 0 || ySize <= 0 ) {
            throw new IllegalArgumentException("World size must be larger than zero");
        }
        pixelArray = new int[ySize][xSize];
    }

    public void placePixel(Point point) {
        pixelArray[point.getY()][point.getX()] = 1;
    }

    public int getXSize() {
        return pixelArray[0].length;
    }

    public int getYSize() {
        return pixelArray.length;
    }

    public boolean isInWorld(Point point) {
        return point.getX() < (getXSize()) && point.getY() < (getYSize()) ;
        // No negative check because Points can't be negative.
    }

    public void saveToImage() {
        BufferedImage image = new BufferedImage(getXSize(), getYSize(), 1);
        int largestYvalue = getYSize() - 1;
        for (int y = 0; y < getYSize(); y++) {
            for (int x = 0; x < getXSize(); x++) {
                if (pixelArray[y][x] == 1) {
                    image.setRGB(x, largestYvalue - y, 0);
                } else {
                    image.setRGB(x, largestYvalue - y, 0xffffff);
                }
            }
        }
        try {
            ImageIO.write(image, "png", new File("out.png"));
        } catch (IOException e) {
            System.err.println("Failed to write image file");
        }

    }





}
