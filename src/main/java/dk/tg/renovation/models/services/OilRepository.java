package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Oil;
import dk.tg.renovation.models.services.interfaces.ICrud;
import dk.tg.renovation.models.services.interfaces.IReadAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OilRepository implements ICrud<Oil>, IReadAll<Oil> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;

    @Override
    public List<Oil> readAll() {

        ArrayList<Oil> oils = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM oil");

        while(sqlRowSet.next()){
            oils.add(new Oil(sqlRowSet.getString("size"), sqlRowSet.getInt("amount"),
                    sqlRowSet.getInt("fk_CVR")));
        }

        return oils;
    }


    @Override
    public ArrayList<Oil> read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM oil WHERE fk_CVR=" + cvr);
        ArrayList<Oil> oil = new ArrayList<>();


        while(sqlRowSet.next()){
            oil.add(new Oil(sqlRowSet.getString("size"), sqlRowSet.getInt("amount")));
        }


        return oil;
    }

    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(Oil oil) {
        jdbc.update("INSERT INTO oil(size, amount, fk_CVR) " +
                "VALUES('" + oil.getSize() + "', '" + oil.getAmount() + "', '" + oil.getFkCVR() + "') ");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM oil WHERE id = " + id);
    }

    @Override
    public void update(Oil oil, int id) {
        jdbc.update("UPDATE  oil SET size = '" + oil.getSize() + "', amount = '" + oil.getAmount() + "', fk_CVR = '" +
                oil.getFkCVR() + "' WHERE id = " + id);
    }

}
