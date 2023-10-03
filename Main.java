import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Box> boxes = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            boxes.add(new Box(i, 69, (int) (Math.random() * 100), (int) (Math.random() * 100), (int) (Math.random() * 100), null));
        }
        Collections.sort(boxes);
        for (Box box: boxes) {
            System.out.println(box.getId() + ": L" + box.getLength() + " W" + box.getWidth());
        }
    }
}
