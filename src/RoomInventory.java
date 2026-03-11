import java.util.*;

public class RoomInventory {

    private Map<String, Integer> availableRooms;

    public RoomInventory() {
        availableRooms = new HashMap<>();
        availableRooms.put("Standard", 10);
        availableRooms.put("Deluxe", 5);
        availableRooms.put("Suite", 2);
    }

    // Validate booking
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

    // Book rooms
    public void bookRooms(String roomType, int roomsRequested) throws InvalidBookingException {
        validateBooking(roomType, roomsRequested);
        int updated = availableRooms.get(roomType) - roomsRequested;
        availableRooms.put(roomType, updated);
    }

    // Cancel rooms (rollback inventory)
    public void cancelBooking(String roomType, int roomsToRelease) {
        int updated = availableRooms.getOrDefault(roomType, 0) + roomsToRelease;
        availableRooms.put(roomType, updated);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n=== Room Inventory ===");
        for (Map.Entry<String, Integer> entry : availableRooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}