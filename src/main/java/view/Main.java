package view;

import view.ui.ContactUI;
import view.ui.NumberUI;
import view.ui.PhoneBookUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        PhoneBookUI phoneBookUI=new PhoneBookUI();
        Scanner input= new Scanner(System.in);
        boolean exit=true;
        while (exit){
            System.out.println("please Enter a number: " +
            "1. Add \t" +
            "2. Seatch \t" +
            "3. edit \t "+
            "4. delete \t "+
            "5. Report \t" +
            "6. exit");
            int num=input.nextInt();
            switch (num){
                case 1:
                    phoneBookUI.insertPhoneBook();
                    break;
//                case 2:
//                    phoneBookUI.searchPhoneBook();
//                    break;
//                case 3:
//                    phoneBookUI.reaport();
//                    break;
                case 6:
                    exit=false;
            }
        }


    }
}
