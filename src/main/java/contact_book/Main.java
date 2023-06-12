package contact_book;

public class Main {
    public static void main(String[] args) {

        ContactBook contactBook = new ContactBook();
        final String FRANCIS = "Francis Kusi";

        // create contacts
        Contact contact0 = new Contact("Gabriel Rockson", "thegabe@gmail.com");
        Contact contact1 = new Contact("Anastasia Adzraku", "ann@gmail.com");
        Contact contact2 = new Contact("Naomi Adzraku", "naa@gmail.com");
        Contact contact3 = new Contact(FRANCIS, "francs@gmail.com");

        // add the contacts to the contact book
        contactBook.addContact(contact0);
        contactBook.addContact(contact1);
        contactBook.addContact(contact2);
        contactBook.addContact(contact3);

        // adding an already existing contact
        try {
            contactBook.addContact(contact3);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // display all the contacts before update
        System.out.println("Contacts before update");
        contactBook.displayContacts();

        System.out.println();

        // update a non-existent contact
        Contact updatedContact = new Contact(FRANCIS, "kusi@gmail.com");
        Contact nonExistentContact = new Contact("Francis Kusil", "francs@gmail.com");

        try {
            contactBook.updateContact(nonExistentContact, updatedContact);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // update an existing contact
        Contact anotherUpdatedContact = new Contact(FRANCIS, "kusi@gmail.com");

        try {
            contactBook.updateContact(contact3, anotherUpdatedContact);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // display all the contacts after update
        System.out.println("Contacts after update");
        contactBook.displayContacts();

        // find contact by email
        System.out.println();
        System.out.println(contactBook.findContact("kusi@gmail.com")); // search by email
        System.out.println(contactBook.findContact("Gabriel Rockson")); // search by name
        System.out.println(contactBook.findContact("Gabby")); // not found

        // backup contact book
        contactBook.backupContacts();

        // restore contact book backup
        contactBook.restoreContacts();

    }
}
