import java.util.*;

public class UseCase10BookingCancellation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        List<BookingRecord> bookings = new ArrayList<>();
        Stack<String> canceledReservations = new Stack<>(); // Track canceled IDs

        System.out.println("=== Book My Stay: Booking Cancellation & Inventory Rollback ===");

        while (true) {
            inventory.displayInventory();

            System.out.println("\nOptions: 1. Book  2. Cancel  3. View Bookings  4. Exit");
            System.out.print("Select option: ");
            String option = sc.nextLine();

            switch (option) {
                case "1": // Booking
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

                case "2": // Cancellation
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings available to cancel.");
                        break;
                    }

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
                        System.out.println("Reservation ID not found or already canceled.");
                    } else {
                        // Remove booking and rollback inventory
                        bookings.remove(toCancel);
                        inventory.cancelBooking(toCancel.getRoomType(), toCancel.getRoomsBooked());
                        canceledReservations.push(toCancel.getReservationId());

                        System.out.println("Booking canceled successfully: " + toCancel);
                    }
                    break;

                case "3": // View current bookings
                    System.out.println("\n=== Current Bookings ===");
                    if (bookings.isEmpty()) {
                        System.out.println("No confirmed bookings.");
                    } else {
                        for (BookingRecord b : bookings) {
                            System.out.println(b);
                        }
                    }
                    break;

                case "4": // Exit
                    System.out.println("Thank you for using Book My Stay!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}