/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.DAOcontroller;

import cm.supptic.managerClass.Enseignant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noumodong
 */
public class DAOEnseignants {

    /**
     * Ajouter un enseingnant dans la base de donnée
     *
     * @param enseignant
     * @param myConn
     */
    public void addProduit(Enseignant enseignant, Connection myConn) throws SQLException {

        PreparedStatement stmnt = null;

        try {
            stmnt = myConn.prepareStatement("insert into enseignant values(?,?,?,?,?,?,?)");
            stmnt.setString(1, enseignant.getId_enseignant());
            stmnt.setString(2, enseignant.getNom_enseignant());
            stmnt.setString(3, enseignant.getPrenom_enseignant());
            stmnt.setString(4, enseignant.getAdresse_enseignant());
            stmnt.setString(5, enseignant.getEmail_enseignant());
            stmnt.setInt(6, enseignant.getTelephone());
            stmnt.setString(7, enseignant.getStatut_enseignant());

            stmnt.execute();
            System.out.println("Enregistrer avec succes ...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            myConn.close();
            stmnt.close();
        }
    }

    //Avoir la liste de tous les enseignants qui se trouvent dans la base de donnee
    /**
     *
     * @param myConn
     * @return
     * @throws Exception
     */
    public List<Enseignant> getAllEnseignants(Connection myConn) throws Exception {

        List<Enseignant> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from enseignant");
            while (myRs.next()) {
                Enseignant tempEnseignant = convertRowToEnseignant(myRs);
                list.add(tempEnseignant);
            }
            return list;

        } finally {
            myConn.close();
            myStmt.close();
            System.out.println("Voilà la liste des enseignants que tu cherchais :)");
        }
    }

    //Pour supprimer un enseignant
    /**
     *
     * @param id
     * @param myConn
     * @throws SQLException
     */
    public void effacerProduit(String id, Connection myConn) throws SQLException {
        PreparedStatement myStmt = null;

        String sqlExecute = "DELETE FROM enseignant WHERE id_enseignant ='" + id + "'";
        try {
            myStmt = myConn.prepareStatement(sqlExecute);
            myStmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            myConn.close();
            myStmt.close();
        }
    }

    //Modifier un enseignant
    /**
     *
     * @param enseignant
     * @param myConn
     * @throws SQLException
     */
    public void modifierEnseignant(Enseignant enseignant, Connection myConn) throws SQLException {
        PreparedStatement myStmt = null;

        String sqlExecute = "update enseignant set nom_enseignant=?,prenom_enseignant=?,add_enseignant=?,email_enseignant=?"
                + ",telephone_enseignant=?,statut_enseignant=? WHERE id_enseignant=?";
        try {
            myStmt = myConn.prepareStatement(sqlExecute);
            myStmt.setString(1, enseignant.getNom_enseignant());
            myStmt.setString(2, enseignant.getPrenom_enseignant());
            myStmt.setString(3, enseignant.getAdresse_enseignant());
            myStmt.setString(4, enseignant.getEmail_enseignant());
            myStmt.setInt(5, enseignant.getTelephone());
            myStmt.setString(6, enseignant.getStatut_enseignant());
            myStmt.setString(7, enseignant.getId_enseignant());

            myStmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            myConn.close();
            myStmt.close();
        }
    }

    //La methode qui retourne un objet enseignant apres avoir recu en parametre un resultset
    /**
     *
     * @param myRs
     * @return
     * @throws SQLException
     */
    private Enseignant convertRowToEnseignant(ResultSet myRs) throws SQLException {

        String id = myRs.getString("id_enseignant");
        String nom = myRs.getString("nom_enseignant");
        String prenom = myRs.getString("prenom_enseignant");
        String add = myRs.getString("add_enseignant");
        String email = myRs.getString("email_enseignant");
        int tel = myRs.getInt("telephone_enseignant");
        String sattut = myRs.getString("statut_enseignant");

        Enseignant tempEnseignant = new Enseignant(id, nom, prenom, add, email, tel, sattut);

        return tempEnseignant;
    }

    public int getINouveauId(Connection con) {

        Statement myStmt = null;
        ResultSet myRs = null;
        int id = 0;
        try {
            myStmt = con.createStatement();
            myRs = myStmt.executeQuery("SELECT * from identifiant");

            while (myRs.next()) {
                id = myRs.getInt("autoIcri");
            }
        } catch (Exception ex) {
        } finally {
            System.out.println("voila l'idi)");
        }

        return id;
    }

    public void mettreAjoutId   (int id, Connection myConn) throws SQLException {

        PreparedStatement stmnt = null;

        try {
            stmnt = myConn.prepareStatement("UPDATE identifiant SET `autoIncri`=? WHERE `autoIncri`='" + id + "'");
            stmnt.setInt(1, id + 1);
            stmnt.executeUpdate();
            System.out.println("nombres de vote incrementer avec succès.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

        }
    }
}
