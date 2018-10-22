package model.entity;

import java.util.List;

public class PhoneBookEntity {
    private int phoneBookId;
    private String phonebookName;
    private List<ContactEntity> contactList;

    public PhoneBookEntity(int phoneBookId, String phonebookName, List<ContactEntity> contactList) {
        this.phoneBookId = phoneBookId;
        this.phonebookName = phonebookName;
        this.contactList = contactList;
    }

    public PhoneBookEntity() {}

    public int getPhoneBookId() {return phoneBookId;}

    public void setPhoneBookId(int phoneBookId) {this.phoneBookId = phoneBookId;}

    public List<ContactEntity> getContactList() {return contactList;}

    public void setContactList(List<ContactEntity> contactList) {
        this.contactList = contactList;
    }

    public String getPhonebookName() {
        return phonebookName;
    }

    public void setPhonebookName(String phonebookName) {
        this.phonebookName = phonebookName;
    }

}
