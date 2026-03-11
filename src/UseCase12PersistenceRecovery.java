import java.io.*;
import java.util.*;

public class UseCase12PersistenceRecovery {

    private static final String BOOKING_FILE = "booking_history.dat";
    private static final String INVENTORY_FILE = "room_inventory.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load persisted state if exists
        List<BookingRecord> bookings = loadBookings();
        RoomInventory inventory = loadInventory();

        if (bookings == null) bookings = new ArrayList<>();
        if (inventory == null) inventory = new RoomInventory();

        System.out.println("=== Book My Stay: Persistence & Recovery ===");

        while (true) {
            inventory.displayInventory();

            System.out.println("\nOptions: 1. Book  2. Cancel  3. View Bookings  4. Save & Exit");
            System.out.print("Select option: ");
            String option = sc.nextLine();

            switch (option) {
                case "1": // Book
                    System.out.print("Enter Guest Name: ");
                    String guestName = sc.nextLine();

                    System.out.print("Enter Room Type: ");
                    String roomType = sc.nextLine();

                    System.out.print("Enter Number of Rooms: ");
                    int roomsRequested;
                    try {
                        roomsRequested = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number input.");
                        break;
                    }

                    try {
                        inventory.bookRooms(roomType, roomsRequested);
                        String reservationId = "RES" + (bookings.size() + 1);
                        BookingRecord booking = new BookingRecord(reservationId, guestName, roomType, roomsRequested);
                        bookings.add(booking);
                        System.out.println("Booking Confirmed: " + booking);
                    } catch (InvalidBookingException e) {
                        System.out.println("Booking Failed: " + e.getMessage());
                    }
                    break;

                case "2": // Cancel
                    System.out.print("Enter Reservation ID to cancel: ");
                    String resId = sc.nextLine();
                    BookingRecord toCancel = null;

                    for (BookingRecord b : bookings) {
                        if (b.getReservationId().equalsIgnoreCase(resId)) {
                            toCancel = b;
                            break;
                        }
                    }

                    if (toCancel == null) {
                        System.out.println("Reservation ID not found.");
                    } else {
                        bookings.remove(toCancel);
                        inventory.cancelBooking(toCancel.getRoomType(), toCancel.getRoomsBooked());
                        System.out.println("Booking canceled successfully: " + toCancel);
                    }
                    break;

                case "3": // View bookings
                    System.out.println("\n=== Current Bookings ===");
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        for (BookingRecord b : bookings) {
                            System.out.println(b);
                        }
                    }
                    break;

                case "4": // Save & Exit
                    saveBookings(bookings);
                    saveInventory(inventory);
                    System.out.println("System state saved. Exiting.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Serialization helpers
    private static void saveBookings(List<BookingRecord> bookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKING_FILE))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            System.out.println("Failed to save bookings: " + e.getMessage());
        }
    }

    private static void saveInventory(RoomInventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INVENTORY_FILE))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            System.out.println("Failed to save inventory: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<BookingRecord> loadBookings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKING_FILE))) {
            return (List<BookingRecord>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous booking history found.");
            return null;
        }
    }

    private static RoomInventory loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(INVENTORY_FILE))) {
            return (RoomInventory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous inventory state found.");
            return null;
        }
    }
}