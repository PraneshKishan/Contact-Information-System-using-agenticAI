import java.util.UUID;

public class Contact {
    private final UUID id;
    private String name;
    private int age;
    private String phone;
    private String email;

    public Contact(String name, int age, String phone, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
