import java.util.HashMap;
import java.util.Map;


public class RoomInventory {

    private HashMap<String, Integer> inventory;


    public RoomInventory() {
        inventory = new HashMap<>();
    }


    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }


    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }


    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }


    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}