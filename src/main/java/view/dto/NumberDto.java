package view.dto;

public class NumberDto {

    private String number;
    private String newnumber;
    private NumberType numberType;

    public NumberDto() {}

    public NumberDto(String number, NumberType numberType) {
        this.number = number;
        this.numberType = numberType;
    }

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
    public String getNewnumber() {
        return newnumber;
    }

    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber;
    }




}
