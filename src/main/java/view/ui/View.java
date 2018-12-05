package view.ui;
import model.reports.*;
import java.util.Scanner;

public class View {
     private final static View instance= new View();
     private Scanner input=new Scanner(System.in);
     private PhoneBookUI phoneBookUI=new PhoneBookUI();
     private ContactUI contactUI=new ContactUI();
     private NumberUI numberUI=new NumberUI();


     private View(){
     }

    public static View getInstance(){
         return instance;
    }

    public void search(){
        System.out.println("Which one do you want to search?" +"1. searchPhoneBook\t" + "2. searchContact\t" + "3. searchNumber");
        int request=input.nextInt();
        switch (request){
            case 1:
                phoneBookUI.searchPhoneBook();
                break;
            case 2:
                contactUI.searchContact();
                break;
            case 3:
                numberUI.searchNumber();
                break;
            default:
                System.out.println("your request is invalid...try again please");

        }
        System.out.println("********************************************************");
    }

    public void display(){
        System.out.println("Which one do you want to display?\t" + "1. displayPhoneBook\t" + "2. displayContact\t");
        int request=input.nextInt();
        switch (request){
            case 1:
                phoneBookUI.displayPhoneBook();
                break;
            case 2:
                contactUI.displayContact();
                break;
            default:
                System.out.println("your request is invalid...try again please");
        }
        System.out.println("********************************************************");
    }

    public void edit(){
        System.out.println("which one do you want edit?\t" + "1.editPhonebook\t" + "2.editContact\t" + "3.editNumber");
        int request=input.nextInt();
        switch (request){
            case 1:
                phoneBookUI.editPhoneBook();
                break;
            case 2:
                contactUI.editContact();
                break;
            case 3:
                numberUI.editNumber();
                break;
                default:
                    System.out.println("your request is invalid...try again please");
        }
        System.out.println("********************************************************");
    }

    public void remove(){
        System.out.println(" which one do you want to deleted: " + "1.removePhoneBook\t" + "2.removeContact\t" + "3.removeNumber");
        int request=input.nextInt();
        switch (request){
            case 1:
                phoneBookUI.removePhoneBook();
                break;
            case 2:
                contactUI.removeContact();
                break;
            case 3:
                numberUI.removeNumber();
                break;
                default:
                    System.out.println("your request is invalid...try again please");
        }
        System.out.println("********************************************************");
    }

    public void reports(){
        System.out.println("which one do you want to reported?\t" + "1.PhonebookReport\t" + "2.ContactReport\t" + "3.NumberReport");
        int reqest=input.nextInt();
        switch (reqest){
            case 1:
                ;
                break;
            case 2:
//                contactUI.report();
                break;
            case 3:
//                numberUI.report();
                break;
                default:
                    System.out.println("your request is invalid...try again please");
        }
        System.out.println("********************************************************");
    }
}
