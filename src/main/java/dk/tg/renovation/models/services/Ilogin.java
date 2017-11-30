package dk.tg.renovation.models.services;

import java.util.List;

public interface Ilogin<T> {

    public T logIn(String cName, String password);

}
