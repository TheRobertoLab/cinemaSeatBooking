package sample;

public class Seat{
    private String theatreTitle;
    private String type;
    private double price;
    private String id;
    private int weekNumber;
    private boolean isTaken;
    private boolean isSelected;

    public Seat(String theatreTitle, String type, double price, String id, boolean isTaken) {
        this.theatreTitle = theatreTitle;
        this.type = type;
        this.price = price;
        this.id = id;
        this.isTaken = isTaken;
    }
    public Seat(String theatreTitle, String type, double price, boolean isTaken) {
        this.theatreTitle = theatreTitle;
        this.type = type;
        this.price = price;
        this.id = "0";
        this.isTaken = isTaken;
    }
    public Seat(String type, double price, String id, boolean isSelected, boolean isTaken) {
        this.type = type;
        this.price = price;
        this.id = id;
        this.isTaken = isTaken;
        this.isSelected = isSelected;
    }

    public Seat(double price, String number) {
        this.price = price;
        this.id = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatreTitle() {
        return theatreTitle;
    }

    public void setTheatreTitle(String theatreTitle) {
        this.theatreTitle = theatreTitle;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Seat{" +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", id='" + id + '\'' +
                ", weekNumber=" + weekNumber +
                ", isTaken=" + isTaken +
                ", isSelected" + isSelected+'}';
    }
}
