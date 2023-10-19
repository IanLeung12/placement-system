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
        Warehouse warehouse = new Warehouse(1, 99999, 20);
        PlacementSystem placement = new PlacementSystem();
        ReceivingSystem receive = new ReceivingSystem(warehouse);
        // DIsplay system not working rn cuz its in java 21
        //DisplaySystem display = new DisplaySystem(warehouse, placement);
        receive.start();
        //display.runLoop();
    }
}

