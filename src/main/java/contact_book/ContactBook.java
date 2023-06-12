package contact_book;

import java.io.*;
import java.util.*;

public class ContactBook implements Iterable<Contact>, Serializable {
    private final List<Contact> contacts = new ArrayList<>();
    private final String CONTACTBACKUPFILE = "./src/main/java/contact_book/contacts.bak";

    public void addContact(final Contact newContact) {
        if (contacts.contains(newContact))
            throw new IllegalArgumentException(newContact + " already exists");
        contacts.add(newContact);
    }

    public void updateContact(final Contact contact, final Contact updatedContact) {
        int indexOfOldContact = contacts.indexOf(contact);

        if (indexOfOldContact != -1)
            contacts.set(indexOfOldContact, updatedContact);
        else {
            throw new IllegalArgumentException(contact + " does not exist in contact book");
        }
    }

    // TODO: how do I get this method to return -1 if not found?
    public Optional<Contact> findContact(String query) {
        if (isEmail(query)) return contacts.stream().filter(contact -> contact.email().equals(query)).findFirst();
        else return contacts.stream().filter(contact -> contact.name().equals(query)).findFirst();
    }

    private boolean isEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    public void backupContacts() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(CONTACTBACKUPFILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            System.out.println("\nBacking up contacts ...");
            objectOutputStream.writeObject(contacts);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.getStackTrace();
        }
    }

    public void restoreContacts() {
        try (FileInputStream fileInputStream = new FileInputStream(CONTACTBACKUPFILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            System.out.println("\nRestoring contacts ...");
            System.out.println(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.getStackTrace();
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void displayContacts() {
        System.out.println("Name\t\t\t\t|\tEmail");
        System.out.println("--------------------------------------");
        for (Contact contact : contacts) System.out.println(contact.name() + "\t\t\t" + contact.email());
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.iterator();
    }
}
