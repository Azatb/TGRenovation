package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Admin;
import dk.tg.renovation.models.entities.Chauffør;
import dk.tg.renovation.models.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRepository implements Ilogin<Admin>, ICrud<Chauffør>, IAdmin<Company> {

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
    public List<Chauffør> read(int cvr) {
        return null;
    }

    @Override
    public void create(Chauffør chauffør) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Chauffør chauffør, int id) {

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
        jdbc.update("DELETE FROM renovationdb.company WHERE CVR = " + cvr);
    }


}
