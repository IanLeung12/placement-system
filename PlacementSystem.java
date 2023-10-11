/**
 * [PlacementSystem.java]
 * This class is used to load boxes into a truck
 *
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0 October 10, 2023
 */

import java.util.ArrayList;
import java.util.Collections;

public class PlacementSystem {
    public PlacementSystem() {

    }

    /**
     * loadBoxToTruck
     * loads an array of boxes efficiently into a truck without going over the max weight
     * @param box the boxes to load into the truck
     * @param truck the truck
     */

    public void loadBoxToTruck(Box box, Truck truck, int row, int col) {
        //check if box can be loaded into truck
        if (isValid(box, truck, row, col)) {
            box.setCoords(row, col);
            truck.addBox(box);
        }
    }

    public boolean isValid(Box box, Truck truck, int y, int x) {
        if (truck.isValid(box)) {
            for (int row = y; row < y + box.getLength(); row++) {
                for (int col = x; col < x + box.getWidth(); col++) {
                    if (truck.getSpaceArray()[row][col] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void loadBoxesToTruck(ArrayList<Box> boxes, Truck truck) {
        Collections.sort(boxes);
        int row;
        int startRow;
        int endY;
        int maxLength;
        int startRow2 = 0;
        int endY2;
        int maxLength2;
        int spaces;
        boolean widthFits;
        boolean lengthFits;
        ArrayList<Integer> availableWidths = new ArrayList<>();


        for (int col = 0; col < truck.getWidth(); col ++) {
            availableWidths.clear();
            row = 0;
            startRow = -1;
            endY = -1;
            spaces = 0;
            maxLength2 = -1;

            // Empty space finding
            while (row < truck.getLength()) {
                if (truck.getSpaceArray()[row][col] == 0) {
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
                endY = truck.getLength() - 1;
            }

            if (startRow == -1) {
                maxLength = 0;

            } else {
                maxLength = endY - startRow + 1;
            }

            if (spaces != maxLength) { // Column is split in two by a box
                row = 0;
                startRow2 = -1;
                endY2 = -1;

                while (row < truck.getLength()) {
                    if (truck.getSpaceArray()[row][col] == 0) {

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
                    endY2 = truck.getLength() - 1;
                }
                maxLength2 = endY2 - startRow2;
            }

            for (int b = 0; b < boxes.size(); b++) { // Boxes are ordered in decreasing length

                Box box = boxes.get(b);
                availableWidths.add(box.getWidth());

                if ((truck.isValid(box)) && (maxLength > 0) || (maxLength2 > 0)) {
                    lengthFits = box.getLength() <= (truck.getWidth() - col);
                    widthFits = box.getWidth() <= (truck.getWidth() - col);

                    if (box.getLength() <= maxLength && widthFits) {
                        box.setCoords(col, startRow);
                        truck.addBox(box);
                        boxes.remove(b);
                        availableWidths.remove(b);
                        startRow = startRow + box.getLength();
                        maxLength = maxLength - box.getLength();
                        b--;

                    } else if (box.getWidth() == maxLength && lengthFits) {
                        box.rotate();
                        box.setCoords(col, startRow);
                        truck.addBox(box);
                        boxes.remove(b);
                        availableWidths.remove(b);
                        startRow = startRow + box.getLength();
                        maxLength = maxLength - box.getLength();
                        b--;

                    } else if (box.getWidth() < maxLength && lengthFits) {
                        for (int w = 0; w < availableWidths.size() - 1; w++) {

                            // When two widths combined fit the empty space
                            if ((availableWidths.get(w) + box.getWidth() == maxLength) &&
                                    (boxes.get(w).getLength() < (truck.getWidth() - col))) {
                                Box[] bothBoxes = new Box[]{boxes.get(w), box};

                                if (truck.isValid(bothBoxes)) {
                                    boxes.get(w).rotate();
                                    box.rotate();
                                    boxes.get(w).setCoords(col, startRow);
                                    box.setCoords(col, startRow + boxes.get(w).getLength());
                                    truck.addBox(boxes.get(w));
                                    truck.addBox(box);
                                    boxes.remove(b);
                                    boxes.remove(w);
                                    availableWidths.remove(b);
                                    availableWidths.remove(w);
                                    maxLength = 0;
                                    b = b - 2;
                                }

                            }
                        }

                    } else if (maxLength2 > 0) {
                        if (box.getLength() <= maxLength2 && widthFits) {
                            box.setCoords(col, startRow2);
                            truck.addBox(box);
                            boxes.remove(b);
                            availableWidths.remove(b);
                            startRow2 = startRow2 + box.getLength();
                            maxLength2 = maxLength2 - box.getLength();
                            b--;

                        } else if (box.getWidth() == maxLength2 && lengthFits) {
                            box.rotate();
                            box.setCoords(col, startRow2);
                            truck.addBox(box);
                            boxes.remove(b);
                            availableWidths.remove(b);
                            startRow2 = startRow2 + box.getLength();
                            maxLength2 = maxLength2 - box.getLength();
                            b--;

                        } else if (box.getWidth() < maxLength2 && lengthFits) {
                            for (int w = 0; w < availableWidths.size() - 1; w++) {

                                if ((availableWidths.get(w) + box.getWidth() == maxLength2) &&
                                        (boxes.get(w).getLength() < (truck.getWidth() - col))) {

                                    Box[] bothBoxes = new Box[]{boxes.get(w), box};
                                    if (truck.isValid(bothBoxes)) {
                                        boxes.get(w).rotate();
                                        box.rotate();
                                        boxes.get(w).setCoords(col, startRow2);
                                        box.setCoords(col, startRow + boxes.get(w).getLength());
                                        truck.addBox(boxes.get(w));
                                        truck.addBox(box);
                                        boxes.remove(b);
                                        boxes.remove(w);
                                        availableWidths.remove(b);
                                        availableWidths.remove(w);
                                        maxLength2 = 0;
                                        b = b - 2;
                                    }
                                }
                            }
                        }
                    }
                    if (truck.getMaxWeight() < truck.getLoadedWeight()) {
                        System.out.println("a");
                    }
                }
            }
        }
    }


    public void cutoff(Box[] boxes, int len) {
        int low = 0;
        int high = boxes.length;
    }

    public static ArrayList<Box> cutoff(ArrayList<Box> boxes, int len) {
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

    public ArrayList<Box> temp(ArrayList<Box> unsortedBoxes, Truck truck) {
        ArrayList<Box> sortedBoxes = new ArrayList<>();
        int minSideLength = Math.min(truck.getLength(), truck.getWidth());

        for (int i = 0; i < unsortedBoxes.size(); i++) {
            Box box = unsortedBoxes.get(i);
            if (box.getLength() > minSideLength) {
                if (!(box.getWidth() > minSideLength)) {
                    sortedBoxes.add(box);
                }
            } else {
                // Both length and width less than min side length
                sortedBoxes.add(box);
            }
        }
        return sortedBoxes;
    }

    //public void assignBoxPositionInTruck();
}
