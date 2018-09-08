package view.dto;

public enum  NumberType {

    HOME(1), MOBILE(2), WORK(3), FAX(4), OTHER(5);

    private int type;

    private NumberType (int t){
        type=t;
    }

    public int getType(){
        return type;
    }
}
