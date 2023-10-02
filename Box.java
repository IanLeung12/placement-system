import java.awt.Color;

public class Box {
    private int boxID;
    private int weight;
    private int height;
    private int length;
    private int width;
    private int positionXInTruck;
    private int positionYInTruck;
    private int positionZInTruck;
    private Color color;

    Box(int id, int weight, int height, int length, int width, int x, int y, int z) {
        this.boxID = id;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.positionXInTruck = x;
        this.positionYInTruck = y;
        this.positionZInTruck = z;
    }

    public void rotate() {
        int tempLength = this.width;
        this.width = length;
        this.length = tempLength;
    }

}
