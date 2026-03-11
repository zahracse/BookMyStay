import java.util.*;

// Reservation class (represents a confirmed booking)
class BookingRecord {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double cost;

    public BookingRecord(String reservationId, String guestName, String roomType, double cost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.cost = cost;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room: " + roomType +
                ", Cost: ₹" + cost;
    }
}

// Booking History (stores confirmed reservations)
class BookingHistory {
    private List<BookingRecord> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(BookingRecord reservation) {   // ✔ fixed
        history.add(reservation);
    }

    public List<BookingRecord> getAllReservations() {         // ✔ fixed
        return history;
    }
}

// Reporting Service (generates reports)
class BookingReportService {

    public void displayAllBookings(List<BookingRecord> reservations) {  // ✔ fixed
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("=== Booking History ===");
        for (BookingRecord r : reservations) {   // ✔ fixed
            System.out.println(r);
        }
    }

    public void generateSummary(List<BookingRecord> reservations) {  // ✔ fixed
        int totalBookings = reservations.size();
        double totalRevenue = 0;

        for (BookingRecord r : reservations) {   // ✔ fixed
            totalRevenue += r.getCost();
        }

        System.out.println("\n=== Booking Summary Report ===");
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        BookingRecord r1 = new BookingRecord("RES101", "Alice", "Deluxe", 3000);
        BookingRecord r2 = new BookingRecord("RES102", "Bob", "Suite", 5000);
        BookingRecord r3 = new BookingRecord("RES103", "Charlie", "Standard", 2000);

        // Add to history
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Display bookings
        reportService.displayAllBookings(history.getAllReservations());

        // Generate report
        reportService.generateSummary(history.getAllReservations());
    }
}