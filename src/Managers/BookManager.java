package Managers;

import Entities.Author;
import Entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    //private Book[] _books;
    private List<Book> _books = new ArrayList<>();

    public BookManager(){
        //_books = new Book[255];
    }
    public BookManager(Book[] books){
        for(int i = 0; i < books.length; i++)
            if(books[i] != null)
                _books.add(books[i]);
    }

    public Book createBook(String title, int publishedYear, Author[] authors, int count){
        return new Book(title, publishedYear, authors, count);
    }
    public void addBook(Book book){
       _books.add(book);
    }

    public void setBooks(List<Book> books){
        _books = books;
    }
    public List<Book> getBooks(){
        return _books;
    }
}
