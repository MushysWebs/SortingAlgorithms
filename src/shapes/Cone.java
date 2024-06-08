package shapes;

public class Cone extends Shape {
    private double radius;

    public Cone(double height, double radius) {
        super(height);
        this.radius = radius;
    }

    @Override
    public double calcBaseArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calcVolume() {
        return (1.0 / 3.0) * Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return "Cone [height=" + height + ", radius=" + radius + "]";
    }
}
