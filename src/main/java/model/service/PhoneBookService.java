package model.service;

import dao.PhoneBookDao;
import model.entity.PhoneBookEntity;
import view.dto.PhoneBookDto;

public class PhoneBookService {
    private final static PhoneBookService instance = new PhoneBookService();
    private PhoneBookDao pbDao = PhoneBookDao.getInstance();
    private ContactService contactService = ContactService.getInstance();

    private PhoneBookService() {
    }

    public static PhoneBookService getInstance() {
        return instance;
    }

    public boolean insertPhoneBook(PhoneBookEntity pbEntity) {
        int phoneBookId;
        boolean result;
        try {
            if (!(pbDao.searchPhoneBook(pbEntity))) {
                pbDao.insertPhoneBook(pbEntity);
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
            } else {
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
            }
            pbEntity.setPhoneBookId(phoneBookId);
            contactService.insertContact(pbEntity);
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean searchPhoneBook(PhoneBookEntity phoneBookEntity) {
        boolean result = false;
        try {
            if (pbDao.searchPhoneBook(phoneBookEntity)) {
                result = true;
            } else result = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public boolean searchPhoneBookgetId(PhoneBookDto phoneBookDto) {
//        int phoneBookId;
//        PhoneBookEntity pbEntity = new PhoneBookEntity();
//        boolean result = false;
//        try {
//            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
//            if (pbDao.searchPhoneBook(pbEntity)) {
//                phoneBookId = pbDao.selectPhoneBook(pbEntity);
////                editPhoneBook(phoneBookId)
//                result = true;
//
//            } else result = false;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public void displayPhoneBook(PhoneBookEntity phoneBookEntity) {
        try {
            pbDao.displayPhoneBook(phoneBookEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean editPhoneBook(PhoneBookEntity phoneBookEntity) {
        boolean result = false;
        int phoneBookId;
        try {
            if (pbDao.searchPhoneBook(phoneBookEntity)) {
                phoneBookId = pbDao.selectPhoneBook(phoneBookEntity);
                phoneBookEntity.setPhoneBookId(phoneBookId);
                pbDao.editPhoneBook(phoneBookEntity);
                result = true;
            } else result = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean removePhoneBook(PhoneBookEntity phoneBookEntity) {
        boolean resulte = false;
        int phonebookId;
        try {
            if (pbDao.searchPhoneBook(phoneBookEntity)) {
                phonebookId = pbDao.selectPhoneBook(phoneBookEntity);
                contactService.removeContact(phonebookId);
                phoneBookEntity.setPhoneBookId(phonebookId);
                pbDao.removePhoneBook(phoneBookEntity);
                resulte = true;
            } else resulte = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resulte;
    }


}
