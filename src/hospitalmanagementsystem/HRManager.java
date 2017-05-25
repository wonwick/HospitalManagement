/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author oshan
 */
public class HRManager extends Employee {

    void fetchAttendanceDetailsFromTo(String StartingDate, String EndingDate,JTable attendanceTable) {
      
        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        String showAttendanceSQL = "select employeeID,count(Date) from Attendance where Date(date)>=? and Date(date)<=? group by employeeID;";
        PreparedStatement selctAttendancePrepStat;
        /**/

        try {
            selctAttendancePrepStat = DbcRecept.con.prepareStatement(showAttendanceSQL);
            selctAttendancePrepStat.setString(1, StartingDate);
            selctAttendancePrepStat.setString(2, EndingDate);

            DbcRecept.rs = selctAttendancePrepStat.executeQuery();

            attendanceTable.setModel(DbUtils.resultSetToTableModel(DbcRecept.rs));

        } catch (SQLException ex) {
            Logger.getLogger(ReceptView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
