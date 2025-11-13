import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected String email;
    protected String password;
    protected String contact;

    public User(String name, String email, String password, String contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getContact() { return contact; }
}