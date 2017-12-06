package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.AdditionalInfo;
import dk.tg.renovation.models.entities.Driver;
import dk.tg.renovation.models.entities.ModelClass;
import dk.tg.renovation.models.services.interfaces.Ilogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DriverRepository implements Ilogin<Driver> {


    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;


    @Override
    public Driver logIn(String un, String pw) {

        ArrayList<Driver> drivers = new ArrayList<Driver>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.driver");

        while (sqlRowSet.next()) {
            // indhold af sqlRowset ned i en arrayliste
            drivers.add(new Driver(sqlRowSet.getString("username"), sqlRowSet.getString("password"), sqlRowSet.getString("region")));
        }

        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getDriverUserName().equals(un) && drivers.get(i).getDriverPassword().equals(pw)) {
                return drivers.get(i);
            }
        }
        return null;

    }

    public ArrayList<ModelClass> checkRegion() {
        ArrayList<AdditionalInfo> ai = new ArrayList<>();




        ArrayList<ModelClass> mc = new ArrayList<>();

        return null;

    }

}
