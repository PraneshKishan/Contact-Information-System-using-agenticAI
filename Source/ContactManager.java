import java.util.ArrayList;
import java.util.regex.Pattern;

public class ContactManager {
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 120;
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z ]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{7,15}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9]+([._][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$");

    private final ArrayList<Contact> contacts;

    public ContactManager() {
        this.contacts = new ArrayList<>();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void addContact(String name, int age, String phone, String email) {
        contacts.add(new Contact(name, age, phone, email));
    }

    public void updateContact(Contact contact, String name, int age, String phone, String email) {
        contact.setName(name);
        contact.setAge(age);
        contact.setPhone(phone);
        contact.setEmail(email);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public ArrayList<Contact> searchContacts(String keyword) {
        ArrayList<Contact> results = new ArrayList<>();
        String normalizedKeyword = keyword.toLowerCase();

        for (Contact contact : contacts) {
            if (containsIgnoreCase(contact.getName(), normalizedKeyword)
                    || containsIgnoreCase(contact.getPhone(), normalizedKeyword)
                    || containsIgnoreCase(contact.getEmail(), normalizedKeyword)) {
                results.add(contact);
            }
        }

        return results;
    }

    public boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public boolean isValidAge(String ageText) {
        if (ageText == null) {
            return false;
        }

        try {
            int age = Integer.parseInt(ageText);
            return age >= MIN_AGE && age <= MAX_AGE;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public int parseAge(String ageText) {
        return Integer.parseInt(ageText);
    }

    private boolean containsIgnoreCase(String value, String normalizedKeyword) {
        return value.toLowerCase().contains(normalizedKeyword);
    }
}
