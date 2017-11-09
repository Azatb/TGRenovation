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

    @Override
    public void create(Company company) {
        jdbc.update("INSERT INTO renovationdb.company(company_name, CVR, p_number, pickup_adress) " +
                "VALUES('" + company.getCompanyName() + "', '" + company.getCvr() + "', '" +  company.getpNumber() + "', '" + company.getPickupAdress() + "') ");
    }

    @Override
    public void delete(int cvr) {

    }

    @Override
    public void update(Company company) {

    }

}
