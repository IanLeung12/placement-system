import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Warehouse
 * @author Michael Khart, Dilen De Silva
 * ICS4UE
 * @version 1.0 - 2023/10/09
 * Warehouse stores all the boxes and trucks
 * It is able to add and delete them if needed
 */

public class Warehouse {
    private int WarehouseID;
    private int MAX_BOXES;
    private int MAX_TRUCKS;
    private ArrayList<Box> inventory = new ArrayList<>();
    private ArrayList<Truck> trucks = new ArrayList<>();


    public Warehouse() {}

    /**
     * Warehouse
     * @param id - the id of warehosue
     * @param maxBoxes - maxboxes of warehouse
     * @param maxTrucks - max trucks of warehouse
     */
    public Warehouse(int id, int maxBoxes, int maxTrucks) {
        this.WarehouseID = id;
        this.MAX_BOXES = maxBoxes;
        this.MAX_TRUCKS = maxTrucks;
    }


    /**
     * addBox
     * adds a box to the warehouse inventory
     * @param box - the box to add
     */
    public void addBox(Box box) {
        if (this.inventory.size() + 1 > this.MAX_BOXES) {
            throw new RuntimeException("Too many boxes in warehouse");
        } else {
            this.inventory.add(box);
        }
    }

    /**
     * removeBox
     * removes box
     * @param boxID - the id of which box to remove
     */
    public void removeBox(int boxID) {

        for (int b = 0; b < this.inventory.size(); b ++){
            if(boxID == this.inventory.get(b).getBoxID()){
                this.inventory.remove(b);
                b--;
            }
        }

        for (Truck truck : trucks) {
            truck.removeBox(boxID);
        }
    }

    /**
     * addTruck
     * adds the truck to warehouse list
     * @param truck - truck to add
     */
    public void addTruck(Truck truck) {
        if (this.trucks.size() + 1 > this.MAX_TRUCKS) {
            throw new RuntimeException("Too many trucks in warehouse");
        } else {
            this.trucks.add(truck);
        }
    }

    /**
     * removeTruck
     * removes the truck from list
     * @param truckID - id of truck to remove
     */
    public void removeTruck(int truckID) {

        for (Truck truck : this.trucks) {
            if (truck.getTruckID() == truckID) {
                this.inventory.addAll(truck.getBoxes());
            }
        }

        this.trucks.removeIf(truck -> truck.getTruckID() == truckID);
    }

    // will be overridden
    public void draw(Graphics g) {    }

    /**
     * toString override
     * will make a string out of warehouse and values - used in uplaoding info to files
     * @return - the string object
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Warehouse"); // gets object type and its initation values into string
        int[] values = ReceivingSystem.asArray(this);

        for (int value : values) { // adds all values
            string.append(" ");
            string.append(value);
        }


        for (Box box : this.inventory) { // adds all string representation of boxes in inventory to string
            string.append("\n");
            string.append(ReceivingSystem.toStringForWarehouse(box));
        }

        for (Truck truck : this.trucks) { // adds all string representation of trucks and their boxes to string
            string.append("\n");
            string.append(truck.toString());
        }

        return string.toString();
    }

    /**
     * getWarehouseID
     * Gets the ID of the warehouse.
     * @return - The warehouse ID
     */
    public int getWarehouseID() {
        return WarehouseID;
    }

    /**
     * setWarehouseID
     * Sets the ID of the warehouse.
     * @param warehouseID - The new warehouse ID to set
     */
    public void setWarehouseID(int warehouseID) {
        WarehouseID = warehouseID;
    }

    /**
     * getMAX_BOXES
     * Gets the maximum number of boxes allowed in the warehouse.
     * @return - The maximum number of boxes
     */
    public int getMAX_BOXES() {
        return MAX_BOXES;
    }

    /**
     * setMAX_BOXES
     * Sets the maximum number of boxes allowed in the warehouse.
     * @param MAX_BOXES - The new maximum number of boxes to set
     */
    public void setMAX_BOXES(int MAX_BOXES) {
        this.MAX_BOXES = MAX_BOXES;
    }

    /**
     * getMAX_TRUCKS
     * Gets the maximum number of trucks allowed in the warehouse.
     * @return - The maximum number of trucks
     */
    public int getMAX_TRUCKS() {
        return MAX_TRUCKS;
    }

    /**
     * setMAX_TRUCKS
     * Sets the maximum number of trucks allowed in the warehouse.
     * @param MAX_TRUCKS - The new maximum number of trucks to set
     */
    public void setMAX_TRUCKS(int MAX_TRUCKS) {
        this.MAX_TRUCKS = MAX_TRUCKS;
    }

    /**
     * getInventory
     * Gets the inventory of boxes in the warehouse.
     * @return - A list of boxes in the inventory
     */
    public ArrayList<Box> getInventory() {
        return inventory;
    }

    /**
     * setInventory
     * Sets the inventory of boxes in the warehouse.
     * @param inventory - The new inventory of boxes to set
     */
    public void setInventory(ArrayList<Box> inventory) {
        this.inventory = inventory;
    }

    /**
     * getTrucks
     * Gets the list of trucks in the warehouse.
     * @return - A list of trucks
     */
    public ArrayList<Truck> getTrucks() {
        return trucks;
    }

    /**
     * setTrucks
     * Sets the list of trucks in the warehouse.
     * @param trucks - The new list of trucks to set
     */
    public void setTrucks(ArrayList<Truck> trucks) {
        this.trucks = trucks;
    }

}