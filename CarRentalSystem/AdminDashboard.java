import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {
    private DefaultTableModel carTableModel, customerTableModel, rentalTableModel;
    private ArrayList<Car> carList;
    private ArrayList<Customer> customerList;
    private ArrayList<Rental> rentalList;

    public AdminDashboard(ArrayList<Car> carList) {
        this.carList = carList;
        this.customerList = FileHandler.loadCustomers();
        this.rentalList = FileHandler.loadRentals();

        setTitle("Admin Dashboard - Car Rental System");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // ======================
        // 🚗 CARS TAB
        // ======================
        JPanel carPanel = new JPanel(new BorderLayout());
        carTableModel = new DefaultTableModel(new Object[]{"Car ID", "Brand", "Model", "Price/Day", "Available"}, 0);
        JTable carTable = new JTable(carTableModel);
        carPanel.add(new JScrollPane(carTable), BorderLayout.CENTER);

        JPanel carBtnPanel = new JPanel();
        JButton addBtn = new JButton("Add Car");
        JButton deleteBtn = new JButton("Delete Car");
        carBtnPanel.add(addBtn);
        carBtnPanel.add(deleteBtn);
        carPanel.add(carBtnPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Cars", carPanel);

        // ======================
        // 👤 CUSTOMERS TAB
        // ======================
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerTableModel = new DefaultTableModel(new Object[]{"Name", "Email", "Contact"}, 0);
        JTable customerTable = new JTable(customerTableModel);
        customerPanel.add(new JScrollPane(customerTable), BorderLayout.CENTER);
        tabbedPane.addTab("Customers", customerPanel);

        // ======================
        // 📜 RENTALS TAB
        // ======================
        JPanel rentalPanel = new JPanel(new BorderLayout());
        rentalTableModel = new DefaultTableModel(new Object[]{"Customer", "Email", "Car ID", "Model", "Days", "Total Cost"}, 0);
        JTable rentalTable = new JTable(rentalTableModel);
        rentalPanel.add(new JScrollPane(rentalTable), BorderLayout.CENTER);
        tabbedPane.addTab("Rentals", rentalPanel);

        add(tabbedPane);

        refreshCarTable();
        refreshCustomerTable();
        refreshRentalTable();

        addBtn.addActionListener(e -> {
            try {
                String id = JOptionPane.showInputDialog("Enter Car ID:");
                String brand = JOptionPane.showInputDialog("Enter Brand:");
                String model = JOptionPane.showInputDialog("Enter Model:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Price per Day:"));
                Car newCar = new Car(id, brand, model, price, true);
                carList.add(newCar);
                FileHandler.saveCars(carList);
                refreshCarTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = carTable.getSelectedRow();
            if (row >= 0) {
                String carId = carTableModel.getValueAt(row, 0).toString();
                carList.removeIf(c -> c.getId().equals(carId));
                FileHandler.saveCars(carList);
                refreshCarTable();
            }
        });

        setVisible(true);
    }

    private void refreshCarTable() {
        carTableModel.setRowCount(0);
        for (Car c : carList) {
            carTableModel.addRow(new Object[]{c.getId(), c.getBrand(), c.getModel(), c.getPricePerDay(), c.isAvailable()});
        }
    }

    private void refreshCustomerTable() {
        customerTableModel.setRowCount(0);
        for (Customer c : customerList) {
            customerTableModel.addRow(new Object[]{c.getName(), c.getEmail(), c.getContact()});
        }
    }

    private void refreshRentalTable() {
        rentalTableModel.setRowCount(0);
        for (Rental r : rentalList) {
            rentalTableModel.addRow(new Object[]{
                    r.getCustomerName(),
                    r.getCustomerEmail(),
                    r.getCarId(),
                    r.getCarModel(),
                    r.getDays(),
                    r.getTotalCost()
            });
        }
    }
}
