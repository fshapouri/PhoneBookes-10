package main;

import view.ui.PhoneBookUI;
import view.ui.View;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        View view=View.getInstance();
        PhoneBookUI phoneBookUI = new PhoneBookUI();
        Scanner input = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            System.out.println("please Enter a number: " + "1.Add\t" + "2.Search\t" + "3.Display\t" + "4.Edit\t" + "5.Delete\t" + "6.ReportTemplate\t" + "7.Exit");
            int num = input.nextInt();
            switch (num) {
                case 1:
                    phoneBookUI.insertPhoneBook();
                    break;
                case 2:
                    view.search();
                    break;
                case 3:
                    view.display();
                    break;
                case 4:
                    view.edit();
                    break;
                case 5:
                    view.remove();
                    break;
                case 6:
                    view.reports();
                    break;
                case 7:
                    exit = false;
            }
        }
    }
}
