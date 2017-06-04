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
import net.proteanit.sql.DbUtils;

/**
 *
 * @author oshan
 */
public class Receptionist {

    public ResultSet getPatientsTestChargesDetails(Patient thePatient) {

        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        String checkPatient = "SELECT Date,DrID,Description,charges FROM drPatient,Test WHERE drPatient.patientID=? and drPatient.drPatientID=Test.drPatientID;";
        PreparedStatement checkPatientPrepStat;
        try {
            checkPatientPrepStat = DbcRecept.con.prepareStatement(checkPatient);
            checkPatientPrepStat.setInt(1, thePatient.patientID);
            DbcRecept.rs = checkPatientPrepStat.executeQuery();
            return DbcRecept.rs;

        } catch (SQLException ex) {
            Logger.getLogger(ReceptView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public ResultSet getPatientsTreatmentChargesDetails(Patient thePatient) {

        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        String checkPatient = "SELECT Date,DrID,Description,charges FROM drPatient,Treatment WHERE drPatient.patientID=? and drPatient.drPatientID=Treatment.drPatientID;";
        PreparedStatement checkPatientPrepStat;
        try {
            checkPatientPrepStat = DbcRecept.con.prepareStatement(checkPatient);
            checkPatientPrepStat.setInt(1, thePatient.patientID);
            DbcRecept.rs = checkPatientPrepStat.executeQuery();
            return DbcRecept.rs;

        } catch (SQLException ex) {
            Logger.getLogger(ReceptView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public int getPatientsTotalTreatmentCharges(Patient thePatient) {

        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        String checkPatient = "SELECT SUM(charges) FROM drPatient,Treatment WHERE drPatient.patientID=? and drPatient.drPatientID=Treatment.drPatientID;";
        PreparedStatement checkPatientPrepStat;
        try {
            checkPatientPrepStat = DbcRecept.con.prepareStatement(checkPatient);
            checkPatientPrepStat.setInt(1, thePatient.patientID);
            DbcRecept.rs = checkPatientPrepStat.executeQuery();
            if (DbcRecept.rs.next()) {
                return DbcRecept.rs.getInt("SUM(charges)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReceptView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    public int getPatientsTotalTestCharges(Patient thePatient) {

        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        String checkPatient = "SELECT SUM(charges) FROM drPatient,Test WHERE drPatient.patientID=? and drPatient.drPatientID=Test.drPatientID;";
        PreparedStatement checkPatientPrepStat;
        try {
            checkPatientPrepStat = DbcRecept.con.prepareStatement(checkPatient);
            checkPatientPrepStat.setInt(1, thePatient.patientID);
            DbcRecept.rs = checkPatientPrepStat.executeQuery();
            if (DbcRecept.rs.next()) {
                return DbcRecept.rs.getInt("SUM(charges)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReceptView.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    int HandlePayment(Patient thePatient, int Amount) {
        DBConnect DbcRecept = new DBConnect();
        DbcRecept.connectdb();
        thePatient.paidAmount+=Amount;                
        String checkPatient = "UPDATE Patient set paidAmount=? where patientID=?;";
        PreparedStatement checkPatientPrepStat;
        try {
            checkPatientPrepStat = DbcRecept.con.prepareStatement(checkPatient);
            checkPatientPrepStat.setInt(1, thePatient.paidAmount);
            checkPatientPrepStat.setInt(2, thePatient.patientID);
            checkPatientPrepStat.executeUpdate();
            return thePatient.paidAmount;
        } catch (SQLException ex) {
            Logger.getLogger(ReceptView.class.getName()).log(Level.SEVERE, null, ex);

        } 

        return -1;
    }

}
