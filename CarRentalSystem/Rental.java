import java.io.Serializable;

public class Rental implements Serializable {
    private String customerEmail;
    private String customerName;
    private String carId;
    private String carModel;
    private int days;
    private double totalCost;

    public Rental(String customerEmail, String customerName, String carId, String carModel, int days, double totalCost) {
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.carId = carId;
        this.carModel = carModel;
        this.days = days;
        this.totalCost = totalCost;
    }

    public String getCustomerEmail() { return customerEmail; }
    public String getCustomerName() { return customerName; }
    public String getCarId() { return carId; }
    public String getCarModel() { return carModel; }
    public int getDays() { return days; }
    public double getTotalCost() { return totalCost; }
}
