package model.service;

import dao.NumberDao;
import model.entity.ContactEntity;
import model.entity.NumberEntity;
import view.dto.NumberDto;

import java.util.List;

public class NumberService {
    private NumberDao numberDao = NumberDao.getInstance();
    private final static NumberService instance= new NumberService();

    private NumberService(){
    }

    public static NumberService getInstance(){
        return instance;
    }

    void insertNumber(ContactEntity contactEntity) {

        for (NumberEntity numberEntity : contactEntity.getNumberList()) {
            numberEntity.setContactId(contactEntity.getContactId());
            if (!(numberDao.searchNumberById(numberEntity)))
                numberDao.insertNumber(numberEntity);
        }
    }

    public boolean searchNumber(NumberEntity numberEntity) {
        boolean result = false;
        try {
            result = numberDao.searchNumberByNum(numberEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean editNumber(NumberEntity numberEntity){
        boolean result=false;
        try {
            if (numberDao.searchNumberByNum(numberEntity)){
                List<NumberEntity>numberEntitiesId=numberDao.selectNumberlist(numberEntity);
                for (NumberEntity numberEntity1:numberEntitiesId) {
                    numberEntity.setNumberId(numberEntity1.getNumberId());
                    numberDao.editNumber(numberEntity);
                }
                result=true;
        }   else result=false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //delete number of contact
    void removeNumber(int contactId){
        NumberEntity numberEntity= new NumberEntity();
        try{
            numberEntity.setContactId(contactId);
            if (numberDao.searchbyContactId(numberEntity)){
                List<NumberEntity> numberEntityList=numberDao.selectByContactId(numberEntity);
                for (NumberEntity numEntity:numberEntityList) {
                    numberEntity.setNumberId(numEntity.getNumberId());
                    numberDao.removeNumber(numberEntity);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//delete number
     public boolean removeNumber(NumberEntity numberEntity) {
        boolean result = false;
        try {
            if (numberDao.searchNumberByNum(numberEntity)) {
                List<NumberEntity> numberEntitiesId = numberDao.selectNumberlist(numberEntity);
                for (NumberEntity numEntity : numberEntitiesId) {
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
