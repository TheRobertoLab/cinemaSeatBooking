package sample;

import java.util.ArrayList;

public class DiscountCodes {
    private ArrayList<DiscountCode> discountCodes;
    public DiscountCodes(){
        discountCodes = new ArrayList<>();
        createDiscountCode("HelloWorld", 25);
        createDiscountCode("university325", 50);
    }
    private void createDiscountCode(String code, int percentage){
        DiscountCode c = new DiscountCode(code, percentage);
        discountCodes.add(c);
    }

    public ArrayList<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public DiscountCode findDiscountCode(String string){
        DiscountCode temp = null;
        for(DiscountCode dc : discountCodes){
            if(dc.getCode().equals(string)){
                temp = dc;
                break;
            }
        }
        if(temp != null){
            return temp;
        }else{
            return null;
        }
    }
}
