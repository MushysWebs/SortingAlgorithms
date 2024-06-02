package shapes;
import java.util.Comparator;

public abstract class Shape implements Comparable<Shape> {
    protected double height;

    public double getHeight() {
        return height;
    }

    public abstract double calcBaseArea();
    public abstract double calcVolume();

    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.height, other.height);
    }

    public static Comparator<Shape> byBaseArea = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            return Double.compare(s1.calcBaseArea(), s2.calcBaseArea());
        }
    };

    public static Comparator<Shape> byVolume = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            return Double.compare(s1.calcVolume(), s2.calcVolume());
        }
    };
}
