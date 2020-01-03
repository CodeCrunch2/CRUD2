package main.java.com.mkudryavtsev.crud2.repository;

import java.util.List;

public interface GenericRepository<T, ID>  {
    void save(T t);
    void delete(T t);
    void update(T t);
    List<T> getAll();
    ID getNextID();
    T getById(ID id);
}
