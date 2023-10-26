import Entities.Author;
import Entities.Book;
import Entities.BookAssignHistory;
import Entities.Reader;
import Managers.BookAssignHistoryManager;
import Managers.BookManager;
import Managers.ReaderManager;

import java.util.Scanner;

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
            System.out.print("Select task:\n0 - exit\n1 - Add new book\n2 - Add new reader\n3 - Return books\n4 - Read list of readers\n5 - Give book to reader\n6 - List assigned books\nYour task number: ");
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
                    for(int i = 0; i < bookManager.getBooks().length; i++){
                        if(bookManager.getBooks()[i] != null){
                            Book book = bookManager.getBooks()[i];
                            String stringAuthors = "";
                            for(int z = 0; z < book.getAuthors().length; z++){
                                stringAuthors += " " + book.getAuthors()[z].toString() + ",";
                            }
                            System.out.println(book.getTitle() + " " + book.getPublishedYear() + " " + stringAuthors);
                        }
                    }
                    break;
                }
                case 4:
                {
                    for(int i = 0; i < readerManager.getReaders().length; i++){
                        if(readerManager.getReaders()[i] != null){
                            Reader readerR = readerManager.getReaders()[i];
                            String stringAuthors = "";
//                            for(int z = 0; z < readerManager.readers.length; z++){
//                                if(readerManager.readers[z] != null)
//                                    stringAuthors += " " + readerManager.readers[z].toString() + ",";
//                            }
                            System.out.println(readerR.toString() + " " + stringAuthors);
                        }
                    }
                }
                    break;
                case 5:
                {
                    System.out.println("List of readers:");
                    Reader[] readers = readerManager.getReaders();
                    for(int i = 0; i < readers.length; i++){
                        if(readers[i] != null){
                            System.out.print(i + ".) " + readers[i]);
                        }
                    }
                    System.out.print("\nSelect number: ");
                    int index = scanner.nextInt();
                    Reader selectedReader = readers[index];
                    System.out.println("List of books");
                    Book[] books = bookManager.getBooks();
                    for(int i = 0; i < books.length; i++){
                        if(books[i] != null){
                            if(books[i].getCount() > 0){
                                System.out.print(i + ".) " + books[i]);
                            }else{
                                System.out.print(i + ".) " + books[i] + " (CANNOT GET)");
                            }
                        }
                    }
                    System.out.print("\nSelect book number");
                    int bookIndex = scanner.nextInt();
                    Book selectedBook = books[bookIndex];
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
                    BookAssignHistory[] histories = historyManager.getHistories();
                    for(int i = 0; i < histories.length; i++){
                        if(histories[i] != null)
                            System.out.println(i+".) "+histories[i].getReader() + " " + histories[i].getBook() + " " + histories[i].getDate().getDate()+"."+histories[i].getDate().getMonth());
                    }
                }
                break;
                default:
                    System.out.println("Reselect a true number");
            }


            storage.save();

//            System.out.print("next? (y)");
//            String text = scanner.next();
//            if(!text.equals("y")) {
//                b
//            }


        }while(loop);
    }
}
