import java.util.ArrayList;

public class Truck {
    private int truckId;
    private int maxWeight;
    private int height;
    private int length;
    private int width;

    // private ArrayList<Box> boxes; // list of boxes to be loaded

    private ArrayList<Box> loadedBoxes; // list of boxes that has been loaded to the truck
    private int[][] spaceArray;

    // creating a truck with no boxes to be loaded
    public Truck(int truckId, int maxWeight, int height, int length, int width) {
        this.truckId = truckId;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.spaceArray = new int[length][width];
    }

    /*
    // creating a truck with pending boxes to be loaded
    public Truck(int truckId, int maxWeight, int height, int length, int width, ArrayList<Box> boxes) {
        this.truckId = truckId;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.boxes = boxes;
    }
    */
    
    // add box to the pending boxes list
    public void addBox(Box box) {
    }
    // remove box from the pending boxes list
    public void removeBox(int boxId) {
        /*
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getId() == boxId) {
                boxes.get;
            }
        }
        */
    }
    // return th current loaded weight of the truck
    public int getLoadedWeight() {
        int weight = 0;
        for (Box b: loadedBoxes) {
            weight += b.getWeight();
        }
        return weight;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }



    // return the truck as ______________________________________
    @Override
    public String toString() {
        return "";
    }

}