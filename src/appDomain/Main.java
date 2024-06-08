package appDomain;

import utilities.ReadShapes;
import utilities.Algorithms;
import shapes.Shape;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        String fileName = "";
        String compareType = "";
        String sortAlgorithm = "";

        // Parsing command-line arguments using switch-case
        for (String arg : args) {
            switch (arg.charAt(1)) {
                case 'f':
                case 'F':
                    fileName = arg.substring(2).replace("\"", "");
                    break;
                case 't':
                case 'T':
                    compareType = arg.substring(2).toLowerCase();
                    break;
                case 's':
                case 'S':
                    sortAlgorithm = arg.substring(2).toLowerCase();
                    break;
                default:
                    System.out.println("Unknown argument: " + arg);
                    System.out.println("Usage: java -jar Sort.jar -f<file_name> -t<comparison_type> -s<sorting_algorithm>");
                    System.out.println("Comparison types: h (height), v (volume), a (base area)");
                    System.out.println("Sorting algorithms: b (bubble), i (insertion), s (selection), m (merge), q (quick), z (heap)");
                    return;
            }
        }

        // Validate command-line arguments
        if (fileName.isEmpty() || compareType.isEmpty() || sortAlgorithm.isEmpty()) {
            System.out.println("Usage: java -jar Sort.jar -f<file_name> -t<comparison_type> -s<sorting_algorithm>");
            System.out.println("Comparison types: h (height), v (volume), a (base area)");
            System.out.println("Sorting algorithms: b (bubble), i (insertion), s (selection), m (merge), q (quick), z (heap)");
            return;
        }

        // Handle relative file paths
        File file = new File(fileName);
        if (!file.isAbsolute()) {
            file = new File("res", fileName);
            if (!file.exists()) {
                file = new File(fileName);
            }
        }

        try {
            // Print the file path for debugging
            System.out.println("Reading file: " + file.getAbsolutePath());

            // Read shapes from the specified file using ReadShapes
            Shape[] shapes = ReadShapes.readShapesFromFile(file.getAbsolutePath());

            // Determine the comparator based on the comparison type
            Comparator<Shape> comparator = null;
            switch (compareType) {
                case "h":
                    comparator = Comparator.comparingDouble(Shape::getHeight).reversed();  // Use natural order for height, descending
                    break;
                case "a":
                    comparator = Shape.byBaseArea.reversed();
                    break;
                case "v":
                    comparator = Shape.byVolume.reversed();
                    break;
                default:
                    System.out.println("Invalid comparison type: " + compareType);
                    return;
            }

            // Sort shapes using the specified sorting algorithm and comparator
            long startTime = System.currentTimeMillis();

            switch (sortAlgorithm) {
                case "b":
                    Algorithms.bubbleSort(shapes, comparator);
                    break;
                case "i":
                    Algorithms.insertionSort(shapes, comparator);
                    break;
                case "s":
                    Algorithms.selectionSort(shapes, comparator);
                    break;
                case "m":
                    Algorithms.mergeSort(shapes, comparator);
                    break;
                case "q":
                    Algorithms.quickSort(shapes, comparator);
                    break;
                case "z":
                    Algorithms.heapSort(shapes, comparator);
                    break;
                default:
                    System.out.println("Invalid sorting algorithm: " + sortAlgorithm);
                    return;
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Sorting time: " + (endTime - startTime) + " milliseconds");

            // Print the first, last, and every thousandth value in between
            if (shapes.length > 0) {
                System.out.println("First shape: " + shapes[0]);
                for (int i = 1000; i < shapes.length; i += 1000) {
                    System.out.println("Shape at index " + i + ": " + shapes[i]);
                }
                System.out.println("Last shape: " + shapes[shapes.length - 1]);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
