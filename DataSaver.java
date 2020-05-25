package sample;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DataSaver {
    public static void saveCustomers(Customers customers){
        try {
            FileOutputStream out = new FileOutputStream("Customers.dat");
            ObjectOutputStream oOut = new ObjectOutputStream(out);
            oOut.writeObject(customers);
            oOut.flush();
            oOut.close();

        } catch (Exception e) {

            AlertMaker.showWarningWithoutNode("Failed to Save the Data");
            System.err.println("Error : " + e);
        }
    }
}
