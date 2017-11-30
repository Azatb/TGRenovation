package dk.tg.renovation.models.services;

import java.util.List;

public interface IAdmin<T> {

    List<T> readAll();

    void deleteCompany(int cvr);

}
