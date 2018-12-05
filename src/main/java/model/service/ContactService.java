package model.service;

import dao.ContactDao;
import model.entity.ContactEntity;
import model.entity.PhoneBookEntity;
import view.dto.ContactDto;

import java.util.List;

public class ContactService {
    private final static ContactService instance= new ContactService();
    private ContactDao contactDao = ContactDao.getInstance();
    private NumberService numberService = NumberService.getInstance();

    private ContactService(){
    }

    public static ContactService getInstance(){
        return instance;
    }

    boolean insertContact(PhoneBookEntity phoneBookEntity) {
        int contactId;
        boolean result = false;
        try {
            for (ContactEntity contactEntity : phoneBookEntity.getContactList()) {
                contactEntity.setPhoneBookId(phoneBookEntity.getPhoneBookId());
                if (!(contactDao.searchContactById(contactEntity))) {
                    contactDao.insertContact(contactEntity);
                    contactId = contactDao.selectContact(contactEntity);
                    contactEntity.setContactId(contactId);
                    numberService.insertNumber(contactEntity);
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean searchContact(ContactEntity contactEntity) {
        boolean result = false;
        try {
            result = contactDao.searchContactByName(contactEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void displayContact(ContactEntity contactEntity) {
        contactDao.displayContact(contactEntity);
    }

    public boolean editContact(ContactEntity contactEntity) {
        boolean result = false;
        int contactId;
        try {
            if (contactDao.searchContactByName(contactEntity)) {
                contactId = contactDao.selectContact(contactEntity);
                contactEntity.setContactId(contactId);
                contactDao.editContact(contactEntity);
                result = true;
            } else result = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //delete contact of phonebook
    void removeContact(int phoneBookId) {
        int contactId;
        ContactEntity contactEntity = new ContactEntity();
        try {
            contactEntity.setPhoneBookId(phoneBookId);
            if (contactDao.searchByPhoneId(contactEntity)) {
                List<ContactEntity> contactEntityList = contactDao.selectbyPhoneBookId(contactEntity);
                for (ContactEntity conEntity : contactEntityList) {
                    contactId = conEntity.getContactId();
                    numberService.removeNumber(contactId);
                    contactEntity.setContactId(contactId);
                    contactDao.removeContact(contactEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //delete contact
    public boolean removeContact(ContactEntity contactEntity) {
        boolean result = false;
        int contactId;
        try {
            if (contactDao.searchContactByName(contactEntity)) {
                contactId = contactDao.selectContact(contactEntity);
                numberService.removeNumber(contactId);
                contactEntity.setContactId(contactId);
                contactDao.removeContact(contactEntity);
                result = true;
            } else result = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
