package sample;

public class SearchEngine {
    private Theatres theatres = MainPageController.theatres;
    private Cards cards = MainPageController.cards;
    private Films films = MainPageController.films;
    private Basket basket = MainPageController.basket;
    private Customers customers = LoginPageController.customers;

    public Theatre searchTheatre(String title, int week){
        return theatres.findByTitleAndWeek(title, week);
    }
    public Card searchCard(String cardNumber){
        return cards.findByNumber(cardNumber);
    }
    public Film findFilmByTitle(String title){
        return films.findFilmByTitle(title);
    }
    public BasketItem searchBasketItem(Film film){
        BasketItem searchedItem = basket.findByTitleAndWeek(film.getTitle(), film.getWeekNumber());
        if(searchedItem != null){
            return searchedItem;
        }else{
            return null;
        }
    }
    public Customer findCustomerByNameAndSurname(String name, String surname){
        return customers.findCustomerByNameAndSurname(name, surname);
    }
}
