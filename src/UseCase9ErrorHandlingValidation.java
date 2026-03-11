import java.util.*;

// Custom Exception for Invalid Bookings
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ✅ Use existing RoomInventory class from previous use case
        RoomInventory inventory = new RoomInventory();

        // ✅ Use existing BookingRecord class from previous use case
        List<BookingRecord> bookings = new ArrayList<>();

        System.out.println("=== Welcome to Book My Stay ===");

        while (true) {
            inventory.displayInventory();

            System.out.print("\nEnter Guest Name (or 'exit' to quit): ");
            String guestName = sc.nextLine();
            if (guestName.equalsIgnoreCase("exit")) break;

            System.out.print("Enter Room Type: ");
            String roomType = sc.nextLine();

            System.out.print("Enter Number of Rooms: ");
            int roomsRequested;
            try {
                roomsRequested = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number input. Try again.");
                continue;
            }

            try {
                // Validate and book using existing RoomInventory class
                inventory.bookRooms(roomType, roomsRequested);

                // Create booking record using existing BookingRecord class
                String reservationId = "RES" + (bookings.size() + 1);
                BookingRecord booking = new BookingRecord(reservationId, guestName, roomType, roomsRequested);
                bookings.add(booking);

                System.out.println("Booking Confirmed: " + booking);

            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }

        System.out.println("\n=== All Confirmed Bookings ===");
        for (BookingRecord b : bookings) {
            System.out.println(b);
        }

        sc.close();
    }
}