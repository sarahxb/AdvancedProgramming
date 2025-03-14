public class Drone extends Aircraft implements CargoCapable {
    private double maxPayload;
    private int batteryLife;

    public Drone(String name, String model, String tailNumber, double maxPayload, int batteryLife) {
        super(name, model, tailNumber);
        this.maxPayload = maxPayload;
        this.batteryLife = batteryLife;
    }

    @Override
    public double getMaximumPayload() {
        return maxPayload;
    }

    public int getBatteryLife() {
        return batteryLife;
    }


}
