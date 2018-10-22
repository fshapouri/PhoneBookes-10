package model.service;

import dao.PhoneBookDao;
import model.entity.PhoneBookEntity;
import view.dto.PhoneBookDto;

public class PhoneBookService {
    private PhoneBookDao pbDao = PhoneBookDao.getInstance();
    private ContactService contactService = new ContactService();

    public boolean insertPhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        int phoneBookId;
        boolean result;
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
        try {
            if (!(pbDao.searchPhoneBook(pbEntity))) {
                pbDao.insertPhoneBook(pbEntity);
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
            } else {
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
            }
            contactService.insertContact(phoneBookDto.getcontactList(), phoneBookId);
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean searchPhoneBook(PhoneBookDto phoneBookDto) {
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        boolean result = false;
        try {
            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
            if (pbDao.searchPhoneBook(pbEntity)) {
                result = true;
            } else result = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean searchPhoneBookgetId(PhoneBookDto phoneBookDto) {
        int phoneBookId;
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        boolean result = false;
        try {
            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
            if (pbDao.searchPhoneBook(pbEntity)) {
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
//                editPhoneBook(phoneBookId)
                result = true;

            } else result = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void displayPhoneBook(PhoneBookDto phoneBookDto){
        PhoneBookEntity pbEntity=new PhoneBookEntity();
        try {
            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
            pbDao.displayPhoneBook(pbEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean editPhoneBook(PhoneBookDto phoneBookDto){
        boolean result=false;
        int phoneBookId;
        PhoneBookEntity pbEntity=new PhoneBookEntity();
        try {
            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
            if(pbDao.searchPhoneBook(pbEntity)) {
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
                pbEntity.setPhonebookName(phoneBookDto.getNewPhoneBookName());
                pbEntity.setPhoneBookId(phoneBookId);
                pbDao.editPhoneBook(pbEntity);
                result = true;
            }else
                result=false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean removePhoneBook(PhoneBookDto phoneBookDto) {
        boolean resulte = false;
        int phonebookId;
        PhoneBookEntity pbEntity = new PhoneBookEntity();
        try {
            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
            if (pbDao.searchPhoneBook(pbEntity)) {
                phonebookId = pbDao.selectPhoneBook(pbEntity);
                contactService.removeContact(phonebookId);
                pbEntity.setPhoneBookId(phonebookId);
                pbDao.removePhoneBook(pbEntity);
                resulte = true;
            } else resulte = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resulte;
    }


}
