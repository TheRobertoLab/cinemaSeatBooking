package sample;

public class DiscountCode {
    private String code;
    private int percentage;

    public DiscountCode(String code, int percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    public String getCode() {
        return code;
    }
    public int getPercentage() {
        return percentage;
    }
}
