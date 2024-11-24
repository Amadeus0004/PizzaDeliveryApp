public class DeliveryStaff {
    private String name;
    private String id;
    private boolean available;

    public DeliveryStaff(String name, String id) {
        this.name = name;
        this.id = id;
        this.available = true; // Staff is available when created
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}