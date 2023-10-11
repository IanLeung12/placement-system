/**
 * [Main.java]
 * This program simulates a shipping system
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Box> boxes = new ArrayList<>();
        for (int i = 0; i < 50; i ++) {
            boxes.add(new Box(i, (int) (Math.random() * 25 + 1), (int) (Math.random() * 14 + 1), (int) (Math.random() * 15 + 1), (int) (Math.random() * 15  +1), null));
            //boxes.add(new Box(i, 69, 10, 10, 2, null));
        }
        Collections.sort(boxes);
        //Collections.reverse(boxes);

        Truck truck = new Truck(1, 700, 999, 100, 20);
        PlacementSystem sys = new PlacementSystem();
        sys.loadBoxesToTruck(boxes, truck);
        ArrayList<Box> load = truck.getBoxes();


        int[][] space = sys.getTruckSpace();
        printTruck(space);
        for (Box box: boxes) {
            System.out.println(box.getProps());
        }
        System.out.println(truck.getBoxes().size());
        Box newBox = new Box(123, 1, 3, 2, 2, null);
        Scanner input = new Scanner(System.in);
//        while (true) {
//            sys.loadBoxToTruck(newBox, truck, input.nextInt(), input.nextInt());
//            System.out.println("qwerty");
//        }

    }
    public static void printTruck(int[][] space) {
        int counter = 0;
        System.out.print("i: ");
        for (int j = 0; j < space[0].length; j ++) {
            System.out.print(j % 10);
        }
        System.out.println();
        for (int i = 0; i < space.length; i ++) {
            System.out.print(i % 10 + ": ");
            for (int j = 0; j < space[i].length; j ++) {
                System.out.print(space[i][j]);
                if (space[i][j] == 0) {
                    counter ++;
                }
            }
            System.out.println();
        }
        System.out.println(counter + " empty spaces");
    }
}
