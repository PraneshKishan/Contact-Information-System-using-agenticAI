import java.util.ArrayList;
import java.util.Scanner;

public class ContactsApp {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ContactManager CONTACT_MANAGER = new ContactManager();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            String choice = SCANNER.nextLine().trim();

            switch (choice) {
                case "1":
                    viewContacts();
                    pause();
                    break;
                case "2":
                    addContact();
                    pause();
                    break;
                case "3":
                    editContact();
                    pause();
                    break;
                case "4":
                    deleteContact();
                    pause();
                    break;
                case "5":
                    searchContacts();
                    pause();
                    break;
                case "6":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1 to 6.");
                    pause();
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Contacts Manager");
        System.out.println("1. View Contacts");
        System.out.println("2. Add Contact");
        System.out.println("3. Edit Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Search Contact");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void viewContacts() {
        displayContacts(CONTACT_MANAGER.getContacts(), "Total contacts");
    }

    private static void addContact() {
        String name = promptValidName("Name: ");
        int age = promptValidAge("Age: ");
        String phone = promptValidPhone("Phone: ");
        String email = promptValidEmail("Email: ");

        CONTACT_MANAGER.addContact(name, age, phone, email);
        System.out.println("\u2713 Added.");
    }

    private static void editContact() {
        ArrayList<Contact> contacts = CONTACT_MANAGER.getContacts();
        displayContacts(contacts, "Total contacts");

        if (contacts.isEmpty()) {
            return;
        }

        int selectedIndex = promptContactIndex(contacts.size(), "Select contact to edit (0 = cancel): ");
        if (selectedIndex == 0) {
            System.out.println("Cancelled.");
            return;
        }

        Contact contact = contacts.get(selectedIndex - 1);
        String name = promptOptionalName("Name [" + contact.getName() + "]: ", contact.getName());
        int age = promptOptionalAge("Age [" + contact.getAge() + "]: ", contact.getAge());
        String phone = promptOptionalPhone("Phone [" + contact.getPhone() + "]: ", contact.getPhone());
        String email = promptOptionalEmail("Email [" + contact.getEmail() + "]: ", contact.getEmail());

        CONTACT_MANAGER.updateContact(contact, name, age, phone, email);
        System.out.println("\u2713 Updated.");
    }

    private static void deleteContact() {
        ArrayList<Contact> contacts = CONTACT_MANAGER.getContacts();
        displayContacts(contacts, "Total contacts");

        if (contacts.isEmpty()) {
            return;
        }

        int selectedIndex = promptContactIndex(contacts.size(), "Select contact to delete (0 = cancel): ");
        if (selectedIndex == 0) {
            System.out.println("Cancelled.");
            return;
        }

        Contact contact = contacts.get(selectedIndex - 1);
        System.out.print("Delete [" + contact.getName() + "]? (yes/no): ");
        String confirmation = SCANNER.nextLine().trim();

        if ("yes".equalsIgnoreCase(confirmation)) {
            CONTACT_MANAGER.deleteContact(contact);
            System.out.println("\u2713 Deleted.");
            return;
        }

        System.out.println("Cancelled.");
    }

    private static void searchContacts() {
        System.out.print("Keyword: ");
        String keyword = SCANNER.nextLine().trim();
        ArrayList<Contact> results = CONTACT_MANAGER.searchContacts(keyword);

        if (results.isEmpty()) {
            System.out.println("No results for '" + keyword + "'.");
            System.out.println("Result count: 0");
            return;
        }

        displayContacts(results, "Result count");
    }

    private static void displayContacts(ArrayList<Contact> contacts, String countLabel) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts.");
            System.out.println(countLabel + ": 0");
            return;
        }

        System.out.printf("%-5s %-25s %-5s %-18s %-30s%n", "No", "Name", "Age", "Phone", "Email");
        System.out.println("-------------------------------------------------------------------------------------");

        for (int index = 0; index < contacts.size(); index++) {
            Contact contact = contacts.get(index);
            System.out.printf("%-5d %-25s %-5d %-18s %-30s%n",
                    index + 1,
                    contact.getName(),
                    contact.getAge(),
                    contact.getPhone(),
                    contact.getEmail());
        }

        System.out.println(countLabel + ": " + contacts.size());
    }

    private static String promptValidName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = SCANNER.nextLine().trim();

            if (CONTACT_MANAGER.isValidName(name)) {
                return name;
            }

            System.out.println("Invalid name. Please use letters and spaces only.");
        }
    }

    private static int promptValidAge(String prompt) {
        while (true) {
            System.out.print(prompt);
            String ageText = SCANNER.nextLine().trim();

            if (CONTACT_MANAGER.isValidAge(ageText)) {
                return CONTACT_MANAGER.parseAge(ageText);
            }

            System.out.println("Invalid age. Please enter a number between 1 and 120.");
        }
    }

    private static String promptValidPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = SCANNER.nextLine().trim();

            if (CONTACT_MANAGER.isValidPhone(phone)) {
                return phone;
            }

            System.out.println("Invalid phone. Please enter 7 to 15 digits with an optional leading '+'.");
        }
    }

    private static String promptValidEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = SCANNER.nextLine().trim();

            if (CONTACT_MANAGER.isValidEmail(email)) {
                return email;
            }

            System.out.println("Invalid email. Please enter a valid email address.");
        }
    }

    private static String promptOptionalName(String prompt, String currentName) {
        while (true) {
            System.out.print(prompt);
            String name = SCANNER.nextLine().trim();

            if (name.isEmpty()) {
                return currentName;
            }

            if (CONTACT_MANAGER.isValidName(name)) {
                return name;
            }

            System.out.println("Invalid name. Please use letters and spaces only.");
        }
    }

    private static int promptOptionalAge(String prompt, int currentAge) {
        while (true) {
            System.out.print(prompt);
            String ageText = SCANNER.nextLine().trim();

            if (ageText.isEmpty()) {
                return currentAge;
            }

            if (CONTACT_MANAGER.isValidAge(ageText)) {
                return CONTACT_MANAGER.parseAge(ageText);
            }

            System.out.println("Invalid age. Please enter a number between 1 and 120.");
        }
    }

    private static String promptOptionalPhone(String prompt, String currentPhone) {
        while (true) {
            System.out.print(prompt);
            String phone = SCANNER.nextLine().trim();

            if (phone.isEmpty()) {
                return currentPhone;
            }

            if (CONTACT_MANAGER.isValidPhone(phone)) {
                return phone;
            }

            System.out.println("Invalid phone. Please enter 7 to 15 digits with an optional leading '+'.");
        }
    }

    private static String promptOptionalEmail(String prompt, String currentEmail) {
        while (true) {
            System.out.print(prompt);
            String email = SCANNER.nextLine().trim();

            if (email.isEmpty()) {
                return currentEmail;
            }

            if (CONTACT_MANAGER.isValidEmail(email)) {
                return email;
            }

            System.out.println("Invalid email. Please enter a valid email address.");
        }
    }

    private static int promptContactIndex(int contactCount, String prompt) {
        while (true) {
            System.out.print(prompt);
            String indexText = SCANNER.nextLine().trim();

            try {
                int selectedIndex = Integer.parseInt(indexText);
                if (selectedIndex >= 0 && selectedIndex <= contactCount) {
                    return selectedIndex;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Invalid selection. Please enter a number.");
                continue;
            }

            System.out.println("Invalid selection. Please enter 0 to " + contactCount + ".");
        }
    }

    private static void pause() {
        System.out.print("Press Enter to continue...");
        SCANNER.nextLine();
    }
}
