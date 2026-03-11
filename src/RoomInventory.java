import java.util.*;

// Room Inventory Manager
public class RoomInventory {

    private Map<String, Integer> availableRooms;

    public RoomInventory() {
        availableRooms = new HashMap<>();
        // Initialize sample inventory
        availableRooms.put("Standard", 10);
        availableRooms.put("Deluxe", 5);
        availableRooms.put("Suite", 2);
    }

    // Validate room type and availability
    public void validateBooking(String roomType, int roomsRequested) throws InvalidBookingException {
        if (!availableRooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (roomsRequested <= 0) {
            throw new InvalidBookingException("Number of rooms must be greater than zero.");
        }

        int available = availableRooms.get(roomType);
        if (roomsRequested > available) {
            throw new InvalidBookingException("Only " + available + " rooms available for " + roomType);
        }
    }

    // Book rooms (after validation)
    public void bookRooms(String roomType, int roomsRequested) throws InvalidBookingException {
        validateBooking(roomType, roomsRequested); // fail-fast
        int updated = availableRooms.get(roomType) - roomsRequested;
        availableRooms.put(roomType, updated);
    }

    // Display current room inventory
    public void displayInventory() {
        System.out.println("\n=== Room Inventory ===");
        for (Map.Entry<String, Integer> entry : availableRooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}