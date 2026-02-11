package dao;

import java.util.List;

public interface IDao<T> {
    T findById(int id) throws Exception;
    List<T> findAll() throws Exception;
    int insert(T obj) throws Exception;
    boolean update(T obj) throws Exception;
    boolean delete(int id) throws Exception;
}