package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomFilmListCell extends ListCell<Film> {
    public FXMLLoader cell;
    public AnchorPane anchorPane;
    public ImageView filmImageView;
    public Label ageRestLabel;
    public Label seatsAvailableLabel;
    public Label dayWeekLabel;
    public Label startTimeLabel;
    public Label priceLabel;
    public Label nameTheatreLabel;
    public Label filmTittleLabel;
    public Label releasedYearLabel;

    public SearchEngine searchEngine;
    public Theatre searchedTheatre;
    public CustomFilmListCell() {
        searchEngine = new SearchEngine();
    }

    @Override
    public void updateItem(Film film, boolean empty){
        //update each list view cell by film details
        super.updateItem(film, empty);
        if(empty || film == null){
            setText(null);
            setGraphic(null);
        }else{
            if(cell == null){
                cell = new FXMLLoader(getClass().getResource("CustomFilmListCell.fxml"));
                cell.setController(this);

                try {
                    cell.load();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            filmTittleLabel.setText(film.getTitle());
            filmImageView.setImage(film.getImage());
            ageRestLabel.setText(""+film.getAgeRestrictions());
            dayWeekLabel.setText("Saturday, Week "+film.getWeekNumber());
            startTimeLabel.setText(film.getStartTime());

            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
            //This if needed, because there are films with age restrictions grater than 10, without this we would always have
            //first price - 0.00 and it is not good
            if(film.getAgeRestrictions() > 10){
                priceLabel.setText(format.format(film.getStudentTicketPrice()) + " - " + format.format(film.getTicketForTwoPrice()));
            }else {
                priceLabel.setText(format.format(film.getChildTicketPrice()) + " - " + format.format(film.getTicketForTwoPrice()));
            }

            nameTheatreLabel.setText(film.getTheatreTitle());
            releasedYearLabel.setText("" + film.getReleaseYear());

            //Search theatre for each of film and update available seats label
            searchedTheatre = searchEngine.searchTheatre(film.getTheatreTitle(), film.getWeekNumber());
            seatsAvailableLabel.setText("" + searchedTheatre.getAvailableSeats());

            setText(null);
            setGraphic(anchorPane);
        }
    }
}
