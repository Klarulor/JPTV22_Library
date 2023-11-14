package Managers;

import Entities.Reader;

import java.util.ArrayList;
import java.util.List;

public class ReaderManager {
    private List<Reader> _readers = new ArrayList<>();

    public ReaderManager(){

    }
    public ReaderManager(Reader[] readers){

        for(int i = 0; i < readers.length; i++){
            if(readers[i] != null)
                _readers.add(readers[i]);
        }
    }

    public Reader createReader(String fname, String lname, String phoneNumber, int bornYear){
        return new Reader(fname, lname, phoneNumber, bornYear);
    }
    public void addReader(Reader reader){
        _readers.add(reader);
    }

    public void setReaders(List<Reader> readers){
        _readers = readers;
    }
    public List<Reader> getReaders(){
        return _readers;
    }
}
