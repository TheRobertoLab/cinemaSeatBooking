package sample;

import javafx.scene.image.Image;

public class Victual extends MyThing{
    private String title;
    private String type;
    private double price;
    private String capacity;
    private Image image;

    public Victual(String title, String type, double price, String capacity, String path) {
        this.title = title;
        this.type = type;
        this.price = price;
        this.capacity = capacity;
        this.image = new Image(path);
    }
    public Victual(String title, String type, double price, String capacity) {
        this.title = title;
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }
    public Image getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Victual{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", capacity='" + capacity + '\'' +
                ", image=" + image +
                '}';
    }
}
