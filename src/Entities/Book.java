package Entities;

import java.util.Arrays;
import java.util.Objects;

public class Book {
    private String _title;
    private int _publishedYear;
    private Author[] _authors;
    private int _quantity;
    private int _count;


    public Book(String title, int publishedYear, Author[] authors, int count) {
        this._title = title;
        this._publishedYear = publishedYear;
        this._authors = authors;
        this._count = count;
        this._quantity = count;
    }
    public Book(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return _publishedYear == book._publishedYear && Objects.equals(_title, book._title) && Arrays.equals(_authors, book._authors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(_title, _publishedYear);
        result = 31 * result + Arrays.hashCode(_authors);
        return result;
    }

    @Override
    public String toString() {
        return _title + " (" + _publishedYear + ") (" + _count + " books)";
    }

    public int getPublishedYear() {
        return _publishedYear;
    }

    public void setAuthors(Author[] authors) {
        this._authors = authors;
    }

    public String getTitle() {
        return _title;
    }

    public Author[] getAuthors() {
        return _authors;
    }

    public void setPublishedYear(int publishedYear) {
        this._publishedYear = publishedYear;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public int getCount(){return _count;}
    public void setCount(int count){_count = count;}
    public int getQuantity(){return _quantity;}
    public void setQuantity(int quantity){_quantity = quantity;}
}
