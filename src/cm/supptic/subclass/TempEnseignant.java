/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.subclass;

import cm.supptic.managerClass.Enseignant;

/**
 *
 * @author Noumodong
 */
public class TempEnseignant {

    public static Enseignant tempEnseignant;

    public TempEnseignant() {
    }

    public TempEnseignant(Enseignant tempEnseignant) {
        this.tempEnseignant = tempEnseignant;
    }

    public Enseignant getTempEnseignant() {
        return tempEnseignant;
    }

    public void setTempEnseignant(Enseignant tempEnseignant) {
        this.tempEnseignant = tempEnseignant;
    }

}
