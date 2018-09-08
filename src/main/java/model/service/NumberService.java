package model.service;

import dao.NumberDao;
import model.entity.NumberEntity;
import view.dto.NumberDto;

import java.util.List;

public class NumberService {

    NumberDao numberDao=new NumberDao();

    public boolean insertNumber(List<NumberDto> numberDtoList,int contactid){
        boolean result=false;
        NumberEntity numberEntity=new NumberEntity();
        for (NumberDto numberDto:numberDtoList) {
            numberEntity.setNumber(numberDto.getNumber());
            numberEntity.setNumberType(numberDto.getNumberType());
            numberEntity.setContactId(contactid);
        }


        numberDao.insertNumber(numberEntity);


        return result;
    }
}
