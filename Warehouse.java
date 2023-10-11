/**
 * [Warehouse.java]
 * This class simulates a warehouse storing boxes and trucks
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Warehouse {

    final private int warehouseID;
    final private ArrayList<Box> boxes;
    final private ArrayList<Truck> trucks;

    final private int MAX_BOXES;
    final private int MAX_TRUCKS;

    /**
     * Warehouse
     * creates a warehouse with boxes and trucks
     * @param warehouseID the warehouse id
     * @param MAX_BOXES the max boxes in the warehouse
     * @param MAX_TRUCKS the max trucks in your warehouse
     * @param boxes the boxes in the warehouse
     * @param trucks the trucks in the warehouse
     */
    public Warehouse(int warehouseID, final int MAX_BOXES, final int MAX_TRUCKS, ArrayList<Box> boxes,
            ArrayList<Truck> trucks) {
        this.warehouseID = warehouseID;
        this.boxes = boxes;
        this.trucks = trucks;

        this.MAX_BOXES = MAX_BOXES;
        this.MAX_TRUCKS = MAX_TRUCKS;
    }

    /**
     * addBox
     * adds a box to the warehouse
     * @param box the box to add
     */
    public void addBox(Box box) {
        this.boxes.add(box);
    }

    /**
     * addTruck
     * adds a truck to the warehouse
     * @param truck the truck to add
     */
    public void addTruck(Truck truck) {
        this.trucks.add(truck);
    }

    /**
     * removeBox
     * removes a box from the warehouse
     * @param boxID the id of the box
     */
    public void removeBox(int boxID) {
        for (int i = 0; i < this.boxes.size(); i++) {
            if (this.boxes.get(i).getId() == boxID) {
                this.boxes.remove(i);
                i --;
            }
        }
    }

    /**
     * removeTruck
     * removes a truck from the warehouse
     * @param truckID the id of the truck
     */
    public void removeTruck(int truckID) {
        for (int i = 0; i < this.trucks.size(); i++) {
            if (this.trucks.get(i).getTruckId() == truckID) {
                this.trucks.remove(i);
                i --;
            }
        }
    }


    public void loadBoxes(File f) throws IOException {

    }

    public void loadTrucks(File f) throws IOException {

    }

    public void draw(Graphics g) {

    }

    @Override
    public String toString() {
        return null;
    }
}
