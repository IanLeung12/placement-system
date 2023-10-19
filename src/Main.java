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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Box> boxes = new ArrayList<>();
        Warehouse warehouse = new Warehouse(0, 100000, 100000, boxes, new ArrayList<Truck>());
        ReceivingSystem receive = new ReceivingSystem(warehouse);
        receive.start();
        int numBoxes;
        int maxWeight;
        int maxHeight;
        int maxLength;
        int maxWidth;
        int truckMaxW;
        int truckHeight;
        int truckLength;
        int truckWidth;

        System.out.println("Type \"default\" for preset dimensions");
        if (input.nextLine().equals("default")) {
            numBoxes = 30;
            maxWeight = 20;
            maxHeight = 15;
            maxLength = 15;
            maxWidth = 15;
            truckMaxW = 600;
            truckHeight = 200;
            truckLength = 100;
            truckWidth = 20;
        } else {
            System.out.println("How many boxes are you adding?");
            numBoxes = input.nextInt();
            System.out.println("Enter max weight of a box?");
            maxWeight = input.nextInt();
            System.out.println("Enter max height of box?");
            maxHeight = input.nextInt();
            System.out.println("Enter max length of box?");
            maxLength = input.nextInt();
            System.out.println("Enter max width of box?");
            maxWidth = input.nextInt();
            System.out.println("Enter the max weight of the truck");
            truckMaxW = input.nextInt();
            System.out.println("Enter the height of the truck");
            truckHeight = input.nextInt();
            System.out.println("Enter the length of the truck");
            truckLength = input.nextInt();
            System.out.println("Enter the width of the truck");
            truckWidth = input.nextInt();
        }

        for (int i = 0; i < numBoxes; i ++) {
            boxes.add(new Box(i, (int) (Math.random() * maxWeight + 1), (int) (Math.random() * maxHeight + 1),
                    (int) (Math.random() * maxLength + 1), (int) (Math.random() * maxWidth  +1), null));
        }

        Truck truck = new Truck(1, truckMaxW, truckHeight, truckLength, truckWidth);

        PlacementSystem sys = new PlacementSystem();
        sys.loadBoxesToTruck(boxes, truck);

        int[][] space = sys.getTruckSpace();
        printTruck(space);
        System.out.println("The loaded boxes weigh " + truck.getLoadedWeight() +
                " total out of the truck's " + truck.getMaxWeight() + " max weight");
        System.out.println();

        System.out.println("Truck");
        for (Box box: truck.getBoxes()) {
            System.out.println(box.getProps());
        }

        System.out.println(truck.getBoxes().size() + " boxes in truck.");
        System.out.println("Leftover boxes");
        for (Box box: boxes) {
            System.out.println(box.getProps());
        }

        // Edit this to whatever you want
        Box newBox = new Box(123, 1, 3, 2, 2, null);
        boolean boxAdded = false;
        while (!boxAdded) {
            System.out.println("Type x and y coordinate to add 2x2 box");
            boxAdded = sys.loadBoxToTruck(newBox, truck, input.nextInt(), input.nextInt());
            space = sys.getTruckSpace();
            printTruck(space);
        }
        input.close();

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
