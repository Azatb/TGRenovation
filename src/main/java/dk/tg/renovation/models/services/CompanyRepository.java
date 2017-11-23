package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Company;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyRepository implements ICrud<Company>, Ilogin<Company> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;

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
    public Company read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.company WHERE CVR=" + cvr);

        while(sqlRowSet.next()){
            return new Company(sqlRowSet.getString("company_name"), sqlRowSet.getString("password"),
                    sqlRowSet.getInt("CVR"), sqlRowSet.getInt("p_number"));
        }


        return new Company();
    }


    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(Company company) {
        jdbc.update("INSERT INTO renovationdb.company(company_name, password, CVR, p_number) " +
                "VALUES('" + company.getCompanyName() + "', '" + company.getPassword() + "', '" + company.getCvr() + "', '" +  company.getpNumber() + "') ");
    }

    @Override
    public void delete(int cvr) {
        jdbc.update("DELETE FROM renovationdb.company WHERE CVR = " + cvr);
    }

    @Override
    public void update(Company company) {
        
    }

    @Override
    public Company logIn(String companyName, String password){

        ArrayList<Company> companies = new ArrayList<Company>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.company");

        while(sqlRowSet.next()){
            // indhold af sqlRowset ned i en arrayliste
            companies.add(new Company(sqlRowSet.getString("company_name"), sqlRowSet.getString("password"), sqlRowSet.getInt("CVR"), sqlRowSet.getInt("p_number")));
        }


        for (int i=0; i<companies.size(); i++) {
            if(companies.get(i).getCompanyName().equals(companyName) && companies.get(i).getPassword().equals(password)) {
                return companies.get(i);
            }
        }
        return null;
    }
}
