package Managers;

import Entities.Reader;

public class ReaderManager {
    private Reader[] _readers;

    public ReaderManager(){
        _readers = new Reader[255];
    }
    public ReaderManager(Reader[] readers){
        this._readers = readers;
    }

    public Reader createReader(String fname, String lname, String phoneNumber, int bornYear){
        return new Reader(fname, lname, phoneNumber, bornYear);
    }
    public void addReader(Reader reader){
        for(int i = 0; i < _readers.length; i++){
            if(_readers[i] == null){
                _readers[i] = reader;
                break;
            }
        }
    }

    public void setReaders(Reader[] readers){
        _readers = readers;
    }
    public Reader[] getReaders(){
        return _readers;
    }
}
