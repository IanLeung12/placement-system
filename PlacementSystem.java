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
    public void loadBoxToTruck(Box box, Truck truck, int x, int y) {
        boolean collides = false;
        this.truckSpace = new int[truck.getWidth()][truck.getLength()];
        for (int i = 0; i < this.truckSpace.length; i ++) {
            for (int j = 0; j < this.truckSpace[i].length; j ++) {
                this.truckSpace[i][j] = 0;
            }

        }
        for (Box b: truck.getBoxes()) {
            for (int i = b.getPositionYInTruck(); i < b.getPositionYInTruck() + b.getWidth(); i ++) {
                for (int j = b.getPositionXInTruck(); j < b.getPositionXInTruck() + b.getWidth(); j ++) {
                    if (truckSpace[i][j] != 0) {
                        System.out.println("eeee");
                    }
                    this.truckSpace[i][j] = b.getId() % 9 + 1;
                }
            }
        }

        for (int row = y; row < y + box.getLength(); row++) {
            for (int col = x; col < x + box.getWidth(); col++) {
                if (this.truckSpace[row][col] != 0) {
                    collides = true;
                }
            }
        }

        if (!collides) {
            addBox(box, truck, x, y, box.getId());
        }
    }

    /**
     * loadBoxesToTruck
     * loads an array of boxes to a truck
     * @param boxes the boxes
     * @param truck the truck
     */
    public void loadBoxesToTruck(ArrayList<Box> boxes, Truck truck) {
        Collections.sort(boxes);
        int row;
        int startRow;
        int endY;
        int maxWidth;

        int spaces;
        int startRow2 = 0;
        int endY2;
        int maxWidth2;

        this.truckSpace = new int[truck.getWidth()][truck.getLength()];
        for (int i = 0; i < this.truckSpace.length; i ++) {
            for (int j = 0; j < this.truckSpace[i].length; j ++) {
                this.truckSpace[i][j] = 0;
            }

        }

        ArrayList<Integer> availableLengths = new ArrayList<>();


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
                            endY2 = row;
                        }

                    }
                    row++;
                }

                if (endY2 < 0) {
                    endY2 = truck.getWidth() - 1;
                }
                maxWidth2 = endY2 - startRow2;
            }

            for (int b = 0; b < boxes.size(); b++) { // Boxes are ordered in decreasing length

                Box box = boxes.get(b);
                availableLengths.add(box.getWidth());

                if ((maxWidth > 0) || (maxWidth2 > 0)) {

                    if (box.getWidth() <= maxWidth) {
                        if (this.addBox(box, truck, col, startRow, box.getId())) {
                            boxes.remove(b);
                            availableLengths.remove(b);
                            startRow = startRow + box.getWidth();
                            maxWidth = maxWidth - box.getWidth();
                            b--;
                        }

                    } else if (box.getLength() == maxWidth) {
                        box.rotate();
                        if (this.addBox(box, truck, col, startRow, box.getId())) {
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
                                if (this.addBox(box, truck, col, startRow, box.getId())) {
                                    boxes.remove(b);
                                    availableLengths.remove(b);
                                    startRow = startRow + box.getWidth();
                                    maxWidth = maxWidth - box.getWidth();
                                    b--;
                                    box = null;

                                    if (this.addBox(boxes.get(w), truck, col, startRow, boxes.get(w).getId())) {
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
                            if (this.addBox(box, truck, col, startRow2, box.getId())) {
                                boxes.remove(b);
                                availableLengths.remove(b);
                                startRow2 = startRow2 + box.getWidth();
                                maxWidth2 = maxWidth2 - box.getWidth();
                                b--;
                            }

                        } else if (box.getLength() == maxWidth2) {
                            box.rotate();
                            if (this.addBox(box, truck, col, startRow2, box.getId())) {
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
                                    if (this.addBox(box, truck, col, startRow, box.getId())) {
                                        boxes.remove(b);
                                        availableLengths.remove(b);
                                        startRow2 = startRow2 + box.getWidth();
                                        maxWidth2 = maxWidth2 - box.getWidth();
                                        b--;
                                        box = null;

                                        if (this.addBox(boxes.get(w), truck, col, startRow, boxes.get(w).getId())) {
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
                    if (truck.getMaxWeight() < truckLoadWeight) {
                        System.out.println("a8");
                    }
                }
            }
        }
        System.out.println(truckLoadWeight);
    }

    /**
     * addBox
     * tests if a box can fit in a given position in a truck, and adds it if possible
     * @param box the box
     * @param truck the truck
     * @param x the x position to place the box
     * @param y the y position to place the box
     * @param id the id of the box
     * @return if the box has been added successfully
     */
    private boolean addBox(Box box, Truck truck, int x, int y, int id) {
        if (box.getWeight() + this.truckLoadWeight <= truck.getMaxWeight()) {
            if (x + box.getWidth() <= truck.getLength() && box.getHeight() <= truck.getHeight()) {
                box.setCords(x, y);
                truck.addBox(box);
                for (int i = box.getPositionYInTruck(); i < box.getPositionYInTruck() + box.getWidth(); i ++) {
                    for (int j = box.getPositionXInTruck(); j < box.getPositionXInTruck() + box.getWidth(); j ++) {
                        if (truckSpace[i][j] != 0) {
                            System.out.println("eeee");
                        }
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
     * cutoff
     * removes boxes from the arraylist that do not fit in the truck
     * @param boxes the arraylist of boxes
     * @param len the cutoff length
     * @return the modified arraylist
     */
    public static ArrayList<Box> cutoff(ArrayList<Box> boxes, int len) {
        Collections.sort(boxes);
        Collections.reverse(boxes);
        int low = 0;
        int high = boxes.size() - 1;
        if (boxes.get(low).getLength() > len) {
            return new ArrayList<Box>();
        }
        if (boxes.get(high - 1).getLength() < len) {
            return boxes;
        }
        low = Math.min(1, boxes.size());
        high = boxes.size() - 1;
        int mid = (low + high) / 2;
        while (true) {
            // System.out.println(low+" "+high+" "+mid);
            // fail safe
            if (low > high) {
                // System.out.println("not found");
                return new ArrayList<Box>();
            } else {
                // if the box of size limit is found, check linearly until it reaches a box that
                // is bigger than the limit
                if (boxes.get(mid).getLength() == len) {
                    // System.out.println(mid);
                    // System.out.println(len);
                    for (int i = mid; i < boxes.size(); i++) {
                        // System.out.println(boxes.get(i).getLength());
                        if (boxes.get(i).getLength() > len) {
                            // System.out.println(i-1);
                            return new ArrayList<>(boxes.subList(0, i));
                        }
                    }
                }
                // if the box is less than the limit, check the box to the right of it, if that
                // is not over the limit, check the right side of the current box
                else if (boxes.get(mid).getLength() < len) {
                    if (boxes.get(mid + 1).getLength() > len) {
                        return new ArrayList<>(boxes.subList(0, mid + 1));
                    }
                    low = mid + 1;
                }
                // if the box is greater than the limit, check the box to the left of it, if
                // that is not under/equal to the limit, check the left side of the current box
                else if (boxes.get(mid).getLength() > len) {
                    if (boxes.get(mid - 1).getLength() <= len) {
                        return new ArrayList<>(boxes.subList(0, mid));
                    }
                    high = mid - 1;
                }
                mid = (low + high) / 2;
            }
        }
    }

    /**
     * getTruckSpace
     * returns an int array representing the boxes in a truck
     * @return truckSpace the boxes in the truck
     */
    public int[][] getTruckSpace() {
        return truckSpace;
    }

}
