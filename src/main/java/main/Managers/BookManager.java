package main.Managers;

import main.App;
import main.Entities.BookEntity;

import java.util.List;
import java.util.Scanner;

public class BookManager {
    private static Scanner scanner = new Scanner(System.in);
    public static void addBook(){
        System.out.print("Имя книги: ");
        String bookName = scanner.nextLine();
        System.out.print("Год выпуска: ");
        int publishedYear = scanner.nextInt(); scanner.nextLine();
        //System.out.print("Сколько авторов: ");
//                    int authorCount = scanner.nextInt();
//                    Author[] authors = new Author[authorCount];
//                    for(int i = 0; i < authorCount; i++){
//                        System.out.println("Автор " + (i+1) + "й:");
//                        System.out.println("    Введите имя автора:");
//                        String fname = scanner.next();
//                        System.out.println("    Введите фамилию автора:");
//                        String lname = scanner.next();
//                        authors[i] = new Author(fname, lname);
//
//                    }
//                    System.out.println("Авторы:");
//                    for(int i = 0; i < authorCount; i++){
//                        System.out.println((i+1)+".) "+ authors[i].firstname + " " + authors[i].lastname);
//                    }
        System.out.print("Количество на складе: ");
        int count = scanner.nextInt();scanner.nextLine();
        BookEntity ent = new BookEntity();
        ent.setTitle(bookName);
        ent.setPublishedYear(publishedYear);
        ent.setCount(count);
        ent.setAuthors("");
        ent.setQuantity(count);
        App.getEntityManager().getTransaction().begin();
        App.getEntityManager().persist(ent);
        App.getEntityManager().getTransaction().commit();
        ent.commit();
    }
    public static void printBooks(){
        List<BookEntity> ents = (List<BookEntity>)BookEntity.fetchAllStatic("BookEntity");
        for(BookEntity e : ents){
            System.out.printf("%s.) %s (%s) %s/%s%n", e.getId(), e.getTitle(), e.getPublishedYear(), e.getQuantity(), e.getCount());
        }
    }
}
