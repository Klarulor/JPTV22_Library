package Managers;

import Entities.Book;
import Entities.BookAssignHistory;
import Entities.Reader;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

public class BookAssignHistoryManager {
    private BookAssignHistory[] _histories;

    public BookAssignHistoryManager(int length){
        _histories = new BookAssignHistory[length];
    }
    public BookAssignHistoryManager(BookAssignHistory[] histories){
        _histories = histories;
    }
    public BookAssignHistoryManager(){}
    public void load(BookAssignHistory[] histories){
        for(int i = 0; i < histories.length; i++){
            _histories[i] = histories[i];
        }
    }

    public void assign(Reader reader, Book book){
        for(int i = 0; i< _histories.length; i++){
            if(_histories[i] == null)
            {
                _histories[i] = new BookAssignHistory(reader, book, new Date());
                break;
            }
        }
        book.setCount(book.getCount()-1);
    }

    public BookAssignHistory[] searchByReader(Reader reader){
        return (BookAssignHistory[]) Arrays.stream(_histories).filter(x -> x.getReader().equals(reader)).toArray();
    }
    public BookAssignHistory[] searchByBook(Book book){
        return (BookAssignHistory[]) Arrays.stream(_histories).filter(x -> x.getBook().equals(book)).toArray();
    }

    public BookAssignHistory[] getHistories(){
        return _histories;
    }
    public void setHistories(BookAssignHistory[] histories){
        _histories = histories;
    }
}
