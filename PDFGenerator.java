package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class PDFGenerator {

    public PDFGenerator() {
    }

    public static void saveAndGeneratePDF(Stage stage, Purchase purchase, Card card){
        //This method creates PDF document and adds to it a text
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        String cardExtractedLastDigits = card.getCardNumber().substring(7, 11);
        String hiddenCardNumber = "********" + cardExtractedLastDigits;

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        fileChooser.setInitialFileName(purchase.getFilmTitle() + " Ticket");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null)
        {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDImageXObject pdImage = PDImageXObject.createFromFile("D:/AssignmentV2/src/sample/Assets/PDFLogo.jpg", document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawXObject(pdImage, 10, 620, 595, 160);
            contentStream.beginText();

            //Setting the position for the text line
            contentStream.newLineAtOffset(26, 625);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.setLeading(14.5f);

            contentStream.showText("Your Purchase: ");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Film Title: " + purchase.getFilmTitle());
            contentStream.newLine();
            contentStream.showText("Place: " + purchase.getTheatreTitle() + ", Week " + purchase.getWeekNumber());
            contentStream.newLine();
            contentStream.showText("Time: " + purchase.getStartTime());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Tickets: " + purchase.getTickets());
            contentStream.newLine();
            contentStream.showText("Seats: " + purchase.getMySeats());
            contentStream.newLine();
            contentStream.showText("Victuals: " + purchase.getVictuals());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Total Amount: " );
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Payment Details: ");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Buyer's Full Name: " + card.getHoldersFullName());
            contentStream.newLine();
            contentStream.showText("Card Number: " + hiddenCardNumber);

            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Unique ID (provide it for a Cashier): " + purchase.getOrderUniqueID());
            contentStream.newLine();
            contentStream.showText("Booking Date and Time: " + purchase.getWhenBought());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            //Put greeting on the center of page
            //I have just one idea how to do it:
            contentStream.showText("                                                               Have a Nice Day!");
            contentStream.endText();

            //Add prices to the pdf
            contentStream.beginText();
            contentStream.newLineAtOffset(540, 625);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.setLeading(14.5f);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("" + gb.format(purchase.getTicketsAmount()));
            contentStream.newLine();
            contentStream.showText("" + gb.format(purchase.getMySeatsAmount()));
            contentStream.newLine();
            contentStream.showText("" + gb.format(purchase.getMyVictualsAmount()));
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("" + gb.format(purchase.getAmount()));
            contentStream.endText();

            //Draw rectangles as a decorate lines
            contentStream.setNonStrokingColor(Color.DARK_GRAY);
            contentStream.addRect(25, 468, 550, 1);
            contentStream.addRect(25, 350, 550, 1);
            contentStream.fill();

            contentStream.close();

            document.save(file);

            AlertMaker.showInformation("Your choice successfully saved", "File has been saved as a PDF");

            //Closing the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();

            AlertMaker.showWarningWithText("Please close file with the same title to overwrite it\n" +
                    "Or you may change a file title", "Close opened file");
        }
    }

    }
    public static void saveAndGeneratePDFPaidCash(Stage stage, Purchase purchase) {
        //This method creates PDF document and adds to it a text
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        fileChooser.setInitialFileName(purchase.getFilmTitle() + " Ticket");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);

                PDImageXObject pdImage = PDImageXObject.createFromFile("D:/AssignmentV2/src/sample/Assets/PDFLogo.jpg", document);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.drawXObject(pdImage, 10, 620, 595, 160);
                contentStream.beginText();

                //Setting the position for the text line
                contentStream.newLineAtOffset(26, 625);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.setLeading(14.5f);

                contentStream.showText("Your Purchase: ");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Film Title: " + purchase.getFilmTitle());
                contentStream.newLine();
                contentStream.showText("Place: " + purchase.getTheatreTitle() + ", Week " + purchase.getWeekNumber());
                contentStream.newLine();
                contentStream.showText("Time: " + purchase.getStartTime());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Tickets: " + purchase.getTickets());
                contentStream.newLine();
                contentStream.showText("Seats: " + purchase.getMySeats());
                contentStream.newLine();
                contentStream.showText("Victuals: " + purchase.getVictuals());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Total Amount: ");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Unique ID (provide it for a Cashier): " + purchase.getOrderUniqueID());
                contentStream.newLine();
                contentStream.showText("Booking Date and Time: " + purchase.getWhenBought());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("                                                               Have a Nice Day!");
                contentStream.endText();

                //Add prices to the pdf
                contentStream.beginText();
                contentStream.newLineAtOffset(540, 625);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.setLeading(14.5f);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("" + gb.format(purchase.getTicketsAmount()));
                contentStream.newLine();
                contentStream.showText("" + gb.format(purchase.getMySeatsAmount()));
                contentStream.newLine();
                contentStream.showText("" + gb.format(purchase.getMyVictualsAmount()));
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("" + gb.format(purchase.getAmount()));
                contentStream.endText();

                //Draw rectangles as a decorate lines
                contentStream.setNonStrokingColor(Color.DARK_GRAY);
                contentStream.addRect(25, 468, 550, 1);
                contentStream.addRect(25, 350, 550, 1);
                contentStream.fill();

                contentStream.close();

                document.save(file);

                AlertMaker.showInformation("Your choice successfully saved", "File has been saved as a PDF");

                //Closing the document
                document.close();
            } catch (Exception e) {
                e.printStackTrace();

                AlertMaker.showWarningWithText("Please close file with the same title to overwrite it\n" +
                        "Or you may change a file title", "Close opened file");
            }
        }
    }

}
