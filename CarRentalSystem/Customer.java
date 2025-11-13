public class Customer {
    private String name;
    private String email;
    private String password;
    private String contact;

    // ✅ 4-argument constructor
    public Customer(String name, String email, String password, String contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    // ✅ NEW: 3-argument constructor (for FileHandler)
    public Customer(String name, String email, String password) {
        this(name, email, password, "N/A");  // default contact if missing
    }

    // ✅ Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getContact() { return contact; }
}
