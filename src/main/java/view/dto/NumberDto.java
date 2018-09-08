package view.dto;

public class NumberDto {

    private String number;
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








}
