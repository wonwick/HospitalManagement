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
public class Doctor extends Employee {

    String drID;
    String description;
    String wardID;

    Doctor() {
    }

    Doctor(ResultSet rs) {
        try {
            drID = rs.getString("drID");
            description = rs.getString("description");
            wardID = rs.getString("wardID");
            employeeID = rs.getInt("employeeID");
            super.setEmployee(employeeID);
            super.setPerson(super.NIC);

        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setDoctor(int empID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();
            String getEmployeeSQL = "SELECT * FROM Doctor WHERE employeeID=?;";
            PreparedStatement getEmployeePrepStat;
            getEmployeePrepStat = DbcRecept.con.prepareStatement(getEmployeeSQL);
            getEmployeePrepStat.setInt(1, empID);
            DbcRecept.rs = getEmployeePrepStat.executeQuery();
            if (DbcRecept.rs.next()) {
                drID = DbcRecept.rs.getString("drID");
                description = DbcRecept.rs.getString("description");
                wardID = DbcRecept.rs.getString("wardID");
                employeeID = DbcRecept.rs.getInt("employeeID");
                super.setEmployee(empID);
                super.setPerson(NIC);
            } else {
                System.out.println("problem here at setDoctor by empId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setDoctor(String drID) {
        try {
            this.drID = drID;
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();
            String getEmployeeSQL = "SELECT * FROM Doctor WHERE drID=?;";
            PreparedStatement getEmployeePrepStat;
            getEmployeePrepStat = DbcRecept.con.prepareStatement(getEmployeeSQL);
            getEmployeePrepStat.setString(1, this.drID);
            DbcRecept.rs = getEmployeePrepStat.executeQuery();
            if (DbcRecept.rs.next()) {
                this.drID = DbcRecept.rs.getString("drID");
                this.description = DbcRecept.rs.getString("description");
                this.wardID = DbcRecept.rs.getString("wardID");
                this.employeeID = DbcRecept.rs.getInt("employeeID");
                super.setEmployee(this.employeeID);
                super.setPerson(NIC);
            } else {
                System.out.println("problem here at setDoctor by drID");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
