package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.AdditionalInfo;
import dk.tg.renovation.models.services.interfaces.ICrud;
import dk.tg.renovation.models.services.interfaces.IReadAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class AdditionalInfoRepository implements ICrud<AdditionalInfo>, IReadAll<AdditionalInfo> {

    @Autowired
    private JdbcTemplate jdbc;
    private SqlRowSet sqlRowSet;

    @Override
    public List<AdditionalInfo> readAll() {

        ArrayList<AdditionalInfo> additionalInfos = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM additionalinfo");

        while(sqlRowSet.next()){
            additionalInfos.add(new AdditionalInfo(sqlRowSet.getString("settlement"), sqlRowSet.getString("comments"),
                    sqlRowSet.getString("weekday"), sqlRowSet.getString("region"), sqlRowSet.getInt("fk_CVR")));
        }

        return additionalInfos;
    }

    @Override
    public ArrayList<AdditionalInfo> read(int cvr) {

        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM additionalinfo WHERE fk_CVR=" + cvr);
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
            jdbc.update("INSERT INTO additionalInfo(settlement, comments, weekday, region, fk_CVR) " +
                    "VALUES('" + additionalInfo.getSettlement() + "', '" + additionalInfo.getComments() + "', '" + additionalInfo.getWeekDay() + "', '" + additionalInfo.getRegion() + "', '" + additionalInfo.getFkCVR() + "') ");

        }

        @Override
        public void delete (int id){
            jdbc.update("DELETE FROM additionalinfo WHERE id = " + id);
        }

        @Override
        public void update (AdditionalInfo additionalInfo, int id){
            jdbc.update("UPDATE additionalinfo SET settlement = '" + additionalInfo.getSettlement() + "', comments = '" + additionalInfo.getComments() + "', weekday = '" + additionalInfo.getWeekDay() + "', region = '" + additionalInfo.getRegion() + "', fk_CVR = '" +
                    additionalInfo.getFkCVR() + "' WHERE id = " + id);
        }
}