import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private ArrayList<Car> carList;
    private ArrayList<Customer> customerList;

    public LoginFrame() {
        // ✅ Load cars and customers
        carList = FileHandler.loadCars();
        customerList = FileHandler.loadCustomers();

        setTitle("Car Rental Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");
        panel.add(loginBtn);
        panel.add(registerBtn);

        add(panel);

        loginBtn.addActionListener(e -> handleLogin());
        registerBtn.addActionListener(e -> new RegisterFrame(customerList));

        setVisible(true);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // ✅ Admin login
        if (email.equalsIgnoreCase("admin@rental.com") && password.equals("admin")) {
            new AdminDashboard(carList);
            dispose();
            return;
        }

        // ✅ Customer login check
        for (Customer c : customerList) {
            if (c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(password)) {
                new CustomerDashboard(carList);
                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "❌ Invalid Email or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
    }
}
