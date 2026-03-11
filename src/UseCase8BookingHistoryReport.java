import java.util.*;

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Use the existing BookingRecord class
        List<BookingRecord> bookingHistory = new ArrayList<>();

        System.out.println("=== Book My Stay: Booking History & Reporting ===");

        while (true) {
            System.out.println("\nOptions: 1. Add Booking  2. View History  3. Generate Report  4. Exit");
            System.out.print("Select option: ");
            String option = sc.nextLine();

            switch (option) {
                case "1": // Add Booking
                    System.out.print("Enter Reservation ID: ");
                    String resId = sc.nextLine();

                    System.out.print("Enter Guest Name: ");
                    String guestName = sc.nextLine();

                    System.out.print("Enter Room Type: ");
                    String roomType = sc.nextLine();

                    System.out.print("Enter Number of Rooms: ");
                    int roomsBooked;
                    try {
                        roomsBooked = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number input.");
                        break;
                    }

                    // Create and store booking
                    BookingRecord booking = new BookingRecord(resId, guestName, roomType, roomsBooked);
                    bookingHistory.add(booking);
                    System.out.println("Booking added successfully: " + booking);
                    break;

                case "2": // View History
                    System.out.println("\n=== Booking History ===");
                    if (bookingHistory.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        for (BookingRecord b : bookingHistory) {
                            System.out.println(b);
                        }
                    }
                    break;

                case "3": // Generate Summary Report
                    System.out.println("\n=== Booking Summary Report ===");
                    int totalBookings = bookingHistory.size();
                    int totalRooms = 0;
                    for (BookingRecord b : bookingHistory) {
                        totalRooms += b.getRoomsBooked();
                    }
                    System.out.println("Total Bookings: " + totalBookings);
                    System.out.println("Total Rooms Booked: " + totalRooms);
                    break;

                case "4": // Exit
                    System.out.println("Exiting Booking History module.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}