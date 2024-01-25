package main.Managers;

import main.Entities.ReaderEntity;

import java.util.List;
import java.util.Scanner;

public class ReaderManager {
    private static Scanner scanner = new Scanner(System.in);
    public static void addReader(){
        System.out.println("    Введите имя: ");
        String fname = scanner.next();
        System.out.println("    Введите фамилию: ");
        String lname = scanner.next();
        System.out.println("Год рождения: ");
        int bornYear = scanner.nextInt();
        System.out.println("    Введите номер телефона: ");
        String phoneNum = scanner.next();
        ReaderEntity ent = new ReaderEntity();
        ent.setFname(fname);
        ent.setLname(lname);
        ent.setBornYear(bornYear);
        ent.setPhoneNumber(phoneNum);
        ent.commit();
    }
    public static void printReaders(){
        List<ReaderEntity> ents = (List<ReaderEntity>)ReaderEntity.fetchAllStatic("ReaderEntity");
        for (ReaderEntity reader : ents) {
            String stringAuthors = "";
//                            for(int z = 0; z < readerManager.readers.length; z++){
//                                if(readerManager.readers[z] != null)
//                                    stringAuthors += " " + readerManager.readers[z].toString() + ",";
//                            }
            System.out.println(reader.toString() + " " + stringAuthors);
        }
    }
}
