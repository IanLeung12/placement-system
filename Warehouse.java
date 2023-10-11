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

    public Warehouse(int warehouseID, final int MAX_BOXES, final int MAX_TRUCKS, ArrayList<Box> boxes,
            ArrayList<Truck> trucks) {
        this.warehouseID = warehouseID;
        this.boxes = boxes;
        this.trucks = trucks;

        this.MAX_BOXES = MAX_BOXES;
        this.MAX_TRUCKS = MAX_TRUCKS;
        Collections.sort(boxes);
    }

    public Warehouse(int warehouseID, final int MAX_BOXES, final int MAX_TRUCKS, Box[] boxes, Truck[] trucks) {
        this.warehouseID = warehouseID;

        this.boxes = new ArrayList<Box>(Arrays.asList(boxes));
        this.trucks = new ArrayList<Truck>(Arrays.asList(trucks));

        this.MAX_BOXES = MAX_BOXES;
        this.MAX_TRUCKS = MAX_TRUCKS;
    }

    public void addBox(Box box) {
        this.boxes.add(box);
    }

    public void addTruck(Truck truck) {
        this.trucks.add(truck);
    }

    public void removeBox(int boxID) {
        for (int i = 0; i < this.boxes.size(); i++) {
            if (this.boxes.get(i).getId() == boxID) {
                this.boxes.remove(i);
                i --;
            }
        }
    }

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
