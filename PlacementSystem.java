import java.util.ArrayList;

public class PlacementSystem {
    public PlacementSystem() {

    }

    public void loadTruck(Box[] boxes, Truck truck) {
        
    }

    public ArrayList<Box> temp (ArrayList<Box> unsortedboxes, Truck truck) {
        ArrayList<Box> sortedBoxes = new ArrayList<>();
        int minSideLength = Math.min(truck.getLength(), truck.getWidth());

        for (int i = 0; i < unsortedboxes.size(); i ++) {
            Box box = unsortedboxes.get(i);
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
