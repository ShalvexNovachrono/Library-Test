package BackEndSide;

import java.util.ArrayList;

public class organiser {
    
    public static class Book {
        private int BookID;
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
            this.Currently_Taken = false;
        }

        public String getBookName() {
            return BookName;
        }

        public String getAuthor() {
            return Author;
        }

        public void setID(int ID) {
            this.BookID = ID;
        }

        public int getID() {
            return BookID;
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
            return this.Currently_Taken;
        }
        public void setCurrently_Taken(Boolean Currently_Taken) {
            this.Currently_Taken = Currently_Taken;
        }
    }
    public static class BookShelf {
        private ArrayList<Book> BookShelfItems;

        public BookShelf() {
            this.BookShelfItems = new ArrayList<>();
        }

        public Book getBookByIndex(int Index) {
            return BookShelfItems.get(Index);
        }

        public ArrayList<Book> getAllBooks() {
            return this.BookShelfItems;
        }

        public void addBook(Book book) {
            book.setID(BookShelfItems.size()); // this will create a unique ID for each book
            this.BookShelfItems.add(book);
        }

        public void removeBook(int Index) {
            this.BookShelfItems.remove(Index);
        }

        public void removeAllBooks() {
            this.BookShelfItems.removeAll(BookShelfItems);
        }

        public ArrayList<Integer> searchBook_getIDs(String query) {
            ArrayList<Integer> ids = new ArrayList<>();
            for (Book book : BookShelfItems) {
                if (book.getBookName().toLowerCase().contains(query.toLowerCase()) || 
                    book.getBookDescription().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    ("View: " + Integer.toString(book.getAmountOfViews())).toLowerCase().contains(query.toLowerCase()) ||
                    ("Rating: " + Float.toString(book.getBookRating())).toLowerCase().contains(query.toLowerCase())) {
                    ids.add(book.getID());
                }
            }
            return ids;
        }
    }

    static class User {
        private String UserName;
        private String type;
        private ArrayList<Integer> ReadBooks; // takes the id of the book that way we can save proccess power and ram hopefully
        public ArrayList<Integer> BooksOnHand;
        public ArrayList<Integer> OverDueBooksOnHand;

        public User(String UserName, String type) {
            this.UserName = UserName;
            this.type = type;
            this.ReadBooks = new ArrayList<Integer>();
            this.BooksOnHand = new ArrayList<Integer>();
            this.OverDueBooksOnHand = new ArrayList<Integer>();
        }

        public String getUserName() {
            return this.UserName;
        }

        public String getType() {
            return this.type;
        }

        public ArrayList<Integer> getReadBooks() {
            return this.ReadBooks;
        }

        public void addToReadBooks(int BookID) {
            this.ReadBooks.add(BookID);
        }

        public ArrayList<Integer> getBooksOnHand() {
            return this.BooksOnHand;
        }
        public void addToBooksOnHand(int BookID) {
            if (this.BooksOnHand.size() <= 3) {
                this.BooksOnHand.add(BookID);
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

        public ArrayList<Integer> getOverDueBooksOnHand() {
            return this.OverDueBooksOnHand;
        }
        public void addToOverDueBooksOnHand(int BookID) {
            this.OverDueBooksOnHand.add(BookID);
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

    public static BookShelf BookShelf_ = new BookShelf();
}
