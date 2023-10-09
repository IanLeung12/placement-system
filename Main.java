import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Box> boxes = new ArrayList<>();
        for (int i = 0; i < 40; i ++) {
            boxes.add(new Box(i, 69, (int) (Math.random() * 14 + 1), (int) (Math.random() * 15 + 1), (int) (Math.random() * 10  +1), null));
            //boxes.add(new Box(i, 69, 10, 10, 2, null));
        }
        Collections.sort(boxes);
        //Collections.reverse(boxes);

        Truck truck = new Truck(1, 1200, 999, 20, 100);
        PlacementSystem sys = new PlacementSystem();
        sys.loadTruck(boxes, truck);
        ArrayList<Box> load = truck.getLoadedBoxes();
        for (Box box: load) {
            System.out.println(box.getProps());
        }


        int[][] space = truck.getSpaceArray();
        System.out.print("i: ");
        for (int j = 0; j < space[0].length; j ++) {
            System.out.print(j % 10);
        }
        System.out.println();
        for (int i = 0; i < space.length; i ++) {
            System.out.print(i % 10 + ": ");
            for (int j = 0; j < space[i].length; j ++) {
                System.out.print(space[i][j]);
            }
            System.out.println();
        }

    }
}
