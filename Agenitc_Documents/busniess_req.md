# Contacts Manager – Business Requirements

## 1. Purpose
The application is a CLI-based Contacts Manager used to store and manage contact information.

---

## 2. Core Features
- View Contacts
- Add Contact
- Edit Contact
- Delete Contact
- Search Contacts
- Exit Application

---

## 3. Contact Data Model
Each contact must contain:
- Name
- Age
- Phone Number
- Email

---

## 4. User Interaction Flow

The system must display a repeating menu:

1. View Contacts
2. Add Contact
3. Edit Contact
4. Delete Contact
5. Search Contact
6. Exit

- The menu must loop until the user selects Exit
- After every operation:
  "Press Enter to continue..."

---

## 5. Functional Requirements

### 5.1 View Contacts
- Display all contacts in table format
- Columns:
  - No
  - Name
  - Age
  - Phone
  - Email
- Show total number of contacts
- If no contacts:
  "No contacts."

---

### 5.2 Add Contact
- Prompt user for all fields
- Validate each field
- If invalid → re-prompt only that field
- On success:
  "✓ Added."

---

### 5.3 Edit Contact
- Display contacts list
- User selects contact by index (0 = cancel)
- Show current values in [brackets]
- If input is blank → keep old value
- Validate only updated fields
- On success:
  "✓ Updated."

---

### 5.4 Delete Contact
- Display contacts list
- User selects by index (0 = cancel)
- Ask confirmation:
  "Delete [Name]? (yes/no)"
- If yes:
  "✓ Deleted."
- If no:
  "Cancelled."

---

### 5.5 Search Contact
- Accept keyword input
- Perform case-insensitive match on:
  - Name
  - Phone
  - Email
- Display matching contacts in table
- Show result count
- If no matches:
  "No results for '[keyword]'."

---

## 6. Validation Rules

- Name:
  - Must not be empty
  - Only letters and spaces allowed

- Age:
  - Must be an integer
  - Range: 1 to 120

- Phone:
  - Digits with optional '+'
  - Length: 7 to 15

- Email:
  - Check with regex pattern: ^[a-zA-Z0-9]+([._][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-][a-zA-Z0-9]+)*\.[a-zA-Z]{2,}$

---

## 7. Constraints
- CLI-based only
- No database
- Data stored in memory during runtime only