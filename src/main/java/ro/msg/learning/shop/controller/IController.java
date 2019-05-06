package ro.msg.learning.shop.controller;

import java.util.List;

public interface IController <E, I> {
    E getOne(I id);
    List<E> getAll();
    E save(E entity);
    E update(E entity);
    void remove(I id);
}
