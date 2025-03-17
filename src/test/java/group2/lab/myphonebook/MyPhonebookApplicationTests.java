package group2.lab.myphonebook;

import group2.lab.myphonebook.contact.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MyPhonebookApplicationTests {
    @Autowired
    private ContactService contactService; // Внедряем сервис

    @Test
    void contextLoads() {
    }

    @Test
    void main() {
    }

    @Test
    void addContact() {
        Contact contact = new Contact(null, "John Doe", "12345678900", "Test Note");
        Contact savedContact = contactService.addContact(contact);
        assertNotNull(savedContact.getId());
        assertEquals("John Doe", savedContact.getFullName());
        assertEquals("12345678900", savedContact.getPhoneNumber());
        assertEquals("Test Note", savedContact.getNote());
    }

    @Test
    void updateContact() {
        Contact contact = new Contact(null, "John Doe", "12345678900", "Test Note");
        Contact savedContact = contactService.addContact(contact);
        savedContact.setFullName("Jane Doe");
        savedContact.setPhoneNumber("98765432100");
        savedContact.setNote("Updated Note");
        Contact updatedContact = contactService.addContact(savedContact);
        assertEquals("Jane Doe", updatedContact.getFullName());
        assertEquals("98765432100", updatedContact.getPhoneNumber());
        assertEquals("Updated Note", updatedContact.getNote());
    }

    @Test
    void deleteContact() {
        int count = contactService.getAllContacts().size();
        Contact contact = new Contact(null, "John Doe", "12345678900", "Test Note");
        Contact savedContact = contactService.addContact(contact);
        contactService.deleteContact(savedContact.getId());
        assertEquals(count, contactService.getAllContacts().size());
    }

    @Test
    void getContact() {
        Contact contact = new Contact(null, "John Doe", "12345678900", "Test Note");
        Contact savedContact = contactService.addContact(contact);
        Optional<Contact> retrievedContact = contactService.getContact(savedContact.getId());
        assertEquals("John Doe", retrievedContact.get().getFullName());
        assertEquals("12345678900", retrievedContact.get().getPhoneNumber());
        assertEquals("Test Note", retrievedContact.get().getNote());
    }
}
