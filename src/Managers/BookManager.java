package Managers;

import Entities.Author;
import Entities.Book;

public class BookManager {
    private Book[] _books;

    public BookManager(){
        _books = new Book[255];
    }
    public BookManager(Book[] books){
        this._books = books;
    }

    public Book createBook(String title, int publishedYear, Author[] authors, int count){
        return new Book(title, publishedYear, authors, count);
    }
    public void addBook(Book book){
        for(int i = 0; i < _books.length; i++)
            if(_books[i] == null)
            {
                _books[i] = book;
                break;
            }
    }

    public void setBooks(Book[] books){
        _books = books;
    }
    public Book[] getBooks(){
        return _books;
    }
}
