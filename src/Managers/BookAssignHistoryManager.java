package Managers;

import Entities.Book;
import Entities.BookAssignHistory;
import Entities.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class BookAssignHistoryManager {
    private List<BookAssignHistory> _histories = new ArrayList<>();

    public BookAssignHistoryManager(int length){

    }
    public BookAssignHistoryManager(BookAssignHistory[] histories){
        for(int i = 0; i < histories.length; i++){
            _histories.add(histories[i]);
        }
    }
    public BookAssignHistoryManager(){}
    public void load(List<BookAssignHistory> histories){
        _histories = histories;
    }

    public void assign(Reader reader, Book book){
        _histories.add(new BookAssignHistory(reader, book, new Date()));
        book.setCount(book.getCount()-1);
    }

    public BookAssignHistory[] searchByReader(Reader reader){
        return (BookAssignHistory[]) _histories.stream().filter(x -> x.getReader().equals(reader)).toArray();
    }
    public BookAssignHistory[] searchByBook(Book book){
        return (BookAssignHistory[]) _histories.stream().filter(x -> x.getBook().equals(book)).toArray();
    }

    public List<BookAssignHistory> getHistories(){
        return _histories;
    }
    public void setHistories(List<BookAssignHistory> histories){
        _histories = histories;
    }
}
