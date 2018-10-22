package model.service;

import dao.NumberDao;
import model.entity.NumberEntity;
import view.dto.NumberDto;

import java.util.List;

public class NumberService {
    private NumberDao numberDao = NumberDao.getInstance();

    boolean insertNumber(List<NumberDto> numberDtoList, int contactId) {
        NumberEntity numberEntity = new NumberEntity();
        boolean result = false;

        for (NumberDto numberDto : numberDtoList) {
            numberEntity.setNumber(numberDto.getNumber());
            numberEntity.setNumberType(numberDto.getNumberType());
            numberEntity.setContactId(contactId);
            if (!(numberDao.searchNumberById(numberEntity))) {
                numberDao.insertNumber(numberEntity);
                result = true;
            } else result = false;
        }
        return result;
    }

    public boolean searchNumber(NumberDto numberDto) {
        NumberEntity numberEntity = new NumberEntity();
        boolean result = false;
        try {
            numberEntity.setNumber(numberDto.getNumber());
            if (numberDao.searchNumberByNum(numberEntity)) result = true;
            else result = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean editNumber(NumberDto numberDto){
        NumberEntity numberEntity=new NumberEntity();
        boolean result=false;
        try {
            numberEntity.setNumber(numberDto.getNumber());
            if (numberDao.searchNumberByNum(numberEntity)){
                List<NumberEntity>numberEntities=numberDao.selectNumberlist(numberEntity);
                for (NumberEntity numberEntity1:numberEntities) {
                    numberEntity.setNumberId(numberEntity1.getNumberId());
                    numberEntity.setNumber(numberDto.getNewnumber());
                    numberEntity.setNumberType(numberDto.getNumberType());
                    numberDao.editNumber(numberEntity);
                }
                result=true;
        }   else result=false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public boolean editNumber(NumberDto numberDto){
//        NumberEntity numberEntity=new NumberEntity();
//        boolean result=false;
//        int numberId;
//        try {
//            numberEntity.setNumber(numberDto.getNumber());
//            if (numberDao.searchNumberByNum(numberEntity)){
//                numberId=numberDao.selectNumber(numberEntity);
//                numberEntity.setNumber(numberDto.getNewnumber());
//                numberEntity.setNumberType(numberDto.getNumberType());
//                numberEntity.setNumberId(numberId);
//                numberDao.editNumber(numberEntity);
//                result=true;
//            }   else result=false;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    boolean removeNumber(int contactId){
        NumberEntity numberEntity= new NumberEntity();
        boolean result=false;
        try{
            numberEntity.setContactId(contactId);
            if (numberDao.searchbyContactId(numberEntity)){
                List<NumberEntity> numberEntityList=numberDao.selectByContactId(numberEntity);
                for (NumberEntity numEntity:numberEntityList) {
                    numberEntity.setNumberId(numEntity.getNumberId());
                    numberDao.removeNumber(numberEntity);
                }
                result=true;
            }else result=false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

     public boolean removeNumber(NumberDto numberDto) {
        NumberEntity numberEntity = new NumberEntity();
        boolean result = false;
        try {
            numberEntity.setNumber(numberDto.getNumber());
            if (numberDao.searchNumberByNum(numberEntity)) {
                List<NumberEntity> numberEntities = numberDao.selectNumberlist(numberEntity);
                for (NumberEntity numEntity : numberEntities) {
                    numberEntity.setNumberId(numEntity.getNumberId());
                    numberDao.removeNumber(numberEntity);
                    result = true;
                }
            } else result = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
