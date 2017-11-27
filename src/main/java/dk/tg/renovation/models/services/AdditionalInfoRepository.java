package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.AdditionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class AdditionalInfoRepository implements ICrud<AdditionalInfo> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;



    @Override
    public List<AdditionalInfo> readAll() {
        return null;
    }

    @Override
    public ArrayList<AdditionalInfo> read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.additionalinfo WHERE fk_CVR=" + cvr);
        ArrayList<AdditionalInfo> additionalInfo = new ArrayList<>();


        while (sqlRowSet.next()) {
            additionalInfo.add(new AdditionalInfo(sqlRowSet.getString("settlement"), sqlRowSet.getString("comments")));
        }


        return additionalInfo;
    }
        // create metoden der bliver kaldt fra homecontrollleren.
        // Får et company ind i constructoren og får fat i værdierne med get metoder.
        // Indsætter ind i databasen med MySql
        @Override
        public void create (AdditionalInfo additionalInfo){
            jdbc.update("INSERT INTO renovationdb.additionalInfo(settlement, comments, fk_CVR) " +
                    "VALUES('" + additionalInfo.getSettlement() + "', '" + additionalInfo.getComments() + "', '" + additionalInfo.getFkCVR() + "') ");

        }

        @Override
        public void delete (int cvr){
            jdbc.update("DELETE FROM renovationdb.additionalinfo WHERE fk_CVR = " + cvr);
        }

        @Override
        public void update (AdditionalInfo additionalInfo){
            jdbc.update("UPDATE renovationdb.additionalinfo SET settlement = '" + additionalInfo.getSettlement() + "', comments = '" + additionalInfo.getComments() + "', fk_CVR = '" +
                    additionalInfo.getFkCVR() + "' WHERE fk_CVR = " + additionalInfo.getFkCVR());
        }
}
