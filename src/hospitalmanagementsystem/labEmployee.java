/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oshan
 */
public class labEmployee {

    ResultSet RefreshJobs() {
        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        try {
            String refreshSql = "select  testID, date, drPatientID, description from Test WHERE result=\"\" ;";
            PreparedStatement refreshPrepStat;
            refreshPrepStat = DbcRecept.con.prepareStatement(refreshSql);
            return refreshPrepStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(labEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    void addResult(String result,int testID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();
            String addResultSql = "UPDATE Test set result=? where testID=? ;";
            PreparedStatement addResulPrepStat;
            addResulPrepStat = DbcRecept.con.prepareStatement(addResultSql);
            addResulPrepStat.setString(1, result);
            addResulPrepStat.setInt(2, testID);
            addResulPrepStat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(labEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
