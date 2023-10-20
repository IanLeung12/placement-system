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
        Warehouse warehouse = new Warehouse();
        PlacementSystem placement = new PlacementSystem();
        ReceivingSystem receive = new ReceivingSystem(warehouse);
        DisplaySystem display = new DisplaySystem(warehouse, placement);
        receive.start();
        display.runLoop();

    }
}


