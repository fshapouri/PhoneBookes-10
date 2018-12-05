package facade.impl;

import model.entity.ContactEntity;
import model.entity.NumberEntity;
import model.entity.PhoneBookEntity;
import model.service.PhoneBookService;
import view.dto.ContactDto;
import view.dto.NumberDto;
import view.dto.PhoneBookDto;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookFacade {
    private PhoneBookService pbService = PhoneBookService.getInstance();
    private final static PhoneBookFacade instance = new PhoneBookFacade();

    private PhoneBookFacade() {
    }

    public static PhoneBookFacade getInstance() {
        return instance;
    }

    public boolean insertPhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());

        List<ContactEntity> contacts = new ArrayList<ContactEntity>();
        for (ContactDto contactDto : phoneBookDto.getcontactList()) {
            List<NumberEntity> numbers = new ArrayList<NumberEntity>();
            for (NumberDto numberDto : contactDto.getNumberList()) {
                numbers.add(new NumberEntity(numberDto.getNumber(), numberDto.getNumberType()));
            }
            contacts.add(new ContactEntity(contactDto.getFirstname(), contactDto.getLastname(), numbers));
        }
        pbEntity.setContactList(contacts);

        return pbService.insertPhoneBook(pbEntity);
    }

    public boolean searchPhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
        return pbService.searchPhoneBook(pbEntity);
    }


    public void displayPhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
        pbService.displayPhoneBook(pbEntity);
    }

    public boolean editPhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
        pbEntity.setNewPhoneBookName(phoneBookDto.getNewPhoneBookName());

        return pbService.editPhoneBook(pbEntity);
    }

    public boolean removePhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());

        return pbService.removePhoneBook(pbEntity);
    }


}
