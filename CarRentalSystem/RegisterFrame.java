import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField, contactField;
    private JPasswordField passwordField;
    private JButton registerBtn, cancelBtn;
    private ArrayList<Customer> customerList;

    public RegisterFrame(ArrayList<Customer> customerList) {
        this.customerList = customerList;

        setTitle("Customer Registration");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input fields
        panel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Contact No:"));
        contactField = new JTextField();
        panel.add(contactField);

        registerBtn = new JButton("Register");
        cancelBtn = new JButton("Cancel");
        panel.add(registerBtn);
        panel.add(cancelBtn);

        add(panel);

        // ✅ Register button logic
        registerBtn.addActionListener(e -> handleRegistration());

        // Cancel button closes window
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void handleRegistration() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String contact = contactField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Check for duplicate email
        for (Customer c : customerList) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                JOptionPane.showMessageDialog(this, "⚠️ Email already registered!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // ✅ Add to list and save
        Customer newCustomer = new Customer(name, email, password, contact);
        customerList.add(newCustomer);
        FileHandler.saveCustomers(customerList);

        JOptionPane.showMessageDialog(this, "✅ Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
