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

    boolean setPatient(int patientID) {
        try {
            DBConnect DbcRecept = new DBConnect();
            DbcRecept.connectdb();
            String getPatientDetailSQL = ("SELECT * FROM Person,Patient WHERE (Patient.patientID=? AND Patient.NIC=Person.NIC);");
            PreparedStatement getPatientDetailPrepStat;
            getPatientDetailPrepStat = DbcRecept.con.prepareStatement(getPatientDetailSQL);
            getPatientDetailPrepStat.setInt(1, patientID);
            DbcRecept.rs = getPatientDetailPrepStat.executeQuery();
            if (DbcRecept.rs.next()) {
                this.patientID = DbcRecept.rs.getInt("patientID");
                this.admittedDate = DbcRecept.rs.getString("admittedDate");
                this.dischargedDate = DbcRecept.rs.getString("dischargedDate");
                this.paidAmount = DbcRecept.rs.getInt("paidAmount");
                this.NIC = DbcRecept.rs.getString("NIC");
                this.guardianNIC = DbcRecept.rs.getString("guardianNIC");
                this.guardRel = DbcRecept.rs.getString("guardRel");
                this.DrID = DbcRecept.rs.getString("DrID");
                this.wardID = DbcRecept.rs.getString("wardID");
                super.setPerson(DbcRecept.rs.getString("Patient.NIC"));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    String getDoctorsName() {
        Doctor patientIncharge=new Doctor();
        patientIncharge.setDoctor(this.DrID);
        return patientIncharge.firstName+" "+patientIncharge.lastName;
 
    
    }
}
