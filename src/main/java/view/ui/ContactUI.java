package view.ui;

import model.service.ContactService;
import view.dto.ContactDto;
import view.dto.NumberDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ContactUI {
    private NumberUI numberUI = new NumberUI();
    private ContactService contactService=new ContactService();
    private Scanner input = new Scanner(System.in);

    List<ContactDto> insertContact() {
        List<ContactDto> contactList = new ArrayList<ContactDto>();
        System.out.print("How many contact do you want add to list?");
        int count = input.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("contact" + (i + 1) + ":");
            System.out.print("Enter FirstName please:");
            String firstname = input.next();
            System.out.print("Enter LastName please:");
            String lastname = input.next();

            ContactDto contactDto = new ContactDto();
            contactDto.setFirstname(firstname);
            contactDto.setLastname(lastname);
            List<NumberDto> numberDtoList = numberUI.insertNumber();
            contactDto.setNumberList(numberDtoList);
            contactList.add(contactDto);
        }
        return contactList;
    }

    void searchContact(){
        ContactDto contactDto = new ContactDto();
        System.out.print("Enter FirstName please:");
        String firstname = input.next();
        System.out.print("Enter LastName please:");
        String lastname = input.next();

        contactDto.setFirstname(firstname);
        contactDto.setLastname(lastname);
        if(contactService.searchContact(contactDto)){
            System.out.println("this contact is available");
        }else
            System.out.println("this contact is not available");

    }

    void displayContact() {
        ContactDto contactDto = new ContactDto();
        System.out.print("Enter firstname please:");
        String firstname = input.next();
        System.out.print("Enter lastname please:");
        String lastname = input.next();

        contactDto.setFirstname(firstname);
        contactDto.setLastname(lastname);
        contactService.displayContact(contactDto);
    }

    void editContact(){
        ContactDto contactDto=new  ContactDto();
        System.out.print("please enter first name to edit:");
        String firstname=input.next();
        System.out.print("please enter last name to edit:");
        String lastname = input.next();
        System.out.print("please enter new first name:");
        String newfirstname=input.next();
        System.out.print("please enter new last name:");
        String newlastname=input.next();

        contactDto.setFirstname(firstname);
        contactDto.setLastname(lastname);
        contactDto.setNewfirstname(newfirstname);
        contactDto.setNewlastname(newlastname);

     if (contactService.editContact(contactDto)){
         System.out.println("Successful editing ");
     } else System.out.println("Editing failed: there is not this contact.");
    }

    void removeContact() {
        ContactDto contactDto = new ContactDto();
        System.out.print("Please enter your first name for deleted:");
        String firstName = input.next();
        System.out.print("please enter your last name for deleted:");
        String lastName = input.next();

        contactDto.setFirstname(firstName);
        contactDto.setLastname(lastName);
        if (contactService.removeContact(contactDto)) {
            System.out.println("Successful deleting");
        } else System.out.println("Deleting failed , there is not this contact");
    }


}


