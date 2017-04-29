package gsanders;

import gsanders.generator.Generator;
import gsanders.generator.GeneratorFactory;

/**
 * @author G. Sanders
 * @since 29-04-17.
 * <p>
 * License: CC BY-SA 4.0 <a href=https://creativecommons.org/licenses/by-sa/4.0/>https://creativecommons.org/licenses/by-sa/4.0/</a>
 */
public class Run {

    public static void main(String[] args) {
        Generator generator = GeneratorFactory.createGenerator();
        World result = generator.generate();
        result.saveToImage();
    }
}
