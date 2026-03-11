import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add a new room type
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Update availability manually
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found.");
        }
    }

    // Book rooms (decrease inventory)
    public void bookRooms(String roomType, int roomsRequested) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) throw new InvalidBookingException("Room type not found.");
        if (roomsRequested <= 0) throw new InvalidBookingException("Invalid number of rooms requested.");
        int available = inventory.get(roomType);
        if (available < roomsRequested) throw new InvalidBookingException("Not enough rooms available.");
        inventory.put(roomType, available - roomsRequested);
    }

    // Cancel booking (increase inventory)
    public void cancelBooking(String roomType, int roomsReleased) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + roomsReleased);
    }

    // Get availability for a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n=== Room Inventory ===");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type) + " rooms available");
        }
    }
}