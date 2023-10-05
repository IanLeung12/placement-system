import java.util.ArrayList;

public class PlacementSystem {
    public PlacementSystem() {

    }

    public void loadTruck(ArrayList<Box> boxes, Truck truck) {
        int nextY = 0;
        int j = 0;
        for (int row = 0; row < truck.getLength(); row ++) {
            nextY = truck.getWidth();
            for (int column = 0; column < truck.getWidth(); column ++) {
                if (truck.getSpaceArray()[row][column] == 0) {
                    nextY = column;
                    break;
                }
            }
            j = boxes.size() - 1;
            if (boxes.size() > 0) {
                do {
                    if (boxes.get(j).getLength() < truck.getWidth() - nextY - 1) {
                        boxes.get(j).rotate();
                        boxes.get(j).setCoords(row, nextY);
                        truck.addBox(boxes.get(j));
                        nextY = nextY + boxes.get(j).getWidth();
                        boxes.remove(j);

                    }
                    j --;
                } while ((nextY > truck.getWidth()) && (j >= 0));
            } else {
                break;
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
