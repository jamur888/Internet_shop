package dao;


import java.util.List;

public interface Dao<T> {

    T read(Long id);

    void create(T t);

    void update(T t);

    void delete(T t);

    List<T> getAll();


}
