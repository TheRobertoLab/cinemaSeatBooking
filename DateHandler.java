package sample;

import java.time.LocalDate;
import java.time.Period;

public class DateHandler {
    public static int calculateCustomersAge(LocalDate birthDate, LocalDate currentDate){
        if((birthDate != null) && (currentDate != null)){
            return Period.between(birthDate, currentDate).getYears();
        }else{
            return 0;
        }
    }
    public static boolean checkDate(LocalDate birthDate, LocalDate currentDate) {
        //Check entered date, don't allow to start if date of birth is after current date
        if(birthDate.isBefore(currentDate)){
            return true;
        }else{
            return false;
        }
    }
}
