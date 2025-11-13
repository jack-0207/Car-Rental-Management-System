import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CustomerDashboard extends JFrame {
    private ArrayList<Car> carList;
    private String customerName;
    private String customerEmail;

    public CustomerDashboard(ArrayList<Car> carList) {
        this.carList = carList;
        setTitle("Customer Dashboard");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ✅ Table model to show cars
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Car ID", "Brand", "Model", "Price/Day", "Available"}, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ✅ Load available cars
        for (Car c : carList) {
            model.addRow(new Object[]{c.getId(), c.getBrand(), c.getModel(), c.getPricePerDay(), c.isAvailable()});
        }

        // ✅ Rent button
        JButton rentBtn = new JButton("Rent Selected Car");
        add(rentBtn, BorderLayout.SOUTH);

        rentBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a car first!");
                return;
            }

            int days;
            try {
                days = Integer.parseInt(JOptionPane.showInputDialog("Enter number of days:"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number of days!");
                return;
            }

            double price = Double.parseDouble(model.getValueAt(row, 3).toString());
            double total = price * days;

            JOptionPane.showMessageDialog(this, "Total Rent: Rs. " + total);
        });

        setVisible(true);
    }
}
