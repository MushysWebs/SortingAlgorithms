package shapes;

public class PentagonalPrism extends Shape {
    private double side;

    public PentagonalPrism(double height, double side) {
        super(height);
        this.side = side;
    }

    @Override
    public double calcBaseArea() {
        return (5.0 / 4.0) * Math.tan(Math.toRadians(54)) * side * side;
    }

    @Override
    public double calcVolume() {
        return calcBaseArea() * height;
    }

    @Override
    public String toString() {
        return "PentagonalPrism [height=" + height + ", side=" + side + "]";
    }
}
