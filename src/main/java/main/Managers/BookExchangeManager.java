package main.Managers;

import main.Entities.BookEntity;
import main.Entities.BookassignhistoryEntity;
import main.Entities.MyEntity;
import main.Entities.ReaderEntity;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BookExchangeManager {

    private static Scanner scanner = new Scanner(System.in);
    public static void giveBook(){
        print("List of books:");
        List<BookEntity> books = MyEntity.fetchAllStatic("BookEntity");
        BookManager.printBooks();
        print("Choose the id: ");
        int bookId = scanner.nextInt(); scanner.nextLine();
        BookEntity selectedBook = (BookEntity) books.stream().filter(x -> x.getId() == bookId).toArray()[0];
        if(selectedBook.getQuantity() <= 0)
        {
            print("bad book.");
            return;
        }
        List<ReaderEntity> readers = MyEntity.fetchAllStatic("ReaderEntity");
        ReaderManager.printReaders();
        print("Choose the id of reader: ");
        int readerId = scanner.nextInt(); scanner.nextLine();
        ReaderEntity selectedReader = (ReaderEntity) readers.stream().filter(x -> x.getId() == readerId).toArray()[0];

        selectedBook.setQuantity(selectedBook.getQuantity()-1);
        BookassignhistoryEntity historyEntity = new BookassignhistoryEntity();
        historyEntity.setBook(selectedBook.getId().toString());
        historyEntity.setReader(selectedReader.getId().toString());
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        historyEntity.setDate(simpleDateFormat.format(new Date()));

        historyEntity.commit();
        selectedBook.commit();



    }

    public static void printHistories(){
        List<BookassignhistoryEntity> histories = MyEntity.fetchAllStatic("BookassignhistoryEntity");
        for(BookassignhistoryEntity h : histories){
            BookEntity book = (BookEntity) ((List<BookEntity>)MyEntity.fetchAllStatic("BookEntity")).stream().filter(x -> x.getId() == Long.parseLong(h.getBook())).toArray()[0];
            ReaderEntity reader = (ReaderEntity) ((List<ReaderEntity>)MyEntity.fetchAllStatic("ReaderEntity")).stream().filter(x -> x.getId() == Long.parseLong(h.getBook())).toArray()[0];
            print(String.format("%s.) %s %s %s", h.getId()+1L, h.getDate(), book.getTitle(), reader.getFname()));
        }
    }

    public static void printRankedBookList(){
        List<BookassignhistoryEntity> histories = MyEntity.fetchAllStatic("BookassignhistoryEntity");

        List<Integer> usedNames = new ArrayList<>();
        histories = histories.stream().filter(x -> {
            boolean b = !usedNames.contains(x.getBook().hashCode());
            usedNames.add(x.getBook().hashCode());
            return b;
        }).collect(Collectors.toList());

        for(BookassignhistoryEntity history : histories){
            BookEntity book = (BookEntity) ((List<BookEntity>)MyEntity.fetchAllStatic("BookEntity")).stream().filter(x -> x.getId() == Long.parseLong(history.getBook())).toArray()[0];
            System.out.println(book.getTitle() + " " + (book.getCount() - book.getQuantity()));
        }
    }
    public static void printMostReadingReaderList(){
        List<BookassignhistoryEntity> histories = MyEntity.fetchAllStatic("BookassignhistoryEntity");
        HashMap<Long, Long> users = new HashMap<>();
        long mostHC = 0, mostV = 0;
        for(BookassignhistoryEntity history : histories){
            long readerHC = Long.parseLong(history.getReader());
            if(!users.containsKey(readerHC))
                users.put(readerHC, 0L);
            long count = users.get(readerHC);
            users.remove(readerHC);
            users.put(readerHC, count+1);

            if(count+1 > mostV)
            {
                mostHC = readerHC;
                mostV = count+1;
            }
        }
        Long finalMostHC = mostHC;
        List<ReaderEntity> readers = MyEntity.fetchAllStatic("ReaderEntity");
        ReaderEntity reader = (ReaderEntity) readers.stream().filter(x -> x.getId() == finalMostHC).toArray()[0];
        System.out.println(reader + " " + mostV + " times");
    }

    private static void print(String str){
        System.out.println(str);
    }
}

