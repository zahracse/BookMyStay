import java.util.Map;

/**
 * Handles read-only room search operations.
 * Retrieves availability from inventory and
 * displays only rooms that are available.
 *
 * @author Zahra Jeelani
 * @version 4.0
 */
public class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.roomType);

            // Show only available rooms
            if (available > 0) {

                System.out.println("\n---------------------------");
                room.displayRoomDetails();
                System.out.println("Available: " + available);
            }
        }
    }
}