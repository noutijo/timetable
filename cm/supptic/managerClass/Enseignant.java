/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.managerClass;

/**
 *
 * @author Noumodong
 */
public class Enseignant {

    private String id_enseignant;
    private String nom_enseignant;
    private String prenom_enseignant;
    private String adresse_enseignant;
    private String email_enseignant;
    private int telephone;
    private String statut_enseignant;

    public Enseignant(String id_enseignant, String nom_enseignant, String prenom_enseignant, String adresse_enseignant, String email_enseignant, int telephone, String statut_enseignant) {
        this.id_enseignant = id_enseignant;
        this.nom_enseignant = nom_enseignant;
        this.prenom_enseignant = prenom_enseignant;
        this.adresse_enseignant = adresse_enseignant;
        this.email_enseignant = email_enseignant;
        this.telephone = telephone;
        this.statut_enseignant = statut_enseignant;

    }

    public Enseignant(String nom_enseignant, String prenom_enseignant, String adresse_enseignant, String email_enseignant, int telephone, String statut_enseignant, String code_categorie) {
        this.nom_enseignant = nom_enseignant;
        this.prenom_enseignant = prenom_enseignant;
        this.adresse_enseignant = adresse_enseignant;
        this.email_enseignant = email_enseignant;
        this.telephone = telephone;
        this.statut_enseignant = statut_enseignant;
    }

    public Enseignant(String nom_enseignant, String prenom_enseignant, String email_enseignant) {
        this.nom_enseignant = nom_enseignant;
        this.prenom_enseignant = prenom_enseignant;
        this.email_enseignant = email_enseignant;

    }

    public String getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(String id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public String getNom_enseignant() {
        return nom_enseignant;
    }

    public void setNom_enseignant(String nom_enseignant) {
        this.nom_enseignant = nom_enseignant;
    }

    public String getPrenom_enseignant() {
        return prenom_enseignant;
    }

    public void setPrenom_enseignant(String prenom_enseignant) {
        this.prenom_enseignant = prenom_enseignant;
    }

    public String getAdresse_enseignant() {
        return adresse_enseignant;
    }

    public void setAdresse_enseignant(String adresse_enseignant) {
        this.adresse_enseignant = adresse_enseignant;
    }

    public String getEmail_enseignant() {
        return email_enseignant;
    }

    public void setEmail_enseignant(String email_enseignant) {
        this.email_enseignant = email_enseignant;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getStatut_enseignant() {
        return statut_enseignant;
    }

    public void setStatut_enseignant(String statut_enseignant) {
        this.statut_enseignant = statut_enseignant;
    }

}
