import java.awt.Color;

public class Box implements Comparable<Box>{
    private final int boxID;
    private int weight;
    private final int height;
    private int length;
    private int width;
    private int positionXInTruck;
    private int positionYInTruck;
    private int positionZInTruck;
    private Color color;

    public Box(int id, int weight, int height, int length, int width, Color color) {
        this.boxID = id;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.positionXInTruck = -1;
        this.positionYInTruck = -1;
        this.positionZInTruck = -1;
        this.color = color;
    }
    public Box(int id, int weight, int height, int length, int width, int x, int y, int z, Color color) {
        this.boxID = id;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.positionXInTruck = x;
        this.positionYInTruck = y;
        this.positionZInTruck = z;
        this.color = color;
    }

    public void rotate() {
        int tempLength = this.width;
        this.width = length;
        this.length = tempLength;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Box other) {
        if (this.width > this.length) {
            this.rotate();
        }
        if (other.width > other.length) {
            other.rotate();
        }
        return this.length - other.length;
    }
}
