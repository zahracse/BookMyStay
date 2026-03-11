import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 5: Booking Request Queue (FIFO)
 *
 * Demonstrates fair handling of booking requests using a Queue.
 *
 * @author Zahra Jeelani
 * @version 5.1
 */

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Book My Stay - Version 5.1");
        System.out.println("   Booking Request Queue");
        System.out.println("=================================");

        // Create queue for booking requests
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Guests submit booking requests
        Reservation r1 = new Reservation("Aisha", "Single Room");
        Reservation r2 = new Reservation("Rahul", "Double Room");
        Reservation r3 = new Reservation("Fatima", "Suite Room");

        // Add requests to queue
        bookingQueue.add(r1);
        bookingQueue.add(r2);
        bookingQueue.add(r3);

        System.out.println("\nBooking Requests Received:");

        // Display queue contents
        for (Reservation reservation : bookingQueue) {
            reservation.displayReservation();
        }

        System.out.println("\nRequests stored in FIFO order and waiting for allocation.");
    }
}