package sample;

import java.util.ArrayList;

public class Theatres extends ArrayList<Theatre>{
    public Theatres(){
        //Create Theatres for each week
        super(new ArrayList<>());
        //Theatres for Week 1
        //I create for each week theatre because we need to store seats data for each week.
        //We have different available seats, and automatically flexibility, because for same theatre we can set different number of seats in a week.
        super.add(new Theatre("Sparck Jones", 1, 20,
                    130, 10, 0.50,2.50, 2.00, 160, 160));
        super.add(new Theatre("Bronte Lectures", 1, 10,
                    100, 10, 0.50, 3.00, 2.50, 120, 120));
        super.add(new Theatre("Oastler Building", 1, 10,
                    100, 20, 0.00,1.50, 2.00, 130, 130));
        super.add(new Theatre("Creative Arts", 1, 10,
                130, 10, 0.25,2.50, 3.00, 150, 150));
        super.add(new Theatre("Edith Key", 1, 10,
                140, 20, 0.00,1.00, 2.00, 170, 170));
        super.add(new Theatre("Quayside", 1, 14,
                120, 10, 0.30,1.25, 2.00, 144, 144));
        super.add(new Theatre("Ramsden", 1, 10,
                105, 15, 0.00,1.25, 1.90, 130, 130));
        super.add(new Theatre("St. Pauls Hall", 1, 16,
                150, 10, 0.25,2.50, 3.00, 176, 176));
        super.add(new Theatre("Heritage Quay", 1, 10,
                130, 10, 0.00,1.50, 2.00, 150, 150));
        super.add(new Theatre("Joseph Priestley", 1, 5,
                130, 5, 0.15,1.15, 2.15, 140, 140));

        //Theatres for Week 2
        super.add(new Theatre("Sparck Jones", 2, 20,
                130, 10, 0.50,2.50, 2.00, 160, 160));
        super.add(new Theatre("Bronte Lectures", 2, 10,
                100, 10, 0.50, 3.00, 2.50, 120, 120));
        super.add(new Theatre("Oastler Building", 2, 10,
                100, 20, 0.00,1.50, 2.00, 130, 130));
        super.add(new Theatre("Creative Arts", 2, 10,
                130, 10, 0.25,2.50, 3.00, 150, 150));
        super.add(new Theatre("Edith Key", 2, 10,
                140, 20, 0.00,1.00, 2.00, 170, 170));
        super.add(new Theatre("Quayside", 2, 14,
                120, 10, 0.30,1.25, 2.00, 144, 144));
        super.add(new Theatre("Ramsden", 2, 10,
                105, 15, 0.00,1.25, 1.90, 130, 130));
        super.add(new Theatre("St. Pauls Hall", 2, 16,
                150, 10, 0.25,2.50, 3.00, 176, 176));
        super.add(new Theatre("Heritage Quay", 2, 10,
                130, 10, 0.00,1.50, 2.00, 150, 150));
        super.add(new Theatre("Joseph Priestley", 2, 5,
                130, 5, 0.15,1.15, 2.15, 140, 140));



    }

    public Theatre findByTitleAndWeek(String title, int week){

        Theatre temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);

            if (temp.getTheatreTitle().equals(title) && temp.getWeekNumber() == week){
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
}
