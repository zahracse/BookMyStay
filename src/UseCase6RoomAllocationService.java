import java.util.*;

/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Processes booking requests, allocates unique room IDs,
 * updates inventory, and prevents double booking.
 *
 * @author Zahra Jeelani
 * @version 6.1
 */

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 6.1");
        System.out.println("   Room Allocation Service");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Double Room", 1);
        inventory.addRoomType("Suite Room", 1);

        // Create booking queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Aisha", "Single Room"));
        bookingQueue.add(new Reservation("Rahul", "Double Room"));
        bookingQueue.add(new Reservation("Fatima", "Single Room"));
        bookingQueue.add(new Reservation("Omar", "Suite Room"));

        // Track allocated rooms
        HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

        System.out.println("\nProcessing Booking Requests...\n");

        while (!bookingQueue.isEmpty()) {

            Reservation reservation = bookingQueue.poll();
            String roomType = reservation.getRoomType();

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                // Generate unique room ID
                String roomId = roomType.replace(" ", "").substring(0, 2).toUpperCase()
                        + "-" + UUID.randomUUID().toString().substring(0, 4);

                // Add room ID to set
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                allocatedRooms.get(roomType).add(roomId);

                // Update inventory
                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + reservation.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);
                System.out.println();

            } else {

                System.out.println("Reservation Failed - No rooms available");
                System.out.println("Guest: " + reservation.getGuestName());
                System.out.println("Requested Room: " + roomType);
                System.out.println();
            }
        }

        System.out.println("Final Allocated Rooms:");

        for (String type : allocatedRooms.keySet()) {

            System.out.println(type + " -> " + allocatedRooms.get(type));
        }

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}