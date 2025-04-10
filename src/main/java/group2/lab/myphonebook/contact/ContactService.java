package group2.lab.myphonebook.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public Optional<Contact> getContact(Long id) {
        return contactRepository.findById(id);
    }
}
