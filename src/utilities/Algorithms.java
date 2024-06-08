package utilities;

import java.util.Comparator;

import shapes.Shape;

public class Algorithms {

    public static void bubbleSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(shapes[j], shapes[j + 1]) > 0) {
                    Shape temp = shapes[j];
                    shapes[j] = shapes[j + 1];
                    shapes[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;  // If no elements were swapped, the array is sorted
        }
    }

    public static void insertionSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;
        for (int i = 1; i < n; i++) {
            Shape key = shapes[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(shapes[j], key) > 0) {
                shapes[j + 1] = shapes[j];
                j = j - 1;
            }
            shapes[j + 1] = key;
        }
    }

    public static void selectionSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(shapes[j], shapes[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            Shape temp = shapes[minIdx];
            shapes[minIdx] = shapes[i];
            shapes[i] = temp;
        }
    }

    public static void mergeSort(Shape[] shapes, Comparator<Shape> comparator) {
        if (shapes.length < 2) return;
        int mid = shapes.length / 2;
        Shape[] left = new Shape[mid];
        Shape[] right = new Shape[shapes.length - mid];

        System.arraycopy(shapes, 0, left, 0, mid);
        System.arraycopy(shapes, mid, right, 0, shapes.length - mid);

        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(shapes, left, right, comparator);
    }

    private static void merge(Shape[] shapes, Shape[] left, Shape[] right, Comparator<Shape> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                shapes[k++] = left[i++];
            } else {
                shapes[k++] = right[j++];
            }
        }
        while (i < left.length) {
            shapes[k++] = left[i++];
        }
        while (j < right.length) {
            shapes[k++] = right[j++];
        }
    }

    public static void quickSort(Shape[] shapes, Comparator<Shape> comparator) {
        quickSort(shapes, 0, shapes.length - 1, comparator);
    }

    private static void quickSort(Shape[] shapes, int low, int high, Comparator<Shape> comparator) {
        if (low < high) {
            int pi = partition(shapes, low, high, comparator);
            quickSort(shapes, low, pi - 1, comparator);
            quickSort(shapes, pi + 1, high, comparator);
        }
    }

    private static int partition(Shape[] shapes, int low, int high, Comparator<Shape> comparator) {
        Shape pivot = shapes[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(shapes[j], pivot) < 0) {
                i++;
                Shape temp = shapes[i];
                shapes[i] = shapes[j];
                shapes[j] = temp;
            }
        }
        Shape temp = shapes[i + 1];
        shapes[i + 1] = shapes[high];
        shapes[high] = temp;
        return i + 1;
    }

    public static void heapSort(Shape[] shapes, Comparator<Shape> comparator) {
        int n = shapes.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(shapes, n, i, comparator);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            Shape temp = shapes[0];
            shapes[0] = shapes[i];
            shapes[i] = temp;

            // call max heapify on the reduced heap
            heapify(shapes, i, 0, comparator);
        }
    }

    private static void heapify(Shape[] shapes, int n, int i, Comparator<Shape> comparator) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        if (left < n && comparator.compare(shapes[left], shapes[largest]) > 0)
            largest = left;

        if (right < n && comparator.compare(shapes[right], shapes[largest]) > 0)
            largest = right;

        if (largest != i) {
            Shape swap = shapes[i];
            shapes[i] = shapes[largest];
            shapes[largest] = swap;

            heapify(shapes, n, largest, comparator);
        }
    }
}
