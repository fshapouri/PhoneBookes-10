package view.ui;

import model.service.ContactService;
import view.dto.ContactDto;
import view.dto.NumberDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactUI {

    private Scanner input= new Scanner(System.in);
    private ContactDto contactDto=new ContactDto();
    ContactService contactService=new ContactService();
    private NumberUI numberUI=new NumberUI();

    List<ContactDto> insertContact() {
      List<ContactDto> contactList = new ArrayList<ContactDto>();
        System.out.print("How many contact do you want add to list?");
        int count = input.nextInt();
        for (int i = 0; i<count; i++) {
            System.out.print("contact" + (i+1) + ":\t");
            System.out.print("Enter FirstName please:\t");
            String firstname = input.next();
            System.out.print("Enter LastName please:");
            String lastname = input.next();
            contactDto.setFirstname(firstname);
            contactDto.setLastname(lastname);
            List<NumberDto> numberDtoList = numberUI.insertNumber();
            contactDto.setNumberList(numberDtoList);
//            for (NumberDto numberDto : numberDtoList) {
//                contactDto.setNumberList();
//                contactList.add(contactDto);
//            }
            contactList.add(contactDto);
//            contactService.insertContact(contactDto,);
//            numberUI.insertNumber();
        }
            return contactList;
        }
    }

