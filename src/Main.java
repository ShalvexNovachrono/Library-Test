
import static FrontEndSide.GUI_WORKER.*;
import BackEndSide.organiser.Book;

public class Main {

    public static void main(String[] args) {

        Frame("Local Library"); // Makes a frame with the Title "Local Library"
        Load_Books();
        Panel_Number_4(); // This will call the GUI function in "FrontEndSide/GUI_WORKER.java"
       
    }

    public static void Load_Books() {
        int count = 0;
        for (int i = 0; i <= 15; i++) {
            Book n = new Book("New Book " + count, "Jacob", "This book is tooo good");
            n.addBookRating(10);
            n.addBookRating(10);
            n.addBookRating(10);
            n.addBookRating(10);
            n.addBookRating(9);
            n.addBookRating(10);


            BackEndSide.organiser.BookShelf_.addBook(n);

            count++;


            n = new Book("New Book " + count, "Lilly", "This book is mid");
            n.addBookRating(1);
            n.addBookRating(2);
            n.addBookRating(2);
            n.addBookRating(3);
            n.addBookRating(9);
            n.addBookRating(10);
            n.setCurrently_Taken(true);


            BackEndSide.organiser.BookShelf_.addBook(n);
            count++;


            n = new Book("New Book " + count, "Robin", "This book is not good");
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(3);


            BackEndSide.organiser.BookShelf_.addBook(n);
            count++;

            n = new Book("New Book " + count, "Des", "This book is not great");
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(10);
            n.addBookRating(10);


            BackEndSide.organiser.BookShelf_.addBook(n);
            count++;
            
            
            n = new Book("New Book " + (i + 4), "Nuzs", "This book is not bad");
            n.addBookRating(10);
            n.addBookRating(10);
            n.addBookRating(3);
            n.addBookRating(3);
            n.addBookRating(7);
            n.addBookRating(3);
            n.addBookRating(10);
            n.addBookRating(10);


            BackEndSide.organiser.BookShelf_.addBook(n);
            count++;
            

        }
    }
}


