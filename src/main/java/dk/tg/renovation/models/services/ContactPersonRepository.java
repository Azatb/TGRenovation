package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.ContactPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactPersonRepository implements ICrud<ContactPerson> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;

    @Override
    public ArrayList<ContactPerson> read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.contact_person WHERE fk_CVR=" + cvr);
        //laver en arraylist
        ArrayList<ContactPerson> contactPersons = new ArrayList<>();


        while(sqlRowSet.next()){
            contactPersons.add(new ContactPerson(sqlRowSet.getString("name"), sqlRowSet.getInt("number"),
                    sqlRowSet.getString("pickup_adress")));
        }


        return contactPersons;
    }

    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(ContactPerson contactPerson) {
        jdbc.update("INSERT INTO renovationdb.contact_person(name, number, pickup_adress, fk_CVR) " +
                "VALUES('" + contactPerson.getName() + "', '" + contactPerson.getNumber() + "', '" + contactPerson.getPickupAdress() + "', '" + contactPerson.getFkCVR() + "') ");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM renovationdb.contact_person WHERE id= " + id);
    }

    @Override
    public void update(ContactPerson contactPerson, int id) {
        jdbc.update("UPDATE renovationdb.contact_person SET name = '" + contactPerson.getName() + "', number = '" + contactPerson.getNumber() + "',  pickup_adress = '" + contactPerson.getPickupAdress() + "', fk_CVR = '" +
                contactPerson.getFkCVR() + "' WHERE id = " + id);
    }

}
