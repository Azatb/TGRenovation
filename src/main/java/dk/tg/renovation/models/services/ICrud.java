package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Company;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface ICrud<T> {

    List<T> readAll();

    T read(int cvr);

    void create(T t);

    void delete(int cvr);

    void update(T t);
}
