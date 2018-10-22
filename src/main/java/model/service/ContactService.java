package model.service;

import dao.ContactDao;
import model.entity.ContactEntity;
import view.dto.ContactDto;

import java.util.List;

public class ContactService {
   private ContactDao contactDao = ContactDao.getInstance();
   private NumberService numberService = new NumberService();

    boolean insertContact(List<ContactDto> contactDtos, int phoneBookId) {
        ContactEntity contactEntity = new ContactEntity();
        int contactId;
        boolean result = false;
        try {
            for (ContactDto contactdto : contactDtos) {
                contactEntity.setFirstname(contactdto.getFirstname());
                contactEntity.setLastname(contactdto.getLastname());
                contactEntity.setPhoneBookId(phoneBookId);
                if (!(contactDao.searchContactById(contactEntity))) {
                    contactDao.insertContact(contactEntity);
                    contactId = contactDao.selectContact(contactEntity);
                    numberService.insertNumber(contactdto.getNumberList(), contactId);
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


    public boolean searchContact(ContactDto contactDto) {
        ContactEntity contactEntity = new ContactEntity();
        boolean result = false;
        try {
            contactEntity.setFirstname(contactDto.getFirstname());
            contactEntity.setLastname(contactDto.getLastname());
            if (contactDao.searchContactByName(contactEntity))
                result=true;
            else result=false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void displayContact(ContactDto contactDto) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstname(contactDto.getFirstname());
        contactEntity.setLastname(contactDto.getLastname());
        contactDao.displayContact(contactEntity);
    }

    public boolean editContact(ContactDto contactDto){
        boolean result=false;
        int contactId;
        ContactEntity contactEntity=new ContactEntity();
        try {
            contactEntity.setFirstname(contactDto.getFirstname());
            contactEntity.setLastname(contactDto.getLastname());
            if(contactDao.searchContactByName(contactEntity)){
                contactId=contactDao.selectContact(contactEntity);
                contactEntity.setFirstname(contactDto.getNewfirstname());
                contactEntity.setLastname(contactDto.getNewlastname());
                contactEntity.setContactId(contactId);
                contactDao.editContact(contactEntity);
                result=true;
            }else result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    void removeContact(int phoneBookId){
        int contactId;
        ContactEntity contactEntity=new ContactEntity();
        try{
            contactEntity.setPhoneBookId(phoneBookId);
            if (contactDao.searchByPhoneId(contactEntity)){
              List<ContactEntity> contactEntityList=contactDao.selectbyPhoneBookId(contactEntity);
                for (ContactEntity conEntity:contactEntityList) {
                    contactId=conEntity.getContactId();
                    numberService.removeNumber(contactId);
                    contactEntity.setContactId(contactId);
                    contactDao.removeContact(contactEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean removeContact(ContactDto contactDto){
        boolean result=false;
        int contactId;
        ContactEntity contactEntity=new ContactEntity();
        try{
            contactEntity.setFirstname(contactDto.getFirstname());
            contactEntity.setLastname(contactDto.getLastname());

            if (contactDao.searchContactByName(contactEntity)){
                contactId=contactDao.selectContact(contactEntity);
                numberService.removeNumber(contactId);
                contactEntity.setContactId(contactId);
                contactDao.removeContact(contactEntity);
                result=true;
            }else result=false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
