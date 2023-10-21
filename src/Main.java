/**
 * [Main.java]
 * This program simulates a shipping system
 * @author Ian Leung
 * @author Sfan Shen
 * @author Leon Yuan
 * @version 1.0
 * @since October 10, 2023
 */

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        PlacementSystem placement = new PlacementSystem();
        ReceivingSystem receive = new ReceivingSystem(warehouse);
        DisplaySystem display = new DisplaySystem(warehouse, placement);
        receive.start();
        display.runLoop();

    }
}


