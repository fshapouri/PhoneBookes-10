package view.ui;

import model.service.NumberService;
import view.dto.NumberType;
import view.dto.NumberDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberUI {
    private Scanner input= new Scanner(System.in);
    private NumberService numberService=new NumberService();

    List<NumberDto> insertNumber(){
        List<NumberDto> numberList=new ArrayList<NumberDto>();
        NumberType numberType = null;
        System.out.println("How many number do you want add to list?");
        int count= input.nextInt();
        for (int i=0; i< count ; i++) {
            System.out.println("number" + (i+1) + ":");
            System.out.print("please enter your number:");
            String number = input.next();
            System.out.println("please Enter number type of your number: \n 1)HOME 2)MOBILE 3)WORK 4)FAX 5)OTHER");
            int type = input.nextInt();
            System.out.println("************************************");
            switch (type) {
                case 1:
                    numberType = NumberType.HOME;
                    break;
                case 2:
                    numberType = NumberType.MOBILE;
                    break;
                case 3:
                    numberType = NumberType.WORK;
                    break;
                case 4:
                    numberType = NumberType.FAX;
                    break;
                case 5:
                    numberType = NumberType.OTHER;
                    break;
                default:
                    System.out.println("please enter valid type!!");
            }
            NumberDto numberDto=new NumberDto();
            numberDto.setNumber(number);
            numberDto.setNumberType(numberType);
            numberList.add(numberDto);
        }
        return numberList;
    }

    void searchNumber(){
        NumberDto numberDto=new NumberDto();
        System.out.print("Enter your number please:");
        String number=input.next();
        numberDto.setNumber(number);
        if (numberService.searchNumber(numberDto))
            System.out.print("this number is available");
        else
            System.out.println("this number is not available");
    }

    void editNumber(){
        NumberType numType = null;
        NumberDto numberDto=new NumberDto();
        System.out.print("please enter number to edited:");
        String number=input.next();
        System.out.print("please enter new number:");
        String newnumber=input.next();
        System.out.println("please enter new type of your number: \n 1)HOME 2)MOBILE 3)WORK 4)FAX 5)OTHER");
        int newtype = input.nextInt();
        switch (newtype){
            case 1:
                numType=NumberType.HOME;
                break;
            case 2:
                numType=NumberType.MOBILE;
                break;
            case 3:
                numType=NumberType.WORK;
                break;
            case 4:
                numType=NumberType.FAX;
                break;
            case 5:
                numType=NumberType.OTHER;
                break;
                default:
                    System.out.print("please enter valid type");
        }
        numberDto.setNumber(number);
        numberDto.setNumberType(numType);
        numberDto.setNewnumber(newnumber);

        if(numberService.editNumber(numberDto)){
            System.out.println("Successful editing ");
        } else System.out.println("Editing failed: there is not this number.");
    }

    void removeNumber(){
        NumberDto numberDto=new NumberDto();
        System.out.print("please enter number to deleted:");
        String number=input.next();
        numberDto.setNumber(number);

        if (numberService.removeNumber(numberDto)){
            System.out.println("Successful deleting ");
        } else System.out.println("Editing failed: there is not this number.");
    }

}
