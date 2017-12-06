package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Company;
import dk.tg.renovation.models.services.interfaces.ICrud;
import dk.tg.renovation.models.services.interfaces.Ilogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyRepository implements ICrud<Company>, Ilogin<Company> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;


    @Override
    public ArrayList<Company> read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.company WHERE CVR=" + cvr);
        ArrayList<Company> companies = new ArrayList<>();

        while(sqlRowSet.next()){
            companies.add(new Company(sqlRowSet.getString("company_name"), sqlRowSet.getString("password"),
                    sqlRowSet.getInt("CVR"), sqlRowSet.getInt("p_number")));
        }


        return companies;
    }


    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(Company company) {
        jdbc.update("INSERT INTO renovationdb.company(company_name, password, CVR, p_number) " +
                "VALUES('" + company.getCompanyName() + "', '" + company.getPassword() + "', '" + company.getCvr() + "', '" +  company.getpNumber() + "') ");
    }

    //Bliver ikke brugt, ligger nu i AdminRepository. Overvejer om den skal flyttes.
    @Override
    public void delete(int cvr) {
        jdbc.update("DELETE FROM renovationdb.company WHERE CVR = " + cvr);
    }

    //Denne metode bruges ikke, men skal implementeres i tilfælde af fejl i opretning eller
    @Override
    public void update(Company company,int id) {
        // bliver ikke brugt
        jdbc.update("UPDATE renovationdb.company SET company_name = '" + company.getCompanyName() + "', password = '" + company.getPassword() + "',  CVR = '" + company.getCvr() + "', p_number = '" +
                company.getpNumber() + "' WHERE CVR = " + company.getCvr());
    }

    @Override
    public Company logIn(String companyName, String password){

        ArrayList<Company> companies = new ArrayList<Company>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.company");

        while(sqlRowSet.next()){
            // indhold af sqlRowset ned i en arrayliste
            companies.add(new Company(sqlRowSet.getString("company_name"),
                                      sqlRowSet.getString("password"),
                                      sqlRowSet.getInt("CVR"),
                                      sqlRowSet.getInt("p_number")));
        }


        for (int i=0; i<companies.size(); i++) {
            if(companies.get(i).getCompanyName().equals(companyName)
               && companies.get(i).getPassword().equals(password)) {
                return companies.get(i);
            }
        }
        return null;
    }
}
