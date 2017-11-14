package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.ContactPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPersonRepository implements ICrud<ContactPerson> {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<ContactPerson> readAll() {
        return null;
    }

    @Override
    public ContactPerson read(int cvr) {
        return null;
    }

    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(ContactPerson contactPerson) {
        jdbc.update("INSERT INTO renovationdb.contact_person(name, number, fk_CVR) " +
                "VALUES('" + contactPerson.getName() + "', '" + contactPerson.getNumber() + "', '" + contactPerson.getFkCVR() + "') ");
    }

    @Override
    public void delete(int cvr) {
        jdbc.update("DELETE FROM renovationdb.contact_person WHERE fk_CVR = " + cvr);
    }

    @Override
    public void update(ContactPerson contactPerson) {

    }

    @Override
    public ContactPerson getCompany(String companyName, String password) {
        return null;
    }
}
