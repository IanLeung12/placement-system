import java.util.ArrayList;

public class Truck {
    private int truckId;
    private int maxWeight;
    private int height;
    private int length;
    private int width;
    private ArrayList<Box> boxes;

    // creating a truck with no boxes to be loaded
    public Truck(int truckId, int maxWeight, int height, int length, int width) {
        this.truckId = truckId;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
    }
    // creating a truck with pending boxes to be loaded
    public Truck(int truckId, int maxWeight, int height, int length, int width, ArrayList<Box> boxes) {
        this.truckId = truckId;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.boxes = boxes;
    }
    // add box to the pending boxes list
    public void addBox(Box box) {

    }
    // remove box from the pending boxes list
    public void removeBox(int boxId) {

    }
    // return th current loaded weight of the truck
    public int getLoadedWeight() {
        return 0;
    }
    // return the truck as ______________________________________--
    @Override
    public String toString() {
        return "";
    }

}