package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyRepository implements ICrud<Company> {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Company> readAll() {
        return null;
    }

    @Override
    public Company read(int cvr) {
        return null;
    }


    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(Company company) {
        jdbc.update("INSERT INTO renovationdb.company(company_name, CVR, p_number, pickup_adress) " +
                "VALUES('" + company.getCompanyName() + "', '" + company.getCvr() + "', '" +  company.getpNumber() + "', '" + company.getPickupAdress() + "') ");
    }

    @Override
    public void delete(int cvr) {
        jdbc.update("DELETE FROM renovationdb.company WHERE CVR = " + cvr);
    }

    @Override
    public void update(Company company) {

    }

}
