package com.example.phonebook.controller;

import com.example.phonebook.model.Contact;
import com.example.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "templates/list"; 
    }

    @GetMapping("/new")
    public String newContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "templates/new"; 
    }

    @PostMapping
    public String createContact(@ModelAttribute Contact contact) {
        contactService.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/{id}/edit")
    public String editContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "templates/edit"; 
    }

    @PostMapping("/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id);
        contactService.save(contact);
        return "redirect:/contacts";
    }
}
