public class Runway {
    private String id;

    public Runway(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Runway " + id;
    }
}

