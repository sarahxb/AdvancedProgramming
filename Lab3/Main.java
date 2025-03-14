//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Airliner airliner1 = new Airliner("SkyExpress", "Boeing 737", "A123", 180);
        Freighter freighter1 = new Freighter("CargoMaster", "Boeing 747F", "B456", 120000);
        Drone drone1 = new Drone("DeliveryBot", "DJI X900", "D789", 50, 120);


        CargoCapable[] cargoAircraft = {freighter1, drone1};

        System.out.println("Cargo-Capable Aircraft:");
        for (CargoCapable aircraft : cargoAircraft) {
            System.out.println(" - " + ((Aircraft) aircraft).getName() +  " (" + ((Aircraft) aircraft).getModel() + "), Max Payload: " + aircraft.getMaximumPayload() + " kg");
        }
    }
    }
