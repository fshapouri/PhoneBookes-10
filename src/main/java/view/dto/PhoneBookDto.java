package view.dto;

import java.util.List;

public class PhoneBookDto {
    private String phonebookName;
    private String newPhoneBookName;
    private List<ContactDto> contactList;

    public PhoneBookDto(String phonebookName, List<ContactDto> contactList) {
        this.phonebookName = phonebookName;
        this.contactList = contactList;
    }

    public PhoneBookDto() {
    }

    public String getPhonebookName() {
        return phonebookName;
    }

    public void setPhonebookName(String phonebookName) {
        this.phonebookName = phonebookName;
    }

    public List<ContactDto> getcontactList() {
        return contactList;
    }

    public void setcontactList(List<ContactDto> contactList) {
        this.contactList = contactList;
    }

    public String getNewPhoneBookName() {return newPhoneBookName;}

    public void setNewPhoneBookName(String newPhoneBookName) {this.newPhoneBookName = newPhoneBookName;}

}
