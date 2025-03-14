public abstract class Aircraft implements Comparable<Aircraft> {
    private String name;
    private String model;
    private String tailNumber;

    public Aircraft(String name, String model, String tailNumber) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
    }

    public String getName() {
        return name;
    }
    public String getModel() {
        return model;
    }
    public String getTailNumber() {
        return tailNumber;
    }
    @Override
    public int compareTo(Aircraft other) {
        return this.name.compareTo(other.name);
    }

}




