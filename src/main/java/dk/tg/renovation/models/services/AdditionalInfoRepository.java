package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.AddtionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdditionalInfoRepository implements ICrud<AddtionalInfo> {

    @Autowired
    private JdbcTemplate jdbc;



    @Override
    public List<AddtionalInfo> readAll() {
        return null;
    }

    @Override
    public AddtionalInfo read(int cvr) {
        return null;
    }

    // create metoden der bliver kaldt fra homecontrollleren.
    // Får et company ind i constructoren og får fat i værdierne med get metoder.
    // Indsætter ind i databasen med MySql
    @Override
    public void create(AddtionalInfo addtionalInfo) {
        jdbc.update("INSERT INTO renovationdb.additionalInfo(settlement, comments, fk_CVR) " +
                "VALUES('" + addtionalInfo.getSettlement() + "', '" + addtionalInfo.getComments() + "', '" + addtionalInfo.getFkCVR() + "') ");

    }

    @Override
    public void delete(int cvr) {
        jdbc.update("DELETE FROM renovationdb.additionalinfo WHERE fk_CVR = " + cvr);
    }

    @Override
    public void update(AddtionalInfo addtionalInfo) {

    }

}
