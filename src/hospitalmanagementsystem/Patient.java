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
public class Patient extends Person {

    int patientID;
    String admittedDate;
    String dischargedDate;
    int paidAmount;
    String guardianNIC;
    String guardRel;
    String DrID;
    String wardID;

    Patient() {
    }

    Patient(ResultSet rs) {
        try {
            patientID = rs.getInt("patientID");
            admittedDate = rs.getString("admittedDate");
            dischargedDate = rs.getString("dischargedDate");
            paidAmount = rs.getInt("paidAmount");
            NIC = rs.getString("NIC");
            guardianNIC = rs.getString("guardianNIC");
            guardRel = rs.getString("guardRel");
            DrID = rs.getString("DrID");
            wardID = rs.getString("wardID");
            super.setPerson(NIC);
        } catch (SQLException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setPatient(int patientID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();
            String getPatientDetailSQL = ("SELECT * FROM Person,Patient WHERE (Patient.patientID=? AND Patient.NIC=Person.NIC);");
            PreparedStatement getPatientDetailPrepStat;
            getPatientDetailPrepStat = DbcRecept.con.prepareStatement(getPatientDetailSQL);
            getPatientDetailPrepStat.setInt(1, patientID);
            DbcRecept.rs = getPatientDetailPrepStat.executeQuery();
            DbcRecept.rs.next();
            DrID=DbcRecept.rs.getString("DrID");
            super.setPerson(DbcRecept.rs.getString("Patient.NIC"));
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String getDoctorsName() {
        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        String drName = null;
        try {
            String getDrNameSQL = ("select Firstname,lastName from Person,Employee,Doctor where Doctor.drID=? and Employee.employeeID=Doctor.employeeID and Employee.NIC=Person.NIC;");
            PreparedStatement getDrNamePrepStat;
            getDrNamePrepStat = DbcRecept.con.prepareStatement(getDrNameSQL);
            getDrNamePrepStat.setString(1, DrID);
            DbcRecept.rs = getDrNamePrepStat.executeQuery();
            DbcRecept.rs.next();
            drName = DbcRecept.rs.getString(1) + " " + DbcRecept.rs.getString(2);

        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);

        }
        return drName;
    }
}
