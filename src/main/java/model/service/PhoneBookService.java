package model.service;

import dao.PhoneBookDao;
import model.entity.PhoneBookEntity;
import view.dto.PhoneBookDto;

public class PhoneBookService{
    private PhoneBookDao pbDao=new PhoneBookDao();
    private PhoneBookEntity pbEntity=new PhoneBookEntity();
    private ContactService contactService=new ContactService();

    public boolean insertPhoneBook(PhoneBookDto phoneBookDto) {
        int phoneBookId = 0;
        boolean result = false;
        pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
        try {
            if (!(pbDao.searchPhoneBook(pbEntity))) {
                pbDao.insertPhoneBook(pbEntity);
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
                result = true;
            }else {
                phoneBookId = pbDao.selectPhoneBook(pbEntity);
            }

            contactService.insertContact(phoneBookDto.getcontactList(),phoneBookId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean searchPhoneBook(PhoneBookDto phoneBookDto) {
        boolean result=false;
        try {
            pbEntity.setPhonebookName(phoneBookDto.getPhonebookName());
            if(pbDao.searchPhoneBook(pbEntity)){
              result=true;
            }else result=false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }



}
