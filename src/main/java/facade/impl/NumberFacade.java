package facade.impl;

import model.entity.NumberEntity;
import model.service.NumberService;
import view.dto.NumberDto;

public class NumberFacade {
    private final static NumberFacade instance= new NumberFacade();
    private NumberService numberService=NumberService.getInstance();

    private NumberFacade(){
    }

    public static NumberFacade getInstance(){
        return instance;
    }

    public boolean searchNumber(NumberDto numberDto){
        NumberEntity numberEntity=new NumberEntity();
        numberEntity.setNumber(numberDto.getNumber());
         if(numberService.searchNumber(numberEntity)){
             return true;
         }else return false;
    }

    public boolean editNumber(NumberDto numberDto){
        NumberEntity numberEntity =new NumberEntity();
        numberEntity.setNumber(numberDto.getNumber());
        numberEntity.setNumberType(numberDto.getNumberType());
        numberEntity.setNewnumber(numberDto.getNewnumber());

        if (numberService.editNumber(numberEntity)){
            return true;
        }else return false;
    }

    public boolean removeNumber(NumberDto numberDto){
        NumberEntity numberEntity= new NumberEntity();
        numberEntity.setNewnumber(numberDto.getNumber());
        if (numberService.removeNumber(numberEntity)){
            return true;
        }else return false;
    }
}
