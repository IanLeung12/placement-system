/**
 * [PlacementSystem.java]
 * This class is used to load boxes into a truck
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import java.util.ArrayList;
import java.util.Collections;

public class PlacementSystem {

    private int truckLoadWeight;
    private int[][] truckSpace;


    /**
     * PlacementSystem
     * creates a placement system
     */
    public PlacementSystem() {
        this.truckLoadWeight = 0;
    }

    /**
     * loadBoxToTruck
     * loads a single box to a truck
     * @param box the box
     * @param truck the truck
     * @param x the x position to place the box
     * @param y the y position to place the box
     */
    public boolean loadBoxToTruck(Box box, Truck truck, int x, int y) {
        this.truckSpace = new int[truck.getWidth()][truck.getLength()];
        this.truckLoadWeight = truck.getLoadedWeight();


        if (x < 0 || y < 0) {
            return false;
        }

        for (int i = 0; i < this.truckSpace.length; i ++) {
            for (int j = 0; j < this.truckSpace[i].length; j ++) {
                this.truckSpace[i][j] = 0;
            }

        }


        for (Box b: truck.getBoxes()) {
            for (int i = getDisplayY(b); i < (getDisplayY(b) + b.getWidth()); i ++) {
                for (int j = getDisplayX(b); j < (getDisplayX(b) + b.getLength()); j ++) {
                    this.truckSpace[i][j] = b.getBoxID() % 9 + 1;
                }
            }
        }



        for (int row = y; row < y + box.getWidth(); row++) {
            for (int col = x; col < x + box.getLength(); col++) {
                if ((row >= truck.getWidth()) || (col >= truck.getLength())) {
                    return false;
                } else if (this.truckSpace[row][col] != 0) {
                    return false;
                }
            }

        }

        boolean added = addBox(box, truck, x, y, box.getBoxID());
        if (added) {
            box.setCords(y, (truck.getLength() - x) - box.getLength());
        }
        return added;
    }

    /**
     * loadBoxesToTruck
     * loads an array of boxes to a truck
     * @param boxes the boxes
     * @param truck the truck
     */
    /**
     * loadBoxesToTruck
     * Loads an array of boxes to a truck
     * @param boxes the boxes
     * @param truck the truck
     */
    public void loadBoxesToTruck(ArrayList<Box> boxes, Truck truck) {

        this.truckLoadWeight = truck.getLoadedWeight();
        ArrayList<Integer> availableLengths = new ArrayList<>();
        int row;
        int startRow;
        int endY;
        int maxWidth;

        int spaces;
        int startRow2 = 0;
        int endY2;
        int maxWidth2;

        this.truckSpace = new int[truck.getWidth()][truck.getLength()]; // creates empty truck space array
        for (int i = 0; i < this.truckSpace.length; i ++) {
            for (int j = 0; j < this.truckSpace[i].length; j ++) {
                this.truckSpace[i][j] = 0;
            }
        }

        for (int b = 0; b < truck.getBoxes().size(); b++) {
            truck.getBoxes().get(b).setCords(-1, -1);
            boxes.add(truck.getBoxes().get(b));
            truck.getBoxes().remove(b);
            b--;
        }

        Collections.sort(boxes);

        for (int col = 0; col < truck.getLength(); col ++) {
            availableLengths.clear();
            row = 0;
            startRow = -1;
            endY = -1;
            spaces = 0;
            maxWidth2 = -1;

            // Empty space finding
            while (row < truck.getWidth()) {
                if (this.truckSpace[row][col] == 0) {
                    if (startRow == -1) {
                        startRow = row;
                    }
                    spaces++;

                } else {
                    if ((startRow != -1) && (endY == -1)) {
                        endY = row - 1;
                    }

                }
                row++;
            }

            if (startRow != -1 && endY == -1) {
                endY = truck.getWidth() - 1;
            }

            if (startRow == -1) {
                maxWidth = 0;

            } else {
                maxWidth = endY - startRow + 1;
            }

            if (spaces != maxWidth) { // Column is split in two by a box
                row = 0;
                startRow2 = -1;
                endY2 = -1;

                while (row < truck.getWidth()) {
                    if (this.truckSpace[row][col] == 0) {
                        if (startRow2 == -1) {
                            startRow2 = -2;
                        } else if (startRow2 == -3) {
                            startRow2 = row;
                            endY2 = -2;
                        }
                    } else {
                        if (startRow2 == -2) {
                            startRow2 = -3;
                        }

                        // Indicates that program has passed the dividing box, allowing the second starting position
                        if ((startRow2 != -3) && (endY2 == -2)) {
                            endY2 = row - 1;
                        }
                    }
                    row++;
                }

                if (endY2 < 0) {
                    endY2 = truck.getWidth() - 1;
                }
                maxWidth2 = endY2 - startRow2 + 1;
            }

            for (int b = 0; b < boxes.size(); b++) { // Boxes are ordered in decreasing length
                Box box = boxes.get(b);
                availableLengths.add(box.getWidth());

                if ((maxWidth > 0) || (maxWidth2 > 0)) {

                    if (box.getWidth() <= maxWidth) {
                        if (this.addBox(box, truck, col, startRow, box.getBoxID())) {
                            boxes.remove(b);
                            availableLengths.remove(b);
                            startRow = startRow + box.getWidth();
                            maxWidth = maxWidth - box.getWidth();
                            b--;
                        }

                    } else if (box.getLength() == maxWidth) {
                        box.rotate();
                        if (this.addBox(box, truck, col, startRow, box.getBoxID())) {
                            boxes.remove(b);
                            availableLengths.remove(b);
                            startRow = startRow + box.getWidth();
                            maxWidth = maxWidth - box.getWidth();
                            b--;
                        } else {
                            box.rotate();
                        }

                    } else if (box.getLength() < maxWidth) {
                        for (int w = 0; w < availableLengths.size() - 1; w++) {

                            // When two widths combined fit the empty space
                            if ((box != null) && (availableLengths.get(w) + box.getLength() == maxWidth)) {
                                box.rotate();
                                if (this.addBox(box, truck, col, startRow, box.getBoxID())) {
                                    boxes.remove(b);
                                    availableLengths.remove(b);
                                    startRow = startRow + box.getWidth();
                                    maxWidth = maxWidth - box.getWidth();
                                    b--;
                                    box = null;

                                    if (this.addBox(boxes.get(w), truck, col, startRow, boxes.get(w).getBoxID())) {
                                        boxes.remove(w);
                                        availableLengths.remove(w);
                                        maxWidth = 0;
                                        b--;

                                    } else {
                                        boxes.get(w).rotate();
                                    }

                                } else {
                                    box.rotate();
                                }

                            }
                        }

                    } else if (maxWidth2 > 0) {
                        if (box.getWidth() <= maxWidth2) {
                            if (this.addBox(box, truck, col, startRow2, box.getBoxID())) {
                                boxes.remove(b);
                                availableLengths.remove(b);
                                startRow2 = startRow2 + box.getWidth();
                                maxWidth2 = maxWidth2 - box.getWidth();
                                b--;
                            }

                        } else if (box.getLength() == maxWidth2) {
                            box.rotate();
                            if (this.addBox(box, truck, col, startRow2, box.getBoxID())) {
                                boxes.remove(b);
                                availableLengths.remove(b);
                                startRow2 = startRow2 + box.getWidth();
                                maxWidth2 = maxWidth2 - box.getWidth();
                                b--;
                            } else {
                                box.rotate();
                            }

                        } else if (box.getWidth() < maxWidth2) {
                            for (int w = 0; w < availableLengths.size() - 1; w++) {

                                // When two widths combined fit the empty space
                                if ((box != null) && (availableLengths.get(w) + box.getLength() == maxWidth2)) {
                                    box.rotate();
                                    if (this.addBox(box, truck, col, startRow, box.getBoxID())) {
                                        boxes.remove(b);
                                        availableLengths.remove(b);
                                        startRow2 = startRow2 + box.getWidth();
                                        maxWidth2 = maxWidth2 - box.getWidth();
                                        b--;
                                        box = null;

                                        if (this.addBox(boxes.get(w), truck, col, startRow, boxes.get(w).getBoxID())) {
                                            boxes.remove(w);
                                            availableLengths.remove(w);
                                            maxWidth2 = 0;
                                            b--;

                                        } else {
                                            boxes.get(w).rotate();
                                        }

                                    } else {
                                        box.rotate();
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * addBox
     * Tests if a box can fit in a given position in a truck, and adds it if possible
     * @param box the box
     * @param truck the truck
     * @param x the x position to place the box
     * @param y the y position to place the box
     * @param id the id of the box
     * @return if the box has been added successfully
     */
    private boolean addBox(Box box, Truck truck, int x, int y, int id) {
        if (box.getWeight() + this.truckLoadWeight <= truck.getMaxWeight()) {
            if ((x + box.getLength() <= truck.getLength()) && (y + box.getWidth() <= truck.getWidth()) && (box.getHeight() <= truck.getHeight())) {
                box.setCords(x, y);
                truck.addBox(box);
                for (int i = y; i < y + box.getWidth(); i ++) {
                    for (int j = x; j < x + box.getLength(); j ++) {
                        this.truckSpace[i][j] = id % 9 + 1;
                    }
                }
                truckLoadWeight = truckLoadWeight + box.getWeight();
                return true;
            }
        }
        return false;
    }

    /**
     * getTruckSpace
     * returns an int array representing the boxes in a truck
     * @return truckSpace the boxes in the truck
     */
    public int[][] getTruckSpace() {
        return truckSpace;
    }

    /**
     * getDisplayX
     * converts a box's x position into one useable for the display system
     * @param box the box
     * @return the edited x position
     */
    private int getDisplayX(Box box) {
        return (truckSpace[0].length - box.getPositionYInTruck()) - box.getLength();
    }

    /**
     * getDisplayY
     * converts a box's y position into one useable for the display system
     * @param box the box
     * @return the edited y position
     */
    private int getDisplayY(Box box) {
        return box.getPositionXInTruck();
    }
}
