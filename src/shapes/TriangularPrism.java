package shapes;

public class TriangularPrism extends Shape {
    private double side;

    public TriangularPrism(double height, double side) {
        super(height);
        this.side = side;
    }

    @Override
    public double calcBaseArea() {
        return (Math.sqrt(3) / 4) * side * side;
    }

    @Override
    public double calcVolume() {
        return calcBaseArea() * height;
    }

    @Override
    public String toString() {
        return "TriangularPrism [height=" + height + ", side=" + side + "]";
    }
}
