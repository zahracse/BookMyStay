import java.util.*;
import java.util.concurrent.*;

// Represents a single booking request
class BookingRequest {
    String guestName;
    String roomType;
    int roomsRequested;

    public BookingRequest(String guestName, String roomType, int roomsRequested) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomsRequested = roomsRequested;
    }
}

// Thread-safe booking processor
class BookingProcessor implements Runnable {

    private RoomInventory inventory;
    private List<BookingRecord> bookings;
    private BookingRequest request;

    public BookingProcessor(RoomInventory inventory, List<BookingRecord> bookings, BookingRequest request) {
        this.inventory = inventory;
        this.bookings = bookings;
        this.request = request;
    }

    @Override
    public void run() {
        synchronized (inventory) { // critical section for inventory update
            try {
                inventory.bookRooms(request.roomType, request.roomsRequested);

                // Generate reservation ID safely
                String reservationId = "RES" + (bookings.size() + 1);
                BookingRecord booking = new BookingRecord(reservationId,
                        request.guestName,
                        request.roomType,
                        request.roomsRequested);

                bookings.add(booking);
                System.out.println("Booking Confirmed by " + Thread.currentThread().getName() + ": " + booking);
            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed by " + Thread.currentThread().getName() + ": " + e.getMessage());
            }
        }
    }
}

public class UseCase11ConcurrentBooking {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        List<BookingRecord> bookings = Collections.synchronizedList(new ArrayList<>());

        // Sample concurrent booking requests
        BookingRequest[] requests = {
                new BookingRequest("Alice", "Standard", 2),
                new BookingRequest("Bob", "Deluxe", 1),
                new BookingRequest("Charlie", "Standard", 3),
                new BookingRequest("David", "Suite", 1),
                new BookingRequest("Eve", "Deluxe", 2)
        };

        // Executor for running concurrent threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (BookingRequest req : requests) {
            executor.submit(new BookingProcessor(inventory, bookings, req));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display final inventory and bookings
        System.out.println("\n=== Final Inventory ===");
        inventory.displayInventory();

        System.out.println("\n=== Confirmed Bookings ===");
        for (BookingRecord b : bookings) {
            System.out.println(b);
        }
    }
}