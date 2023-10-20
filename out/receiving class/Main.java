/**
 * Main
 * @author Michael Khart, Dilen De Silva
 * ICS4UE
 * @version 1.0 - 2023/10/10
 * Main - Uses all other classes for the final program
 */
public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        ReceivingSystem sys = new ReceivingSystem(warehouse);
        sys.start();
    }

}