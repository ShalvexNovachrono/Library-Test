import FrontEndSide.GUI_WORKER;

import java.util.ArrayList;

import static FrontEndSide.GUI_WORKER.*;


public class Main {
    static class Book {
        private String BookName;
        private String Author;
        private ArrayList<Integer> BookRatingList;
        private String BookDescription;
        private int AmountOfViews;
        private String Date_Taken;
        private String Return_Due_Date;
        private String Last_Date_Taken;
        private ArrayList<String> ReadBy;
        private Boolean Currently_Taken;

        public Book(String BookName, String Author, String BookDescription){
            this.BookName = BookName;
            this.Author = Author;
            this.BookDescription = BookDescription;
            this.BookRatingList = new ArrayList<>();
            this.ReadBy = new ArrayList<>();
        }

        public String getBookName() {
            return BookName;
        }
        public String getAuthor() {
            return Author;
        }

        public float getBookRating() {
            int SumOfBookRatingList = 0;
            for (int Rating:BookRatingList) {
                SumOfBookRatingList += Rating;
            }
            return ((float) (SumOfBookRatingList) / (BookRatingList.size()));
        }
        public void addBookRating(int BookRating) {
            this.BookRatingList.add(BookRating);
        }

        public String getBookDescription() {
            return BookDescription;
        }

        public int getAmountOfViews() {
            return AmountOfViews;
        }
        public void incrementAmountOfViews() {
            this.AmountOfViews += 1;
        }

        public String getDate_Taken() {
            return Date_Taken;
        }
        public void setDate_Taken(String Date_Taken) {
            this.Date_Taken = Date_Taken;
        }

        public String getReturn_Due_Date() {
            return Return_Due_Date;
        }
        public void setReturn_Due_Date(String Return_Due_Date) {
            this.Return_Due_Date = Return_Due_Date;
        }

        public String getLast_Date_Taken() {
            return Last_Date_Taken;
        }
        public void setLast_Date_Taken(String Last_Date_Taken) {
            this.Last_Date_Taken = Last_Date_Taken;
        }

        public ArrayList<String> getAllReadBy() {
            return ReadBy;
        }
        public String getLastReadBy_ByIndex(int Index) {
            return ReadBy.get(Index);
        }
        public String getLastReadBy() {
            return ReadBy.get(ReadBy.size() - 1);
        } //  ReadBy.getLast()
        public void addToReadBy(String UserName) {
            this.ReadBy.add(UserName);
        }

        public Boolean getCurrently_Taken() {
            return Currently_Taken;
        }
        public void setCurrently_Taken(Boolean Currently_Taken) {
            this.Currently_Taken = Currently_Taken;
        }
    }
    static class BookShelf {
        private ArrayList<Book> BookShelfItems;

        public BookShelf() {
            this.BookShelfItems = new ArrayList<>();
        }

        public Book getBookByIndex(int Index) {
            return BookShelfItems.get(Index - 1);
        }

        public ArrayList<Book> getAllBooks() {
            return this.BookShelfItems;
        }

        public void addBook(Book book) {
            this.BookShelfItems.add(book);
        }

        public void removeBook(int Index) {
            this.BookShelfItems.remove(Index);
        }

        public void removeAllBooks() {
            this.BookShelfItems.removeAll(BookShelfItems);
        }
    }

    static class User {
        private String UserName;
        private String type;
        private ArrayList<Book> ReadBooks;
        public ArrayList<Book> BooksOnHand;
        public ArrayList<Book> OverDueBooksOnHand;

        public User(String UserName, String type) {
            this.UserName = UserName;
            this.type = type;
            this.ReadBooks = new ArrayList<Book>();
            this.BooksOnHand = new ArrayList<>();
            this.OverDueBooksOnHand = new ArrayList<>();
        }

        public String getUserName() {
            return UserName;
        }

        public ArrayList<Book> getReadBooks() {
            return this.ReadBooks;
        }

        public void addToReadBooks(Book book) {
            this.ReadBooks.add(book);
        }

        public ArrayList<Book> getBooksOnHand() {
            return this.BooksOnHand;
        }
        public void addToBooksOnHand(Book book) {
            if (this.BooksOnHand.size() <= 3) {
                this.BooksOnHand.add(book);
            } else {
                System.out.println("To many book on hand!");
            }
        }
        public void removeBookFromBooksOnHand_ByIndex(int Index) {
            this.BooksOnHand.remove(Index);
        }

        public void removeAllBookFromBooksOnHand() {
            this.BooksOnHand.removeAll(BooksOnHand);
        }

        public ArrayList<Book> getOverDueBooksOnHand() {
            return this.OverDueBooksOnHand;
        }
        public void addToOverDueBooksOnHand(Book book) {
            this.OverDueBooksOnHand.add(book);
        }
        public void removeOverDueBooksOnHand_ByIndex(int Index) {
            this.OverDueBooksOnHand.remove(Index);
        }

        public void removeAllOverDueBooksOnHand() {
            this.OverDueBooksOnHand.removeAll(OverDueBooksOnHand);
        }
    } // need to make search function

    static class UsersList {
        private ArrayList<User> Users;
        public UsersList() {
            this.Users = new ArrayList<>();
        }

        public void addUser(User user) {
            this.Users.add(user);
        }

        public void removeUser_ByIndex(int Index) {
            this.Users.remove(Index);
        }
        public void removeAllUser() {
            this.Users.removeAll(Users);
        }
    }

    public static void main(String[] args) {

        BookShelf bs = new BookShelf();
        Book n = new Book("New Book 0", "Jacob", "This book is tooo good");
        n.addBookRating(10);
        n.addBookRating(10);
        n.addBookRating(10);
        n.addBookRating(10);
        n.addBookRating(9);
        n.addBookRating(10);


        bs.addBook(n);


        n = new Book("New Book 1", "Lilly", "This book is mid");
        n.addBookRating(1);
        n.addBookRating(2);
        n.addBookRating(2);
        n.addBookRating(3);
        n.addBookRating(9);
        n.addBookRating(10);


        bs.addBook(n);
        n = new Book("New Book 2", "Robin", "This book is not good");
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(3);


        bs.addBook(n);
        n = new Book("New Book 3", "Des", "This book is not great");
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(10);
        n.addBookRating(10);


        bs.addBook(n);
        n = new Book("New Book 4", "Nuzs", "This book is not bad");
        n.addBookRating(10);
        n.addBookRating(10);
        n.addBookRating(3);
        n.addBookRating(3);
        n.addBookRating(7);
        n.addBookRating(3);
        n.addBookRating(10);
        n.addBookRating(10);


        bs.addBook(n);




        for (Book book: bs.getAllBooks()) {
            System.out.println("Book: " + book.getBookName() + "\nBook Description: " + book.getBookDescription() + "\nAuthor: " + book.getAuthor() + "\nRating: " + Float.toString(book.getBookRating()) + "\n");
        }

        Frame("Local Library"); // Makes a frame with the Title "Local Library"
        Panel_Number_1(); // This will call the GUI function in "FrontEndSide/GUI_WORKER.java"


        GUI_WORKER.LoginButton.addActionListener(v -> { // this access the global jbutton variable and checks if you did anything with the button like clicking
            Panel_Number_2(); // this will show login page
        });

        GUI_WORKER.RegisterButton.addActionListener(v -> {
            System.out.println("Register Please");
        });
    }
}


