package main.Entities;

import jakarta.persistence.Table;
import main.App;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyEntity<T> {
    public MyEntity(){
    }
    public void commit(){
        App.getEntityManager().getTransaction().begin();
        App.getEntityManager().persist(this);
        App.getEntityManager().getTransaction().commit();
    }

    public List<T> fetchAll(String tableName){
        List<T> list = App.getEntityManager().createQuery(String.format("SELECT b FROM %s b", tableName)).getResultList();
        return list;
    }
    public static List fetchAllStatic(String tableName){
        List list = App.getEntityManager().createQuery(String.format("SELECT b FROM %s b", tableName)).getResultList();
        return list;
    }
    public static List fetchAllStaticFiltered(String tableName, String field, String value){
        List list = App.getEntityManager().createQuery(String.format("SELECT b FROM %s b WHERE %s='%s'", tableName, field, value)).getResultList();
        return list;
    }
}
