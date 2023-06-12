package contact_book;

import java.io.Serializable;

public record Contact(String name, String email) implements Serializable {}
