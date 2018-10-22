package facade;

import model.entity.ContactEntity;
import model.entity.PhoneBookEntity;
import model.service.PhoneBookService;
import view.dto.ContactDto;
import view.dto.PhoneBookDto;

import java.util.List;
//import view.ui.ContactUI;

public class PhoneBookFacade {
    private PhoneBookService pbService = new PhoneBookService();

    private final static PhoneBookFacade instance=new PhoneBookFacade();

    private PhoneBookFacade(){}
    public static PhoneBookFacade getInstance(){return instance;}

//    public boolean insertPhoneBook(PhoneBookDto phoneBookDto) {
//        PhoneBookEntity pbEntity = new PhoneBookEntity();
//        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
//        for (PhoneBookDto pbDto:phoneBookDto.getcontactList()) {
//            pbEntity.setContactList(phoneBookDto.getcontactList());
//
//        }
//        pbEntity.setContactList(phoneBookDto.getcontactList());
//        pbService.insertPhoneBook(pbEntity);
//
//
//
//    }

}
