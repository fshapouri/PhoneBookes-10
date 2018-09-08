package view.ui;

import model.service.PhoneBookService;
import view.dto.ContactDto;
import view.dto.PhoneBookDto;
import java.util.List;
import java.util.Scanner;

public class PhoneBookUI {
    private Scanner input = new Scanner(System.in);
    private PhoneBookDto pbDto = new PhoneBookDto();
    private PhoneBookService pbService =new PhoneBookService();
    private ContactUI contactUI=new ContactUI();

    public void insertPhoneBook() {
        System.out.print("please Enter name of your phonebook:");
        String phoneBookName = input.next();
        pbDto.setPhonebookName(phoneBookName);
            if(!(pbService.searchPhoneBook(pbDto))){
                List <ContactDto> contactDtoList = contactUI.insertContact();
                pbDto.setcontactList(contactDtoList);
                pbService.insertPhoneBook(pbDto);
                System.out.println("add phoneBook successed");
            }else {
                System.out.println("this phonebook is available");
//                List <ContactDto> contactDtoList = contactUI.insertContact();
//                pbDto.setcontactList(contactDtoList);
//                pbService.insertPhoneBook(pbDto);
            }

//         pbService.insertPhoneBook(pbDto);
        }

    public void searchContact(){
//        pbDto.setPhonebookName(phoneBookName);
//        try {
//            pbService.searchPhoneBook(phoneBookName);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return phoneBookName;
////        contactUI.insertContact();
    }

}
