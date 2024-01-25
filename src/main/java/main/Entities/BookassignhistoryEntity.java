package main.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bookassignhistory", schema = "lib")
public class BookassignhistoryEntity extends MyEntity<BookassignhistoryEntity> {
    @Basic
    @Column(name = "reader")
    private String reader;
    @Basic
    @Column(name = "book")
    private String book;
    @Basic
    @Column(name = "date")
    private String date;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookassignhistoryEntity that = (BookassignhistoryEntity) o;

        if (reader != null ? !reader.equals(that.reader) : that.reader != null) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reader != null ? reader.hashCode() : 0;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
