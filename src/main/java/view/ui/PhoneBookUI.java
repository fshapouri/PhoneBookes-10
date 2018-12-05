package view.ui;

import facade.impl.PhoneBookFacade;
import view.dto.ContactDto;
import view.dto.PhoneBookDto;

import java.util.List;
import java.util.Scanner;

public class PhoneBookUI {
    private Scanner input = new Scanner(System.in);
    private ContactUI contactUI = new ContactUI();
    private PhoneBookFacade pbFacade = PhoneBookFacade.getInstance();

    public void insertPhoneBook() {
        System.out.print("Please Enter name of your phonebook:");
        String phoneBookName = input.next();

        PhoneBookDto pbDto = new PhoneBookDto();
        pbDto.setPhonebookName(phoneBookName);
        List<ContactDto> contactDtoList = contactUI.insertContact();
        pbDto.setcontactList(contactDtoList);

        if (pbFacade.insertPhoneBook(pbDto)) {
            System.out.println("Add phoneBook successed.");
        } else {
            System.out.println("Try again please.");
        }
    }

    void searchPhoneBook() {
        PhoneBookDto pbDto = new PhoneBookDto();
        System.out.print("Please Enter name of your phonebook:");
        String phoneBookName = input.next();
        pbDto.setPhonebookName(phoneBookName);
        if (pbFacade.searchPhoneBook(pbDto)) {
            System.out.println("This phoneBook is available");
            System.out.println("**************************************");
        } else {
            System.out.println("This phoneBook is not available");
            System.out.println("**************************************");
        }
    }

    void displayPhoneBook() {
        PhoneBookDto pbDto = new PhoneBookDto();
        System.out.print("Please Enter name of your phonebook:");
        String phoneBookName = input.next();
        pbDto.setPhonebookName(phoneBookName);
        pbFacade.displayPhoneBook(pbDto);

    }

    void editPhoneBook() {
        PhoneBookDto pbDto = new PhoneBookDto();
        System.out.print("Please Enter name of your phonebook to edited:");
        String phoneBookName = input.next();
        System.out.print("Please Enter new name for your phonebook:");
        String newPhoneBookName = input.next();
        pbDto.setPhonebookName(phoneBookName);
        pbDto.setNewPhoneBookName(newPhoneBookName);

        if (pbFacade.editPhoneBook(pbDto)) {
            System.out.println("Successful editing ");
        } else System.out.println("Editing failed: there is not this phonebook.");
    }

    void removePhoneBook() {
        PhoneBookDto pbDto = new PhoneBookDto();
        System.out.print("Please enter name of your phonebook to deleted:");
        String phoneBookName = input.next();
        pbDto.setPhonebookName(phoneBookName);

        if (pbFacade.removePhoneBook(pbDto)) {
            System.out.println("Successful deleting");
        } else System.out.println("Deleting failed , there is not this phonebook");
    }

//    void report(){
//PhoneBookReport ph=new PhoneBookReport();
//
//    }
}
