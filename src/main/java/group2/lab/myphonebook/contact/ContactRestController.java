package group2.lab.myphonebook.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactRestController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping("/contacts")
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact existingContact = contactService.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        existingContact.setFullName(contact.getFullName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setNote(contact.getNote());
        return contactService.addContact(existingContact);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
