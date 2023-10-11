import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.util.ArrayList;

public class Truck {
    private int truckId;
    private int maxWeight;
    private int loadedWeight;
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
        this.loadedBoxes = new ArrayList<>();
        this.spaceArray = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                spaceArray[i][j] = 0;
            }
        }
    }

    /*
     * // creating a truck with pending boxes to be loaded
     * public Truck(int truckId, int maxWeight, int height, int length, int width,
     * ArrayList<Box> boxes) {
     * this.truckId = truckId;
     * this.maxWeight = maxWeight;
     * this.height = height;
     * this.length = length;
     * this.width = width;
     * this.boxes = boxes;
     * }
     */

    // rotates the truck
//    public void rotate() {
//        int newWidth = spaceArray.length;
//        int newLength = spaceArray[0].length;
//        int[][] newSpaceArray = new


    // add box to the pending boxes list
    public void addBox(Box box) {
        loadedBoxes.add(box);
        loadedWeight = loadedWeight + box.getWeight();
        for (int i = box.getPositionYInTruck(); i < box.getPositionYInTruck() + box.getLength(); i ++) {
            for (int j = box.getPositionXInTruck(); j < box.getPositionXInTruck() + box.getWidth(); j ++) {
                spaceArray[i][j] = loadedBoxes.size() % 9 + 1;
            }
        }
    }

    // remove box from the pending boxes list
    public void removeBox(int boxId) {
        /*
         * for (int i = 0; i < boxes.size(); i++) {
         * if (boxes.get(i).getId() == boxId) {
         * boxes.get;
         * }
         * }
         */
    }

    // return th current loaded weight of the truck
    public int getLoadedWeight() {
        return loadedWeight;
    }

    public boolean isValid(Box box) {
        return (loadedWeight + box.getWeight() <= maxWeight) && (box.getHeight() <= height);
    }

    public boolean isValid(Box[] boxes) {
        return ((loadedWeight + boxes[0].getWeight() + boxes[1].getWeight()) <= maxWeight) &&
                (boxes[0].getHeight() <= height);
    }

    // return the truck as ______________________________________
    @Override
    public String toString() {
        return "";
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Box> getLoadedBoxes() {
        return loadedBoxes;
    }

    public int[][] getSpaceArray() {
        return spaceArray;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}