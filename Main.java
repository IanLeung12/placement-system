import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Box> boxes = new ArrayList<>();
        for (int i = 0; i < 9; i ++) {
            boxes.add(new Box(i, 69, (int) (Math.random() * 10 + 1), (int) (Math.random() * 10 + 1), (int) (Math.random() * 10  +1), null));
        }
        Collections.sort(boxes);
        Collections.reverse(boxes);

        for (Box box : boxes) {
            System.out.print(box.getLength() + " ");
        }
        Truck truck = new Truck(1, 1200, 999, 20, 100);
        PlacementSystem sys = new PlacementSystem();
        sys.loadTruck(boxes, truck);
        ArrayList<Box> load = truck.getLoadedBoxes();
        for (Box box : load) {
            System.out.println(box.getProps());
        }

        int[][] space = truck.getSpaceArray();
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[i].length; j++) {
                System.out.print(space[i][j]);
            }
            System.out.println();
        }

    }
}
