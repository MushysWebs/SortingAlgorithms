package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import shapes.*;

public class ReadShapes {

    public static Shape[] readShapesFromFile(String fileName) throws IOException {
        // Use the given file path directly
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        int numberOfShapes = Integer.parseInt(lines.get(0));
        Shape[] shapes = new Shape[numberOfShapes];

        for (int i = 1; i <= numberOfShapes; i++) {
            String[] tokens = lines.get(i).split(" ");
            String type = tokens[0];
            double height = Double.parseDouble(tokens[1]);
            double dimension = Double.parseDouble(tokens[2]);

            switch (type) {
                case "Cylinder":
                    shapes[i - 1] = new Cylinder(height, dimension);
                    break;
                case "Cone":
                    shapes[i - 1] = new Cone(height, dimension);
                    break;
                case "Pyramid":
                    shapes[i - 1] = new Pyramid(height, dimension);
                    break;
                case "SquarePrism":
                    shapes[i - 1] = new SquarePrism(height, dimension);
                    break;
                case "TriangularPrism":
                    shapes[i - 1] = new TriangularPrism(height, dimension);
                    break;
                case "PentagonalPrism":
                    shapes[i - 1] = new PentagonalPrism(height, dimension);
                    break;
                case "OctagonalPrism":
                    shapes[i - 1] = new OctagonalPrism(height, dimension);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape type: " + type);
            }
        }

        return shapes;
    }
}
