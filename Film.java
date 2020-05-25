package sample;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Film implements Serializable {
    private String title;
    private String theatreTitle;
    private String startTime;
    private int releaseYear;
    private double normalTicketPrice;
    private double studentTicketPrice;
    private double childTicketPrice;
    private double ticketForTwoPrice;
    private int ageRestrictions;
    private int weekNumber;
    private Image image;
    private String path;

    public Film(String title, String theatreTitle, String startTime, int releaseYear, double studentTicketPrice,
                double childTicketPrice, double normalTicketPrice, double ticketForTwoPrice, int ageRestrictions, int weekNumber, String path) {
        this.title = title;
        this.theatreTitle = theatreTitle;
        this.startTime = startTime;
        this.releaseYear = releaseYear;
        this.normalTicketPrice = normalTicketPrice;
        this.childTicketPrice = childTicketPrice;
        this.studentTicketPrice = studentTicketPrice;
        this.ticketForTwoPrice = ticketForTwoPrice;
        this.ageRestrictions = ageRestrictions;
        this.weekNumber = weekNumber;
        this.image = new Image(path);
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

    public String getStartTime() {
        return startTime;
    }


    public int getReleaseYear() {
        return releaseYear;
    }

    public int getAgeRestrictions() {
        return ageRestrictions;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public double getNormalTicketPrice() {
        return normalTicketPrice;
    }


    public double getStudentTicketPrice() {
        return studentTicketPrice;
    }

    public double getChildTicketPrice() {
        return childTicketPrice;
    }


    public double getTicketForTwoPrice() {
        return ticketForTwoPrice;
    }

    public String getTheatreTitle() {
        return theatreTitle;
    }


    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", startTime='" + startTime + '\'' +
                ", releaseYear=" + releaseYear +
                ", normalTicketPrice=" + normalTicketPrice +
                ", studentTicketPrice=" + studentTicketPrice +
                ", childTicketPrice=" + childTicketPrice +
                ", ticketForTwoPrice=" + ticketForTwoPrice +
                ", ageRestrictions=" + ageRestrictions +
                ", weekNumber=" + weekNumber +
                '}';
    }
}
