package facade.impl;

import model.entity.ContactEntity;
import model.service.ContactService;
import view.dto.ContactDto;

public class ContactFacade {
    private final static ContactFacade instance= new ContactFacade();
    private ContactService contactService= ContactService.getInstance();

    private ContactFacade(){
    }

    public static ContactFacade getInstance(){
        return instance;
    }

    public boolean searchContact(ContactDto contactDto){
        ContactEntity contactEntity=new ContactEntity();
        contactEntity.setFirstname(contactDto.getFirstname());
        contactEntity.setLastname(contactDto.getLastname());

        if(contactService.searchContact(contactEntity)){
            return true;
        }else return false;
    }

    public void displayContact(ContactDto contactDto){
        ContactEntity contactEntity= new ContactEntity();
        contactEntity.setFirstname(contactDto.getFirstname());
        contactEntity.setLastname(contactDto.getLastname());
        contactService.displayContact(contactEntity);
    }

    public boolean editContact(ContactDto contactDto){
        ContactEntity contactEntity= new ContactEntity();
        contactEntity.setFirstname(contactDto.getFirstname());
        contactEntity.setLastname(contactDto.getLastname());
        contactEntity.setNewfirstname(contactDto.getNewfirstname());
        contactEntity.setNewlastname(contactDto.getNewlastname());

        if (contactService.editContact(contactEntity)){
            return true;
        }else return false;
    }

    public boolean removeContact(ContactDto contactDto){
        ContactEntity contactEntity= new ContactEntity();
        contactEntity.setFirstname(contactDto.getFirstname());
        contactEntity.setLastname(contactDto.getLastname());

        if(contactService.removeContact(contactEntity)){
            return true;
        }else return false;

    }


}
