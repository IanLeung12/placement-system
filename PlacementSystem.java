import java.util.ArrayList;

public class PlacementSystem {
    public PlacementSystem() {

    }

    public void cutoff(Box[] boxes, int len) {
        int low = 0;
        int high = boxes.length;
    }
    public static ArrayList<Box> cutoff (ArrayList<Box> boxes, int len) {
        int low = 0;
        int high = boxes.size()-1;
        if (boxes.get(low).getLength() > len) {
            return new ArrayList<Box>();
        }
        if (boxes.get(high-1).getLength() < len) {
            return boxes;
        }
        low = Math.min(1, boxes.size());
        high = boxes.size()-1;
        int mid = (low+high)/2;
        while (true) {
             // System.out.println(low+" "+high+" "+mid);
            // fail safe
            if (low > high) {
                // System.out.println("not found");
                return new ArrayList<Box>();
            }
            else {
                // if the box of size limit is found, check linearly until it reaches a box that is bigger than the limit
                if (boxes.get(mid).getLength() == len) {
                    // System.out.println(mid);
                    // System.out.println(len);
                    for (int i = mid; i<boxes.size(); i++) {
                        // System.out.println(boxes.get(i).getLength());
                        if (boxes.get(i).getLength() > len) {
                            // System.out.println(i-1);
                            return new ArrayList<>(boxes.subList(0, i));
                        }
                    }
                }
                // if the box is less than the limit, check the box to the right of it, if that is not over the limit, check the right side of the current box
                else if (boxes.get(mid).getLength() < len) {
                    if (boxes.get(mid+1).getLength() > len) {
                        return new ArrayList<>(boxes.subList(0, mid+1));
                    }
                    low = mid+1;
                }
                // if the box is greater than the limit, check the box to the left of it, if that is not under/equal to the limit, check the left side of the current box
                else if (boxes.get(mid).getLength() > len) { 
                    if (boxes.get(mid-1).getLength() <= len) {
                        return new ArrayList<>(boxes.subList(0, mid));
                    }
                    high = mid-1;
                }
                mid = (low+high)/2;
            }
        }
    }

    public void loadTruck(Box[] boxes, Truck truck) {
        
    }
}
