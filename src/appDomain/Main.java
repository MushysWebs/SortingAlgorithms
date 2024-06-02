package appDomain;

import utilities.ReadShapes;
import utilities.Algorithms;
import shapes.Shape;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        String fileName = "";
        String compareType = "";
        String sortAlgorithm = "";

        // Parsing command-line arguments
        for (String arg : args) {
            if (arg.startsWith("-f")) {
                fileName = arg.substring(2);
            } else if (arg.startsWith("-t")) {
                compareType = arg.substring(2);
            } else if (arg.startsWith("-s")) {
                sortAlgorithm = arg.substring(2);
            }
        }

        // Validate command-line arguments
        if (fileName.isEmpty() || compareType.isEmpty() || sortAlgorithm.isEmpty()) {
            System.out.println("Usage: java -jar Sort.jar -f<file_name> -t<comparison_type> -s<sorting_algorithm>");
            System.out.println("Comparison types: h (height), v (volume), a (base area)");
            System.out.println("Sorting algorithms: b (bubble), i (insertion), s (selection), m (merge), q (quick), z (heap)");
            return;
        }

        try {
            // Debug: Print the file path to ensure it's correct
            System.out.println("Reading file: " + fileName);

            // Read shapes from the specified file
            Shape[] shapes = ReadShapes.readShapesFromFile("res/" + fileName);

            // Determine the comparator based on the comparison type
            Comparator<Shape> comparator = null;
            switch (compareType.toLowerCase()) {
                case "h":
                    comparator = Comparator.naturalOrder();
                    break;
                case "a":
                    comparator = Shape.byBaseArea;
                    break;
                case "v":
                    comparator = Shape.byVolume;
                    break;
                default:
                    System.out.println("Invalid comparison type: " + compareType);
                    return;
            }

            // Sort shapes using the specified sorting algorithm and comparator
            long startTime = System.currentTimeMillis();

            switch (sortAlgorithm.toLowerCase()) {
                case "b":
                    Arrays.sort(shapes, comparator);
                    Algorithms.bubbleSort(shapes);
                    break;
                case "i":
                    Arrays.sort(shapes, comparator);
                    Algorithms.insertionSort(shapes);
                    break;
                case "s":
                    Arrays.sort(shapes, comparator);
                    Algorithms.selectionSort(shapes);
                    break;
                case "m":
                    Arrays.sort(shapes, comparator);
                    Algorithms.mergeSort(shapes);
                    break;
                case "q":
                    Arrays.sort(shapes, comparator);
                    Algorithms.quickSort(shapes);
                    break;
                case "z":
                    Arrays.sort(shapes, comparator);
                    Algorithms.heapSort(shapes);
                    break;
                default:
                    System.out.println("Invalid sorting algorithm: " + sortAlgorithm);
                    return;
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Sorting time: " + (endTime - startTime) + " milliseconds");

            // Print the first, last, and every thousandth value in between
            System.out.println("First shape: " + shapes[0]);
            if (shapes.length > 1) {
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
