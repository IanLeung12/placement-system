import java.util.ArrayList;

public class PlacementSystem {
    public PlacementSystem() {

    }

    public void loadTruck(ArrayList<Box> boxes, Truck truck) {
        int y;
        int startY;
        int endY;
        int maxLength;
        for (int x = 0; x < truck.getWidth(); x ++) {
            y = 0;
            startY = -1;
            endY = 0;
            while (y < truck.getLength()) {
                if ((truck.getSpaceArray()[y][x] == 0) && (startY == -1)) {
                    startY = y;

                } else if ((startY != -1) && (truck.getSpaceArray()[y][x] != 0)) {
                    endY = y - 1;
                } else if (y == truck.getLength() - 1) {
                    endY = y;
                }
                y ++;
            }
            System.out.println("ROW: " + x + " Start Y: " + startY + "   End Y: " + endY);
            maxLength = endY - startY;
            System.out.println(maxLength);

            for (int b = 0; b < boxes.size(); b ++) {
                if (maxLength > 0) {
                    Box box = boxes.get(b);
                    if (box.getLength() <= maxLength) {
                        box.setCoords(x, startY);
                        startY = startY + box.getLength();
                        maxLength = maxLength - box.getLength();
                        truck.addBox(box);

                        boxes.remove(b);
                    }
                }
            }
        }
    }

    public ArrayList<Box> temp (ArrayList<Box> unsortedBoxes, Truck truck) {
        ArrayList<Box> sortedBoxes = new ArrayList<>();
        int minSideLength = Math.min(truck.getLength(), truck.getWidth());

        for (int i = 0; i < unsortedBoxes.size(); i ++) {
            Box box = unsortedBoxes.get(i);
            if (box.getLength() > minSideLength) {
                if (!(box.getWidth() > minSideLength)) {
                    sortedBoxes.add(box);
                }
            } else {
                //Both length and width less than min side length
                sortedBoxes.add(box);
            }
        }
        return sortedBoxes;
    }
}
