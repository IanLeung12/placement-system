/**
 * [Box.java]
 * This class simulates a box
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import java.awt.*;

public class Box implements Comparable<Box> {
    private final int boxID;
    private final int weight;
    private final int height;
    private int length;
    private int width;
    private int positionXInTruck;
    private int positionYInTruck;
    private final int positionZInTruck;
    private Color color;

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

    /**
     * setCords
     * sets the cordinates of the box in a truck
     * @param x the x position
     * @param y the y position
     */
    public void setCords(int x, int y) {
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

    /**
     * getWeight
     * returns the weight of the box
     * @return weight the weight
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * getId
     * returns the id of the box
     * @return boxID the id
     */
    public int getBoxID() {
        return this.boxID;
    }

    /**
     * getLength
     * returns the length of the box
     * @return length the length
     */
    public int getLength() {
        return length;
    }

    /**
     * getWidth
     * returns the width of the box
     * @return width the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight
     * returns the height of the box
     * @return height the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * getPositionXInTruck
     * returns the x position in a truck of the box
     * @return positionXInTruck the x position
     */
    public int getPositionXInTruck() {
        return positionXInTruck;
    }

    /**
     * getPositionYInTruck
     * returns the y position in a truck of the box
     * @return positionXInTruck the y position
     */
    public int getPositionYInTruck() {
        return positionYInTruck;
    }

    // temporary toString method

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String toString() {
        return "Box{boxID=" + this.boxID + ", weight=" + this.weight + ", length=" + this.length + ", width=" + this.width + ", height=" + this.height + ", positionXInTruck=" + this.positionXInTruck + ", positionYInTruck=" + this.positionYInTruck + ", positionZInTruck=" + this.positionZInTruck + ", color=" + this.color + '}';
    }

    /**
     * draw
     * Draws the box
     * @param g Graphics object from GraphicsPanel
     * @param x int, the x coordinate where the box will be drawn
     * @param y int, the y coordinate where the box will be drawn
     * @param scaleFactor double, the factor by which the size of the boxes will be scaled
     */
    public void draw(Graphics g, int x, int y, double scaleFactor, int borderWidth) {
        g.setColor(color.darker());
        g.fillRect(x, y, (int)Math.round(width * scaleFactor), (int)Math.round(length * scaleFactor));
        g.setColor(color);
        g.fillRect(x + borderWidth, y + borderWidth, ((int)Math.round(width * scaleFactor)) - (2 * borderWidth), ((int)Math.round(length * scaleFactor)) - (2 * borderWidth));
    }

}
