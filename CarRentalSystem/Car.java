import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean available;

    public Car(String id, String brand, String model, double pricePerDay, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    public String getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}