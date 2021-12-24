/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.benbarara.tpcomptebancaire.managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import ma.benbarara.tpcomptebancaire.ejb.GestionnaireCompte;
import ma.benbarara.tpcomptebancaire.entities.CompteBancaire;

/**
 *
 * @author Benbarara
 */
@Named(value = "compteDetail")
@ViewScoped
public class CompteDetail implements Serializable{
    private Long idCompte;
    private CompteBancaire compteBanquaire;
    /**
     * Creates a new instance of CompteDetail
     */
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    public CompteDetail() {
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompteBanquaire() {
        return compteBanquaire;
    }

    public void setCompteBanquaire(CompteBancaire compteBanquaire) {
        this.compteBanquaire = compteBanquaire;
    }
    
    public String update(){
        compteBanquaire = gestionnaireCompte.update(compteBanquaire);
        return "listeComptes?faces-redirect=true";
    }
    public void loadCompteBancaire() {
        this.compteBanquaire = gestionnaireCompte.getCompteBancaire(idCompte);
    }
   
}
