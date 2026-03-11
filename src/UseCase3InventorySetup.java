/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates how a HashMap is used to manage
 * room availability in a centralized inventory system.
 *
 * @author Zahra Jeelani
 * @version 3.1
 */

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 3.1");
        System.out.println("   Room Inventory Management");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types with availability
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 2);

        // Display inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating inventory...");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nInventory system initialized successfully.");
    }
}