package model.entity;

import java.util.List;

public class ContactEntity {

    private String firstname;
    private String lastname;
    private List<NumberEntity> numberList;
    private int phoneBookId;
    private int contactId;
    private String newfirstname;
    private String newlastname;

    public ContactEntity(String firstname, String lastname, List<NumberEntity> numberList,int phoneBookId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.numberList = numberList;
        this.phoneBookId=phoneBookId;
    }
    public ContactEntity(String firstname, String lastname, List<NumberEntity> numberList) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.numberList = numberList;
    }

    public ContactEntity() {}

    public int getPhoneBookId() {
        return phoneBookId;
    }

    public void setPhoneBookId(int phoneBookId) {this.phoneBookId = phoneBookId;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public List<NumberEntity> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<NumberEntity> numberList) {
        this.numberList = numberList;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public String getNewfirstname() {
        return newfirstname;
    }

    public void setNewfirstname(String newfirstname) {
        this.newfirstname = newfirstname;
    }

    public String getNewlastname() {
        return newlastname;
    }

    public void setNewlastname(String newlastname) {
        this.newlastname = newlastname;
    }




}
