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
import java.awt.Graphics;

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
     * Creates a box without a position in a truck
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
     * Creates a box with a position within a truck
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
     * Rotates the box by switching the width value and length value
     */
    public void rotate() {
        int tempLength = this.width;
        this.width = length;
        this.length = tempLength;
    }

    /**
     * setCords
     * Sets the coordinates of the box in a truck
     * @param x the x position
     * @param y the y position
     */
    public void setCords(int x, int y) {
        this.positionXInTruck = x;
        this.positionYInTruck = y;
    }

    /**
     * compareTo
     * Compares to another box based on its smaller side length for sorting purposes
     * @param other the object to be compared.
     * @return the difference between their smaller side lengths
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
     * Returns the weight of the box
     * @return weight the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * getId
     * Returns the id of the box
     * @return boxID the id
     */
    public int getBoxID() {
        return boxID;
    }

    /**
     * getLength
     * Returns the length of the box
     * @return length the length
     */
    public int getLength() {
        return length;
    }

    /**
     * getWidth
     * Returns the width of the box
     * @return width the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight
     * Returns the height of the box
     * @return height the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * getPositionXInTruck
     * Returns the x position in a truck of the box
     * @return positionXInTruck the x position
     */
    public int getPositionXInTruck() {
        return positionXInTruck;
    }

    /**
     * getPositionYInTruck
     * Returns the y position in a truck of the box
     * @return positionYInTruck the y position
     */
    public int getPositionYInTruck() {
        return positionYInTruck;
    }

    /**
     * getPositionZInTruck
     * Returns the z position in a truck of the box
     * @return positionZInTruck the z position
     * @return
     */
    public int getPositionZInTruck() {
        return positionZInTruck;
    }

    /**
     * getColor
     * Returns the color of the box
     * @return color the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * toString
     * Returning a string representation of the box for saving
     * @return the string representation of the box
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Box");
        int[] values = ReceivingSystem.asArray(this, false);
        int[] var3 = values;
        int var4 = values.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int value = var3[var5];
            string.append(" ");
            string.append(value);
        }

        string.append(" ");
        string.append(ReceivingSystem.getRGB(this.color));
        return string.toString();
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
