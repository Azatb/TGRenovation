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
    public ArrayList<AdditionalInfo> read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM renovationdb.additionalinfo WHERE fk_CVR=" + cvr);
        ArrayList<AdditionalInfo> additionalInfo = new ArrayList<>();


        while (sqlRowSet.next()) {
            additionalInfo.add(new AdditionalInfo(sqlRowSet.getString("settlement"), sqlRowSet.getString("comments"), sqlRowSet.getString("weekday"), sqlRowSet.getString("region"), sqlRowSet.getInt("fk_CVR"), sqlRowSet.getInt("id")));
        }


        return additionalInfo;
    }
        // create metoden der bliver kaldt fra homecontrollleren.
        // Får et company ind i constructoren og får fat i værdierne med get metoder.
        // Indsætter ind i databasen med MySql
        @Override
        public void create (AdditionalInfo additionalInfo){
            jdbc.update("INSERT INTO renovationdb.additionalInfo(settlement, comments, weekday, region, fk_CVR) " +
                    "VALUES('" + additionalInfo.getSettlement() + "', '" + additionalInfo.getComments() + "', '" + additionalInfo.getWeekDay() + "', '" + additionalInfo.getRegion() + "', '" + additionalInfo.getFkCVR() + "') ");

        }

        @Override
        public void delete (int id){
            jdbc.update("DELETE FROM renovationdb.additionalinfo WHERE id = " + id);
        }

        @Override
        public void update (AdditionalInfo additionalInfo, int id){
            jdbc.update("UPDATE renovationdb.additionalinfo SET settlement = '" + additionalInfo.getSettlement() + "', comments = '" + additionalInfo.getComments() + "', comments = '" + additionalInfo.getWeekDay() + "', comments = '" + additionalInfo.getRegion() + "', fk_CVR = '" +
                    additionalInfo.getFkCVR() + "' WHERE id = " + id);
        }
}