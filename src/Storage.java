
import Managers.BookAssignHistoryManager;
import Managers.BookManager;
import Managers.ReaderManager;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    private final String path = "./storage.json";
    public BookManager bookManager;
    public ReaderManager readerManager;
    public BookAssignHistoryManager historyManager;

    public Storage(BookManager bookManager, ReaderManager readerManager, BookAssignHistoryManager historyManager){
        this.bookManager = bookManager;
        this.readerManager = readerManager;
        this.historyManager = historyManager;
    }
    public Storage(){

    }
    public void load(){
        try {
            String text = Files.readString(Paths.get(path));
            Storage storage = deserializeFromJson(text, Storage.class);

            readerManager.setReaders(storage.readerManager.getReaders());
            bookManager.setBooks(storage.bookManager.getBooks());
            historyManager.load(storage.historyManager.getHistories());

        } catch (IOException e) {

        }
    }
    public void save(){

        File yourFile = new File(path);
        try {
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String str = serializeToJson(this);

        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public String serializeToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public <T> T deserializeFromJson(String json, Class<T> type) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public BookManager getBookManager() {
//        return bookManager;
//    }
//
//    public ReaderManager getReaderManager() {
//        return readerManager;
//    }
//
//    public void setBookManager(BookManager bookManager) {
//        this.bookManager = bookManager;
//    }
//
//    public void setReaderManager(ReaderManager readerManager) {
//        this.readerManager = readerManager;
//    }

//    public BookAssignHistoryManager getHistoryManager(BookAssignHistoryManager historyManager) {
//        return this.historyManager;
//    }
//
//    public void setHistoryManager(BookAssignHistoryManager historyManager) {
//        this.historyManager = historyManager;
//    }
}
