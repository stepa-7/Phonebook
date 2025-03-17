package group2.lab.myphonebook;

import group2.lab.myphonebook.contact.Contact;
import group2.lab.myphonebook.contact.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "list";
    }

    @GetMapping("/new")
    public String newContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String editContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        model.addAttribute("contact", contact);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    // Метод для создания контакта с проверкой валидации
    @PostMapping
    public String createContact(@Valid @ModelAttribute Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        contactService.addContact(contact);
        return "redirect:/contacts";
    }

    // Метод для обновления контакта с проверкой валидации
    @PostMapping("/{id}")
    public String updateContact(@PathVariable Long id, @Valid @ModelAttribute Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        contact.setId(id);
        contactService.addContact(contact);
        return "redirect:/contacts";
    }

    // API эндпоинты – также добавляем валидацию

    @GetMapping("/api")
    @ResponseBody
    public List<Contact> getAllContactsApi() {
        return contactService.getAllContacts();
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<?> addContactApi(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        contactService.addContact(contact);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> updateContactApi(@PathVariable Long id, @Valid @RequestBody Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Contact existingContact = contactService.getContact(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        existingContact.setFullName(contact.getFullName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setNote(contact.getNote());
        contactService.addContact(existingContact);
        return ResponseEntity.ok(existingContact);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteContactApi(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
