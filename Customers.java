package sample;

import java.util.ArrayList;

public class Customers extends ArrayList<Customer> {
    public Customers(){
        super(new ArrayList<>());
    }
    public void addCustomer(String name, String surname, String password, int age){
        super.add(new Customer(name, surname, password, age));
    }
    public Customer findCustomerByNameAndSurname(String name, String surname){

        Customer temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);

            if (temp.getName().equals(name) && temp.getSurname().equals(surname) ){
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
