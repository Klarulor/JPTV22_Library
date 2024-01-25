package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import main.Entities.BookEntity;
import main.Entities.ReaderEntity;
import main.Managers.BookExchangeManager;
import main.Managers.BookManager;
import main.Managers.ReaderManager;

import java.io.Reader;
import java.util.*;

public class App {

    public static App Instance;
    private static EntityManager em;
    public App(){
        Instance = this;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }
    public static EntityManager getEntityManager() {
        return em;
    }
    public void run(){

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
                    BookManager.addBook();
                    break;
                case 2:
                    ReaderManager.addReader();
                    break;
                case 3:
                    BookManager.printBooks();
                    break;
                case 4:
                    ReaderManager.printReaders();
                    break;
                case 5:
                    BookExchangeManager.giveBook();
                    break;
                case 6:
                    BookExchangeManager.printHistories();
                    break;
                case 7:
                {
                    BookExchangeManager.printRankedBookList();
                }
                break;
                case 8:
                {
                    BookExchangeManager.printMostReadingReaderList();
                }
                break;

                default:
                    System.out.println("Reselect a true number");
            }
            System.out.println("-------");




        }while(loop);
    }
}
