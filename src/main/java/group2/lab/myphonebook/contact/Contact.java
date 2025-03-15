package group2.lab.myphonebook.contact;

import jakarta.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq_gen")
    @SequenceGenerator(name = "contact_seq_gen", sequenceName = "contact_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;
    private String note;

    public Contact() {}

    public Contact(Long id, String fullName, String phoneNumber, String note) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
