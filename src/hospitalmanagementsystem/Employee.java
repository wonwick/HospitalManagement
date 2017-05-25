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
public class Employee extends Person {

    int employeeID;
    String joinedDate;
    int type;

    Employee() {
    }

    Employee(ResultSet rs) {
        try {
            employeeID = rs.getInt("patientID");
            joinedDate = rs.getString("admittedDate");
            type = rs.getInt("dischargedDate");
            NIC = rs.getString("NIC");
            super.setPerson(NIC);
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setEmployee(int empID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();

            String getEmployeeSQL = "SELECT * FROM Employee WHERE employeeID=?;";
            PreparedStatement getEmployeePrepStat;
            getEmployeePrepStat = DbcRecept.con.prepareStatement(getEmployeeSQL);
            getEmployeePrepStat.setInt(1, empID);
            DbcRecept.rs = getEmployeePrepStat.executeQuery();
            DbcRecept.rs.next();
            NIC = DbcRecept.rs.getString("NIC");
            employeeID = DbcRecept.rs.getInt("employeeID");
            joinedDate = DbcRecept.rs.getString("joinedDate");
            type = DbcRecept.rs.getInt("type");

        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
