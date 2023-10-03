import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Warehouse {

    final private int warehouseID;
    final private ArrayList<Box> boxes;
    final private ArrayList<Truck> trucks;

    final private int MAX_BOXES;
    final private int MAX_TRUCKS;

    public Warehouse(int warehouseID, final int MAX_BOXES, final int MAX_TRUCKS, ArrayList<Box> boxes, ArrayList<Truck> trucks) {
        this.warehouseID = warehouseID;
        this.boxes = boxes;
        this.trucks = trucks;

        this.MAX_BOXES = MAX_BOXES;
        this.MAX_TRUCKS = MAX_TRUCKS;
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

    public void removeBox(Box box) {
        this.boxes.remove(box);
    }

    public void removeTruck(Truck truck) {
        this.trucks.remove(truck);
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
