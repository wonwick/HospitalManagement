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
public class Nurse extends Employee {

    String nurseID;
    String description;
    String wardID;

    Nurse() {
    }

    Nurse(ResultSet rs) {
        try {
            nurseID = rs.getString("drID");
            description = rs.getString("description");
            wardID = rs.getString("wardID");
            employeeID = rs.getInt("employeeID");
            super.setEmployee(employeeID);
            super.setPerson(super.NIC);

        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setNurse(int empID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();

            String getEmployeeSQL = "SELECT * FROM Nurse WHERE employeeID=?;";
            PreparedStatement getEmployeePrepStat;
            getEmployeePrepStat = DbcRecept.con.prepareStatement(getEmployeeSQL);
            getEmployeePrepStat.setInt(1, empID);
            DbcRecept.rs = getEmployeePrepStat.executeQuery();
            DbcRecept.rs.next();
            nurseID = DbcRecept.rs.getString("nurseID");
            description = DbcRecept.rs.getString("description");
            wardID = DbcRecept.rs.getString("wardID");
            employeeID = DbcRecept.rs.getInt("employeeID");
            super.setEmployee(empID);
            super.setPerson(NIC);

        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
