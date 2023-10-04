import java.util.ArrayList;

public class PlacementSystem {
    public PlacementSystem() {

    }

    public void loadTruck(ArrayList<Box> boxes, Truck truck) {
        int nextY = 0;
        int j = 0;
        for (int i = 0; i < truck.getLength(); i ++) {
            nextY = truck.getWidth();
            for (int y = 0; y < truck.getWidth(); y ++) {
                if (truck.getSpaceArray()[i][y] == 0) {
                    nextY = y;
                    break;
                }
            }
            j = boxes.size() - 1;
            if (boxes.size() > 0) {
                do {
                    if (boxes.get(j).getLength() < truck.getWidth() - nextY - 1) {
                        boxes.get(j).rotate();
                        boxes.get(j).setCoords(i, nextY);
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
}
