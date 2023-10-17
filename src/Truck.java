/**
 * [Truck.java]
 * This class simulates a truck
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import java.util.ArrayList;

public class Truck {
    private final int truckId;
    private final int maxWeight;
    private final int height;
    private final int length;
    private final int width;
    private final ArrayList<Box> boxes;

    /**
     * Truck
     * creates an empty truck
     * @param truckId the id
     * @param maxWeight the maximum weight
     * @param height the height
     * @param length the length
     * @param width the width
     */
    public Truck(int truckId, int maxWeight, int height, int length, int width) {
        this.truckId = truckId;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.boxes = new ArrayList<>();
    }

    /**
     * Truck
     * creates a truck with preloaded boxes
     * @param truckId the id
     * @param maxWeight the maximum weight
     * @param height the height
     * @param length the length
     * @param width the width
     * @param boxes the loaded boxes
     */
    public Truck(int truckId, int maxWeight, int height, int length, int width, ArrayList<Box> boxes) {
        this.truckId = truckId;
        this.maxWeight = maxWeight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.boxes = boxes;
    }


    /**
     * addBox
     * adds a box to the truck
     * @param box the box to add
     */
    public void addBox(Box box) {
        this.boxes.add(box);
    }

    /**
     * removeBox
     * removes a box from the truck
     * @param boxId the id of the box
     */
    public void removeBox(int boxId) {
         for (int i = 0; i < this.boxes.size(); i++) {
             if (this.boxes.get(i).getId() == boxId) {
                 this.boxes.remove(i);
                 i --;
             }
         }
    }

    /**
     * getLoadedWeight
     * returns the weight of the load of the truck
     * @return the load weight of the truck
     */
    public int getLoadedWeight() {
        int loadedWeight = 0;
        for (Box box: this.boxes) {
            loadedWeight = loadedWeight + box.getWeight();
        }
        return loadedWeight;
    }

    /**
     * getTruckId
     * returns the truck id
     * @return truckId the id
     */
    public int getTruckId() {
        return truckId;
    }

    @Override
    public String toString() {
        return "";
    }

    /**
     * getHeight
     * returns the height of the truck
     * @return height the truck height
     */
    public int getHeight() {
        return height;
    }

    /**
     * getLength
     * returns the length of the truck
     * @return length the truck length
     */
    public int getLength() {
        return length;
    }

    /**
     * getWidth
     * returns the width of the truck
     * @return width the width of the truck
     */
    public int getWidth() {
        return width;
    }

    /**
     * getBoxes
     * returns the boxes in the truck
     * @return boxes the boxes
     */
    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    /**
     * getMaxWeight
     * returns the max weight of the truck
     * @return maxWeight the max weight
     */
    public int getMaxWeight() {
        return maxWeight;
    }
}