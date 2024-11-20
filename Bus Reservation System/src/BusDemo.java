import java.sql.SQLException;
import java.util.Scanner;

public class BusDemo {
        public static void main(String[] args) throws SQLException {
            Scanner scanner = new Scanner(System.in);
            BusServationSystem system = new BusServationSystem();

            while (true) {
                System.out.println("\t\t\tWelcome to Bharath Travels");
                System.out.println("Bus Reservation System");
                System.out.println("1. Add Bus");
                System.out.println("2. View Available Buses");
                System.out.println("3. Reserve Seat");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter bus number: ");
                        String busNumber = scanner.nextLine();
                        System.out.print("Enter destination: ");
                        String destination = scanner.next();
                        System.out.print("Enter total seats: ");
                        int totalSeats = scanner.nextInt();
                        system.addBus(busNumber, destination, totalSeats);
                        break;
                    case 2:
                        system.displayAvailableBus();
                        break;
                    case 3:
                        System.out.print("Enter bus number: ");
                        String busToReserve = scanner.next();
                        System.out.print("Enter seat number to reserve: ");
                        int seatNumber = scanner.nextInt();
                        System.out.print("Enter passenger name: ");
                        scanner.nextLine();
                        String passengerName = scanner.nextLine();
                        system.reserveSeat(busToReserve, seatNumber,passengerName);
                        break;
                    case 4:
                        System.out.println("Exiting the system. Thank you!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
}