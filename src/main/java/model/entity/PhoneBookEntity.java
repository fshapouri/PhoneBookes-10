package model.entity;

import java.util.List;

public class PhoneBookEntity {

    private String phonebookName;
    private List<ContactEntity> contactList;

    public PhoneBookEntity() {}

    public List<ContactEntity> getContactList() {
        return contactList;
    }

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
