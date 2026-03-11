/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates safe read-only search operations
 * using centralized inventory data.
 *
 * @author Zahra Jeelani
 * @version 4.1
 */

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 4.1");
        System.out.println("   Room Search System");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); // unavailable
        inventory.addRoomType("Suite Room", 2);

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = {single, doubleRoom, suite};

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform room search
        searchService.searchAvailableRooms(rooms);

        System.out.println("\nSearch completed successfully.");
    }
}