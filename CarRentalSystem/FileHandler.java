import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String CAR_FILE = "cars.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String RENTAL_FILE = "rentals.txt";

    // ==============================
    // 🚗 CAR HANDLING
    // ==============================
    public static ArrayList<Car> loadCars() {
        ArrayList<Car> carList = new ArrayList<>();
        File file = new File(CAR_FILE);

        if (!file.exists()) return carList;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String brand = parts[1];
                    String model = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    boolean available = Boolean.parseBoolean(parts[4]);
                    carList.add(new Car(id, brand, model, price, available));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading cars: " + e.getMessage());
        }

        return carList;
    }

    public static void saveCars(ArrayList<Car> carList) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CAR_FILE))) {
            for (Car c : carList) {
                pw.println(c.getId() + "," + c.getBrand() + "," + c.getModel() + "," + c.getPricePerDay() + "," + c.isAvailable());
            }
        } catch (IOException e) {
            System.out.println("Error saving cars: " + e.getMessage());
        }
    }

    // ==============================
    // 👤 CUSTOMER HANDLING
    // ==============================
    public static ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        File file = new File(CUSTOMER_FILE);

        if (!file.exists()) return customers;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    customers.add(new Customer(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }

        return customers;
    }

    public static void saveCustomers(ArrayList<Customer> customers) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CUSTOMER_FILE))) {
            for (Customer c : customers) {
                pw.println(c.getName() + "," + c.getEmail() + "," + c.getContact());
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    // ==============================
    // 📜 RENTAL HANDLING
    // ==============================
    public static ArrayList<Rental> loadRentals() {
        ArrayList<Rental> rentals = new ArrayList<>();
        File file = new File(RENTAL_FILE);

        if (!file.exists()) return rentals;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    rentals.add(new Rental(parts[0], parts[1], parts[2], parts[3],
                            Integer.parseInt(parts[4]), Double.parseDouble(parts[5])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading rentals: " + e.getMessage());
        }

        return rentals;
    }

    public static void saveRentals(ArrayList<Rental> rentals) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RENTAL_FILE))) {
            for (Rental r : rentals) {
                pw.println(r.getCustomerEmail() + "," + r.getCustomerName() + "," +
                        r.getCarId() + "," + r.getCarModel() + "," +
                        r.getDays() + "," + r.getTotalCost());
            }
        } catch (IOException e) {
            System.out.println("Error saving rentals: " + e.getMessage());
        }
    }
}
