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
public class Person {

    String NIC;
    String firstName;
    String lastName;
    String address;
    String contactNumber;
    String gender;
    String DOB;

    Person() {
    }

    Person(ResultSet rs) {
        try {
            NIC = rs.getString("NIC");
            firstName = rs.getString("firstName");
            lastName = rs.getString("lastName");
            address = rs.getString("address");
            contactNumber = rs.getString("contactNumber");
            gender = rs.getString("gender");
            DOB = rs.getString("DOB");
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    void setPerson(String nic) {
        try {
            DBConnect DbcRecept = new DBConnect();
                        DbcRecept.connectdb();

            String getPerson = "SELECT * FROM Person WHERE NIC=?;";
             PreparedStatement getPersonPrepStat;
            getPersonPrepStat = DbcRecept.con.prepareStatement(getPerson);
            getPersonPrepStat.setString(1,nic);
            DbcRecept.rs = getPersonPrepStat.executeQuery();
            DbcRecept.rs.next();
            NIC = DbcRecept.rs.getString("NIC");
            firstName = DbcRecept.rs.getString("firstName");
            lastName = DbcRecept.rs.getString("lastName");
            address = DbcRecept.rs.getString("address");
            contactNumber = DbcRecept.rs.getString("contactNumber");
            gender = DbcRecept.rs.getString("gender");
            DOB = DbcRecept.rs.getString("DOB");
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
