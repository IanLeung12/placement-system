/**
 * [Truck.java]
 * This class simulates a truck
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Truck {
    private final int truckId;
    private final int maxWeight;
    private final int height;
    private final int length;
    private final int width;
    private JButton truckButton;
    private final ArrayList<Box> boxes;

    private final ImageIcon truckImage = new ImageIcon("out/display/Truck.png");

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
             if (this.boxes.get(i).getBoxID() == boxId) {
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
    public int getTruckID() {
        return truckId;
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

    public String toString() {
        StringBuilder string = new StringBuilder("Truck");
        int[] values = ReceivingSystem.asArray(this);
        int[] var3 = values;
        int var4 = values.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int value = var3[var5];
            string.append(" ");
            string.append(value);
        }

        Iterator var7 = this.boxes.iterator();

        while(var7.hasNext()) {
            Box box = (Box)var7.next();
            string.append("\n");
            string.append(box.toString());
        }

        return string.toString();
    }

    public String toFileFormat() {
        return this.truckId + "\n" + this.maxWeight + "\n" + this.height + "\n" + this.length + "\n" + this.width;
    }

    /**
     * draw
     * draws the truck
     * @param g           Graphics object from GraphicsPanel
     * @param x           int, x coordinate for the truck to be drawn
     * @param y           int, y coordinate for the truck to be drawn
     * @param length      int, the length
     * @param width       int, the width
     * @param scaleFactor double, the factor by which the size of the truck is scaled
     */
    public void draw(Graphics g, int x, int y, int length, int width, double scaleFactor) {
        g.drawRect(x, y, (int) (width * scaleFactor), (int) (length * scaleFactor));
    }

    /**
     * drawList
     * draws the button of the truck, which redirects to a display of the truck
     * @param graphicsPanel
     * @return JButton
     */
    public JButton drawList(JPanel graphicsPanel) {
        truckButton = new JButton("Truck " + getTruckID(), truckImage);
        truckButton.setOpaque(true);
        truckButton.setContentAreaFilled(false);
        truckButton.setFocusPainted(false);
        graphicsPanel.add(truckButton);
        graphicsPanel.repaint();
        return truckButton;
    }

    /**
     * removeButton
     * removes buttons from the graphics panel
     * @param graphicsPanel
     */
    public void removeButton(JPanel graphicsPanel){
        graphicsPanel.remove(truckButton);
    }

    /**
     * findBox
     * finds a box within the boxes ArrayList
     * @param id
     * @return Box
     */
    public int findBox(int id) {
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getBoxID() == id) {
                return i;
            }
        }
        return -1;
    }
}