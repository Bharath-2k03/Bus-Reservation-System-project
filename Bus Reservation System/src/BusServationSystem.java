import java.sql.*;
import java.util.ArrayList;


public class BusServationSystem {

    private ArrayList<Bus> buses;

    String url ="jdbc:mysql://localhost:3306/bus_db";
    String username = "root";
    String password ="Mysql@2003";

    Connection connection = DriverManager.getConnection(url,username,password);

    public BusServationSystem() throws SQLException {
        this.buses = new ArrayList<>();
    }

    public void addBus(String busno,String destination,int totalseats) throws SQLException {
        String query = "insert into buses(busno, destination, totalseats) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setNString(1,busno);
        preparedStatement.setNString(2,destination);
        preparedStatement.setInt(3,totalseats);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if(resultSet.next())
        {
            int busid = resultSet.getInt(1);
            buses.add(new Bus(busno,destination,totalseats));
            System.out.println("Bus "+busno+" Added successfully");
        }

    }

    public void displayAvailableBus() throws SQLException {
        String query = "SELECT id, busno, destination, totalseats FROM buses";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<Bus> busList = new ArrayList<>();

        if (resultSet.equals(busList.isEmpty())) {
            System.out.println("No Bus Available");
            return;
        }

        System.out.println("Available Buses:");

        while (resultSet.next()) {
            String busNo = resultSet.getString("busno");
            String destination = resultSet.getString("destination");
            int totalSeats = resultSet.getInt("totalseats");
            Bus bus = new Bus(busNo, destination, totalSeats);
            busList.add(bus);

            System.out.println("Bus No: " + busNo +
                    "\nTo Destination: " + destination +
                    "\nTotal Seats: " + totalSeats);

            bus.displaySeat();
        }
    }


    public void reserveSeat(String busno, int seatNo, String passengerName) throws SQLException {
        // Check if the bus exists and retrieve its id
        String busQuery = "SELECT id, totalSeats FROM buses WHERE busno = ?";
        PreparedStatement busStmt = connection.prepareStatement(busQuery);
        busStmt.setString(1, busno);
        ResultSet busRs = busStmt.executeQuery();

        if (busRs.next()) {
            int busId = busRs.getInt("id");
            int totalSeats = busRs.getInt("totalSeats");

            // Check if the seat number is valid
            if (seatNo < 1 || seatNo > totalSeats) {
                System.out.println("Invalid Seat Number");
                return;
            }

            // Check if the seat is already booked
            String seatQuery = "SELECT * FROM bookings WHERE busId = ? AND seatNo = ?";
            PreparedStatement seatStmt = connection.prepareStatement(seatQuery);
            seatStmt.setInt(1, busId);
            seatStmt.setInt(2, seatNo);
            ResultSet seatRs = seatStmt.executeQuery();

            if (seatRs.next()) {
                System.out.println("Sorry, Seat " + seatNo + " is already booked");
            } else {
                // Book the seat
                String bookSeatQuery = "INSERT INTO bookings (busId, seatNo, passengerName) VALUES (?, ?, ?)";
                PreparedStatement bookSeatStmt = connection.prepareStatement(bookSeatQuery);
                bookSeatStmt.setInt(1, busId);
                bookSeatStmt.setInt(2, seatNo);
                bookSeatStmt.setString(3, passengerName);
                bookSeatStmt.executeUpdate();

                System.out.println("Seat: " + seatNo + " on Bus " + busno + " has been booked successfully");
            }

        } else {
            System.out.println("Bus Number " + busno + " not found");
        }
    }
}
