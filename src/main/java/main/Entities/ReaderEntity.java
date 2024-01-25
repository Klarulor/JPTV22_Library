package main.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reader", schema = "lib")
public class ReaderEntity extends MyEntity<ReaderEntity>{
    @Basic
    @Column(name = "fname")
    private String fname;
    @Basic
    @Column(name = "lname")
    private String lname;
    @Basic
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Basic
    @Column(name = "bornYear")
    private Integer bornYear;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getBornYear() {
        return bornYear;
    }

    public void setBornYear(Integer bornYear) {
        this.bornYear = bornYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderEntity reader = (ReaderEntity) o;

        if (fname != null ? !fname.equals(reader.fname) : reader.fname != null) return false;
        if (lname != null ? !lname.equals(reader.lname) : reader.lname != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(reader.phoneNumber) : reader.phoneNumber != null) return false;
        if (bornYear != null ? !bornYear.equals(reader.bornYear) : reader.bornYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fname != null ? fname.hashCode() : 0;
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (bornYear != null ? bornYear.hashCode() : 0);
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s.) %s %s %s", getId(), getFname(), getLname(), getBornYear());
    }
}
