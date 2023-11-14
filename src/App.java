import Entities.Author;
import Entities.Book;
import Entities.BookAssignHistory;
import Entities.Reader;
import Managers.BookAssignHistoryManager;
import Managers.BookManager;
import Managers.ReaderManager;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public BookManager bookManager;
    public ReaderManager readerManager;
    public BookAssignHistoryManager historyManager;

    public Storage storage;
    public static App Instance;
    public App(){
        Instance = this;
        bookManager = new BookManager();
        readerManager = new ReaderManager();
        historyManager = new BookAssignHistoryManager(255);
        storage = new Storage(bookManager, readerManager, historyManager);
    }
    public void run(){
        storage.load();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        do{
            System.out.print("Select task:\n0 - exit\n1 - Add new book\n2 - Add new reader\n3 - Return books\n4 - Read list of readers\n5 - Give book to reader\n6 - List assigned books\n7 - Ranked read book\n8 - Most reading reader\nYour task number: ");
            int num = 0;
            try{
                num = scanner.nextInt();
            }catch(Exception err){};
            switch (num){
                case 0:
                    System.out.println("Приходите ещё раз!");
                    loop = false;
                    break;
                case 1:
                {
                    System.out.println("Имя книги: "); scanner.nextLine();
                    String bookName = scanner.nextLine();
                    System.out.println("Год выпуска: ");
                    int publishedYear = scanner.nextInt();
                    System.out.println("Сколько авторов: ");
                    int authorCount = scanner.nextInt();
                    Author[] authors = new Author[authorCount];
                    for(int i = 0; i < authorCount; i++){
                        System.out.println("Автор " + (i+1) + "й:");
                        System.out.println("    Введите имя автора:");
                        String fname = scanner.next();
                        System.out.println("    Введите фамилию автора:");
                        String lname = scanner.next();
                        authors[i] = new Author(fname, lname);

                    }
                    System.out.println("Авторы:");
                    for(int i = 0; i < authorCount; i++){
                        System.out.println((i+1)+".) "+ authors[i].firstname + " " + authors[i].lastname);
                    }
                    System.out.println("Количество на складе: ");
                    int count = scanner.nextInt();
                    Book newbook = bookManager.createBook(bookName, publishedYear, authors, count);
                    bookManager.addBook(newbook);
                }
                    break;
                case 2:
                {
                    System.out.println("    Введите имя: ");
                    String fname = scanner.next();
                    System.out.println("    Введите фамилию: ");
                    String lname = scanner.next();
                    System.out.println("Год рождения: ");
                    int bornYear = scanner.nextInt();
                    System.out.println("    Введите номер телефона: ");
                    String phoneNum = scanner.next();
                    Reader reader = readerManager.createReader(fname, lname, phoneNum, bornYear);
                    readerManager.addReader(reader);
                }

                    break;
                case 3:
                {
                    Iterator<Book> it = bookManager.getBooks().iterator();
                    while(it.hasNext()){
                        Book book = it.next();
                        String stringAuthors = "";
                        for(int z = 0; z < book.getAuthors().length; z++){
                            stringAuthors += " " + book.getAuthors()[z].toString() + ",";
                        }
                        System.out.println(book.getTitle() + " " + book.getPublishedYear() + " " + stringAuthors);
                    }
                    break;
                }
                case 4:
                {
                    for (Reader reader : readerManager.getReaders()) {
                        String stringAuthors = "";
//                            for(int z = 0; z < readerManager.readers.length; z++){
//                                if(readerManager.readers[z] != null)
//                                    stringAuthors += " " + readerManager.readers[z].toString() + ",";
//                            }
                        System.out.println(reader.toString() + " " + stringAuthors);
                    }

                }
                    break;
                case 5:
                {
                    System.out.println("List of readers:");
                    List<Reader> readers = readerManager.getReaders();
                    for(int i = 0; i < readers.size(); i++){
                        if(readers.get(i) != null){
                            System.out.print(i + ".) " + readers.get(i));
                        }
                    }
                    System.out.print("\nSelect number: ");
                    int index = scanner.nextInt();
                    Reader selectedReader = readers.get(index);
                    System.out.println("List of books");
                    Iterator<Book> it = bookManager.getBooks().iterator();
                    int i = 0;
                    while(it.hasNext()){
                        Book book = it.next();
                        if(book.getCount() > 0){
                            System.out.print(i + ".) " + bookManager.getBooks().get(i));
                        }else{
                            System.out.print(i + ".) " + bookManager.getBooks().get(i) + " (CANNOT GET)");
                        }
                        i++;
                        System.out.println();
                    }

                    System.out.print("\nSelect book number");
                    int bookIndex = scanner.nextInt();
                    Book selectedBook = bookManager.getBooks().get(bookIndex);
                    System.out.println("Are you sure to assign book " + selectedBook + " to " + selectedReader + "? (y)");
                    String answr = scanner.next();
                    if (answr.equals("y")) {
                        System.out.println("Запись");
                        historyManager.assign(selectedReader, selectedBook);
                    }else{
                        System.out.println("Отмена");
                    }
                }
                break;
                case 6:
                {
                    System.out.println("List of assigned books");
                    List<BookAssignHistory> histories = historyManager.getHistories();
                    for(int i = 0; i < histories.size(); i++){
                        if(histories.get(i) != null)
                            System.out.println(i+".) "+histories.get(i).getReader() + " " + histories.get(i).getBook() + " " + histories.get(i).getDate().getDate()+"."+histories.get(i).getDate().getMonth());
                    }
                }
                break;
                case 7:
                {
                    List<BookAssignHistory> histories = historyManager.getHistories().stream().sorted((b,a) -> b.getBook().getCount() - a.getBook().getCount()).
                            collect(Collectors.toList());

                    List<Integer> usedNames = new ArrayList<>();
                    histories = histories.stream().filter(x -> {
                        boolean b = !usedNames.contains(x.getBook().hashCode());
                        usedNames.add(x.getBook().hashCode());
                        return b;
                    }).collect(Collectors.toList());

                    for(BookAssignHistory history : histories){
                        System.out.println(history.getBook() + " " + (history.getBook().getQuantity() - history.getBook().getCount()));
                    }
                }
                break;
                case 8:
                {
                    List<BookAssignHistory> histories = historyManager.getHistories();
                    HashMap<Integer, Integer> users = new HashMap<>();
                    int mostHC = 0, mostV = 0;
                    for(BookAssignHistory history : histories){
                        int readerHC = history.getReader().hashCode();
                        if(!users.containsKey(readerHC))
                            users.put(readerHC, 0);
                        int count = users.get(readerHC);
                        users.remove(readerHC);
                        users.put(readerHC, count+1);

                        if(count+1 > mostV)
                        {
                            mostHC = readerHC;
                            mostV = count+1;
                        }
                    }
                    int finalMostHC = mostHC;
                    Reader reader = (Reader) readerManager.getReaders().stream().filter(x -> x.hashCode() == finalMostHC).toArray()[0];
                    System.out.println(reader + " " + mostV + " times");

                }
                break;
                default:
                    System.out.println("Reselect a true number");
            }
            System.out.println("-------");


            storage.save();

//            System.out.print("next? (y)");
//            String text = scanner.next();
//            if(!text.equals("y")) {
//                b
//            }


        }while(loop);
    }
}
