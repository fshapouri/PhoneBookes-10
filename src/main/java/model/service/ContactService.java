package model.service;
import dao.ContactDao;
import model.entity.ContactEntity;
import view.dto.ContactDto;
import java.util.List;

public class ContactService {
    private ContactDao contactDao=new ContactDao();
    private NumberService numberService=new NumberService();
    private ContactEntity contactEntity=new ContactEntity();

    public boolean insertContact(List<ContactDto> contactDtos, int phoneBookId) {
        int contactId=0;
        boolean result = false;
        for (ContactDto contactDto : contactDtos) {
            contactEntity.setFirstname(contactDto.getFirstname());
            contactEntity.setLastname(contactDto.getLastname());
            contactEntity.setPhoneBookId(phoneBookId);
            if (!(contactDao.searchContact(contactEntity))) {
                contactDao.insertContact(contactEntity);
                contactId=contactDao.selectContact(contactEntity);
            }else
                contactId=contactDao.selectContact(contactEntity);
        }
//        numberService.insertNumber()
        return result;
    }
    public boolean searchContact(ContactDto contactDto, int phoneBookId){
        boolean result=false;
        contactEntity.setFirstname(contactDto.getFirstname());
        contactEntity.setLastname(contactDto.getLastname());
//        try {
//            if ()
//        }catch (){
//
//        }
      return  result;
    }

}
