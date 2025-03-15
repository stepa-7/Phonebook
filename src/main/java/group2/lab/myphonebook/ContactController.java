package group2.lab.myphonebook;

import group2.lab.myphonebook.contact.Contact;
import group2.lab.myphonebook.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "list"; // Было "templates/list"
    }

    @GetMapping("/new")
    public String newContactForm(Model model) {
        if (result.hasErrors()) {
        return "contacts/new"; 
    }
    contactRepository.save(contact);
    return "redirect:/contacts";
        model.addAttribute("contact", new Contact());
        return "new"; // Было "templates/new"
    }

    @GetMapping("/{id}/edit")
    public String editContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        model.addAttribute("contact", contact);
        return "edit"; // Было "templates/edit"
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    @PostMapping
    public String createContact(@ModelAttribute Contact contact) {
        contactService.addContact(contact);
        return "redirect:/contacts";
    }


    @PostMapping("/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id);
        contactService.addContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Contact> getAllContactsApi() {
        return contactService.getAllContacts();
    }

    @PostMapping("/api")
    @ResponseBody
    public void addContactApi(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Contact updateContactApi(@PathVariable Long id, @RequestBody Contact contact) {
        Contact existingContact = contactService.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        existingContact.setFullName(contact.getFullName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setNote(contact.getNote());
        return contactService.addContact(existingContact);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteContactApi(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
