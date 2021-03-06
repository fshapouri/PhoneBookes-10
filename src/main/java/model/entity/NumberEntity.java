package model.entity;
import view.dto.NumberType;
public class NumberEntity {

    private String number;
    private NumberType numberType;
    private int contactId;
    private int numberId;
    private String newnumber;

    public NumberEntity(String number, NumberType numberType, int contactId) {
        this.number = number;
        this.numberType = numberType;
        this.contactId=contactId;
    }
    public NumberEntity(String number, NumberType numberType) {
        this.number = number;
        this.numberType = numberType;
    }

    public NumberEntity() {}

    public int getContactId() {return contactId;}

    public void setContactId(int contactId) {this.contactId = contactId;}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public NumberType getNumberType() {
        return numberType;
    }

    public void setNumberType(NumberType numberType) {
        this.numberType = numberType;
    }

    public int getNumberId() {
        return numberId;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }
    public String getNewnumber() {
        return newnumber;
    }

    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber;
    }



}
