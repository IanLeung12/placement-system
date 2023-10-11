/**
 * [Box.java]
 * This class simulates a box
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import java.awt.Color;

public class Box implements Comparable<Box> {
    private final int boxID;
    private final int weight;
    private final int height;
    private int length;
    private int width;
    private int positionXInTruck;
    private int positionYInTruck;
    private final int positionZInTruck;
    private final Color color;

    /**
     * Box
     * creates a box without a position in a truck
     * @param id the id
     * @param weight the weight
     * @param height the height
     * @param length the length
     * @param width the width
     * @param color the color
     */
    public Box(int id, int weight, int height, int length, int width, Color color) {
        this.boxID = id;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.positionXInTruck = -1;
        this.positionYInTruck = -1;
        this.positionZInTruck = -1;
        this.color = color;
    }

    /**
     * Box
     * creates a box with a position within a truck
     * @param id the id
     * @param weight the weight
     * @param height the height
     * @param length the length
     * @param width the width
     * @param x the x position
     * @param y the y position
     * @param z the z position
     * @param color the color
     */
    public Box(int id, int weight, int height, int length, int width, int x, int y, int z, Color color) {
        this.boxID = id;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.positionXInTruck = x;
        this.positionYInTruck = y;
        this.positionZInTruck = z;
        this.color = color;
    }

    /**
     * rotate
     * rotates the box
     */
    public void rotate() {
        int tempLength = this.width;
        this.width = length;
        this.length = tempLength;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getId() {
        return this.boxID;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getPositionXInTruck() {
        return positionXInTruck;
    }

    public int getPositionYInTruck() {
        return positionYInTruck;
    }

    // temporary toString method

    public String getProps() {
        return "Box ID " + this.boxID + ": (" + this.positionXInTruck + ", " + this.positionYInTruck + ")" + ", (" +
                this.width + ", " + this.length +"), " + this.weight;
    }

    public void setCoords(int x, int y) {
        this.positionXInTruck = x;
        this.positionYInTruck = y;
    }

    /**
     * compareTo
     * compares to another box based on its greatest side length for sorting purposes
     * @param other the object to be compared.
     * @return the difference between their greatest side lengths
     */
    @Override
    public int compareTo(Box other) {
        if (this.length < this.width) {
            this.rotate();
        }
        if (other.getLength() < other.getWidth()) {
            other.rotate();
        }
        return other.getWidth() - this.width;
    }

}
