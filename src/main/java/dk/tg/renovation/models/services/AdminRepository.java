package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Admin;
import dk.tg.renovation.models.entities.Driver;
import dk.tg.renovation.models.entities.Company;
import dk.tg.renovation.models.services.interfaces.IAdmin;
import dk.tg.renovation.models.services.interfaces.ICrud;
import dk.tg.renovation.models.services.interfaces.IReadAll;
import dk.tg.renovation.models.services.interfaces.Ilogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRepository implements Ilogin<Admin>, ICrud<Driver>, IAdmin<Company>, IReadAll<Company> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;

    @Override
    public Admin logIn(String un, String pw) {
        Admin admin = new Admin("tgadmin", "8g1uiro4k21a");
        if (un.equals(admin.getUsername()) && pw.equals(admin.getPassword())) {
            return admin;

        }
        return null;
    }

    @Override
    public List<Driver> read(int id) {

    //her bruger vi ikke parameteren fordi vi gerne vil ha alt fra databasen
        ArrayList<Driver> drivers = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.driver");

        while(sqlRowSet.next()){
            drivers.add(new Driver(sqlRowSet.getString("username"), sqlRowSet.getString("password"),
                    sqlRowSet.getString("region"), sqlRowSet.getInt("id")));
        }

        return drivers;
    }

    @Override
    public void create(Driver driver) {
        jdbc.update("INSERT INTO renovationdb.driver(username, password, region) " +
                "VALUES('" + driver.getDriverUserName() + "', '" + driver.getDriverPassword() + "', '" +  driver.getDriverRegion() + "') ");
    }

    @Override
    public void delete(int id) {
        jdbc.update("DELETE FROM renovationdb.driver WHERE id= " + id);
    }

    @Override
    public void update(Driver driver, int id) {
        jdbc.update("UPDATE renovationdb.driver SET username = '" + driver.getDriverUserName() + "', password = '" + driver.getDriverPassword() + "',  region = '" + driver.getDriverRegion() + "' WHERE id = " + id);
    }


    @Override
    public List<Company> readAll() {
        ArrayList<Company> companies = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.company");

        while(sqlRowSet.next()){
            companies.add(new Company(sqlRowSet.getString("company_name"), sqlRowSet.getString("password"),
                    sqlRowSet.getInt("CVR"), sqlRowSet.getInt("p_number")));
        }

        return companies;
    }




    @Override
    public void deleteCompany(int cvr) {
        jdbc.update("DELETE FROM renovationdb.contact_person WHERE fk_CVR = " + cvr);
        jdbc.update("DELETE FROM renovationdb.oil WHERE fk_CVR = " + cvr);
        jdbc.update("DELETE FROM renovationdb.additionalinfo WHERE fk_CVR = " + cvr);
        jdbc.update("DELETE FROM renovationdb.company WHERE CVR = " + cvr);
    }


}
