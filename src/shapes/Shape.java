package shapes;
import java.util.Comparator;

public abstract class Shape implements Comparable<Shape> {
    protected double height;

    public Shape(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

   
    public abstract double calcBaseArea();
    public abstract double calcVolume();

  
    @Override
    public int compareTo(Shape other) {
        return Double.compare(other.height, this.height);  
    }


    public static Comparator<Shape> byBaseArea = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            return Double.compare(s2.calcBaseArea(), s1.calcBaseArea());  
        }
    };

    
    public static Comparator<Shape> byVolume = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            return Double.compare(s2.calcVolume(), s1.calcVolume());  
        }
    };
}
