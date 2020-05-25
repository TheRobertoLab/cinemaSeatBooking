package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

import java.util.Iterator;

public class Films extends ObservableListWrapper<Film> {
    private SearchEngine searchEngine;
    public Films(){
        super(FXCollections.observableArrayList());

        searchEngine = new SearchEngine();

        //==========Films for Week 1===========
        //Child ticket available for those who are 0-10 years old
        //So if age restrictions greater I just set ticket price to 0, because we do not need a child ticket price.

        super.add(new Film("Bohemian Rhapsody","Sparck Jones", "18PM", 2019, 6.50,
                0, 7.00, 10.00, 12,1, "sample/Assets/Bohemian.jpg"));
        super.add(new Film("Alita", "Oastler Building", "20PM",  2019, 5.50,
                0, 7.00, 9.00, 12, 1, "sample/Assets/Alita.jpg"));
        super.add(new Film("Aquaman",  "Bronte Lectures","19PM",  2018, 5.50,
                0, 6.00, 11.00, 12, 1, "sample/Assets/Aquaman.jpg"));
        super.add(new Film("The Grinch", "Creative Arts","17PM",  2018, 5.00,
                3.00, 6.00, 10.00, 0, 1, "sample/Assets/TheGrinch.jpg"));
        super.add(new Film("Wonder Woman", "Edith Key","20PM",  2017, 4.50,
                0, 5.00, 8.29, 12, 1, "sample/Assets/WonderWoman.jpg"));
        super.add(new Film("Titanic", "Quayside","19PM",  1998, 1.50,
                0, 2.00, 3.50, 12, 1, "sample/Assets/Titanic.jpg"));
        super.add(new Film("Avatar", "Ramsden","20PM",  2009, 3.20,
                0, 4.20, 7.50, 12, 1, "sample/Assets/Avatar.jpg"));
        super.add(new Film("Matrix", "St. Pauls Hall","21PM",  1999, 1.25,
                0, 1.50, 2.00, 15, 1, "sample/Assets/Matrix.jpg"));
        super.add(new Film("John Wick", "Heritage Quay","22PM",  2014, 3.50,
                0, 4.00, 7.00, 15, 1, "sample/Assets/Wick.jpg"));
        super.add(new Film("Wonder Park", "Joseph Priestley","15PM",  2019, 5.50,
                3.00, 6.00, 9.00, 0, 1, "sample/Assets/WonderPark.jpg"));

        //==========Films for Week 2===========
        super.add(new Film("Dog's Way Home", "Sparck Jones", "20PM",  2019, 6.00,
                4.00, 7.00, 13.00, 0, 2, "sample/Assets/DogsWayHome.jpg"));
        super.add(new Film("The Grinch", "Bronte Lectures","17PM",  2018, 5.00,
                3.00, 6.00, 10.00, 0, 2, "sample/Assets/TheGrinch.jpg"));
        super.add(new Film("The Meg","Oastler Building",  "18PM",  2018, 3.50,
                0, 4.00, 7.00, 12, 2, "sample/Assets/TheMeg.jpg"));
        super.add(new Film("Bohemian Rhapsody","Creative Arts", "18PM", 2019, 6.50,
                0, 7.00, 10.00, 12,2, "sample/Assets/Bohemian.jpg"));
        super.add(new Film("Minions","Edith Key", "16PM", 2015, 3.50,
                3.00, 4.00, 7.00, 0,2, "sample/Assets/Minions.jpg"));
        super.add(new Film("The Hobbit","Quayside", "17PM", 2014, 2.50,
                0, 3.50, 6.55, 15,2, "sample/Assets/Hobbit.jpg"));
        super.add(new Film("Hereditary","Ramsden", "12PM", 2018, 6.00,
                0, 6.59, 10.00, 15,2, "sample/Assets/Hereditary.jpg"));
        super.add(new Film("Spies in Disguise","St. Pauls Hall", "15PM", 2019, 6.25,
                4.50, 7.59, 13.00, 0,2, "sample/Assets/Spies.jpg"));
        super.add(new Film("Deadpool","Heritage Quay", "18PM", 2016, 4.30,
                0, 5.30, 8.00, 15,2, "sample/Assets/Deadpool.jpg"));
        super.add(new Film("Alita", "Joseph Priestley", "20PM",  2019, 5.50,
                0, 7.00, 9.00, 12, 2, "sample/Assets/Alita.jpg"));


        //==========Films for Week 3===========
    }
    public Film findFilmByTitle(String title){

        Film temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);

            if (temp.getTitle().equals(title)){
                indexLocation = i;
                break;
            }
        }
        if (indexLocation == -1) {
            return null;
        } else {
            return super.get(indexLocation);
        }
    }
    public void removeUnavailableFilms(){
        Iterator<Film> iterator = iterator();
        while(iterator.hasNext()){
            Film s = iterator.next();
            Theatre searchedTheatre = searchEngine.searchTheatre(s.getTheatreTitle(), s.getWeekNumber());
            if(searchedTheatre.getAvailableSeats() == 0){
                iterator.remove();
            }
        }
    }
}
