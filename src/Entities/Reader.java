package Entities;

import java.util.Objects;

public class Reader {
    private String fname;
    private String lname;
    private String phoneNumber;
    private int bornYear;

    public Reader(String fname, String lname, String phoneNumber, int bornYear) {
        this.setFname(fname);
        this.setLname(lname);
        this.setPhoneNumber(phoneNumber);
        this.setBornYear(bornYear);
    }
    public Reader(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return getBornYear() == reader.getBornYear() && Objects.equals(getFname(), reader.getFname()) && Objects.equals(getLname(), reader.getLname()) && Objects.equals(getPhoneNumber(), reader.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFname(), getLname(), getPhoneNumber(), getBornYear());
    }

    @Override
    public String toString() {
        return fname + " " + lname +" "+ bornYear;
    }

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

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }
}
