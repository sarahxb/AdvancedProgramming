public class Airliner extends Aircraft implements PassengerCapable {
    private int seatCount;

    public Airliner(String name, String model, String tailNumber, int seatCount) {
        super(name, model, tailNumber);
        this.seatCount = seatCount;
    }

    @Override
    public int getSeatCount() {
        return seatCount;
    }


}
