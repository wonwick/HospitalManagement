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

/**
 *
 * @author oshan
 */
public class OtherEmployee extends Employee{
    String OtherID;
    String description;
    String section;
    String post;
    
    void setOtherEmployee(int empID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();
            String getEmployeeSQL = "SELECT * FROM OtherEmployee WHERE employeeID=?;";
            PreparedStatement getEmployeePrepStat;
            getEmployeePrepStat = DbcRecept.con.prepareStatement(getEmployeeSQL);
            getEmployeePrepStat.setInt(1, empID);
            DbcRecept.rs = getEmployeePrepStat.executeQuery();
            if (DbcRecept.rs.next()) {
                OtherID = DbcRecept.rs.getString("OtherID");
                description = DbcRecept.rs.getString("description");
                section = DbcRecept.rs.getString("section");
                post = DbcRecept.rs.getString("post");                
                employeeID = DbcRecept.rs.getInt("employeeID");
                super.setEmployee(empID);
                super.setPerson(NIC);
            } else {
                System.out.println("problem here at setOtherEmployee by empId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
