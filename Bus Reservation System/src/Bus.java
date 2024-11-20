public class Bus {

    private int id;
    private String busno;
    private String destination;
    private int totalseats;
    private boolean[] seats;

    public Bus(String busno, String destination, int totalseats) {
        this.busno = busno;
        this.destination = destination;
        this.totalseats = totalseats;
        this.seats = new boolean[totalseats];
    }

    public Bus() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getBusno() {
        return busno;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalseats() {
        return totalseats;
    }

    public boolean isSeatAvailable(int seatno)
    {
        return !seats[seatno];
    }

    public void bookSeat(int seatno)
    {
        seats[seatno] = true;
    }


    public void displaySeat()
    {
        System.out.println("Seat Avilable for Bus No "+ busno +" to Destination :"+ destination);
        for (int i = 0; i < totalseats; i++)
        {
            System.out.print((i+1)+(seats[i] ? " Booked " : " Available ") + " | ");
        }
        System.out.println();
    }

    public void setSeats(boolean[] seats) {
        this.seats = seats;
    }
}
