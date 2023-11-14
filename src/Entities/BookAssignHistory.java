package Entities;

import java.util.Date;

public class BookAssignHistory {
    private Reader _reader;
    private Book _book;
    private Date _date;

    public BookAssignHistory(){}
    public BookAssignHistory(Reader reader, Book book, Date date){
        _reader = reader;
        _book = book;
        _date = date;
    }


    public Reader getReader() {
        return _reader;
    }
    public void setReader(Reader reader){
        _reader = reader;
    }

    public Book getBook() {
        return _book;
    }
    public void setBook(Book book){
        _book = book;
    }

    public Date getDate() {
        return _date;
    }
    public void setDate(Date date){
        _date = date;
    }
}
