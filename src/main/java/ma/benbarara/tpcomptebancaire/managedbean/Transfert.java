/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.benbarara.tpcomptebancaire.managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ma.benbarara.tpcomptebancaire.ejb.GestionnaireCompte;
import ma.benbarara.tpcomptebancaire.entities.CompteBancaire;
import static ma.benbarara.tpcomptebancaire.managedbean.Util.message;

/**
 *
 * @author Benbarara
 */
@Named(value = "tranfert")
@RequestScoped
public class Transfert implements Serializable {

    private Long idsrc;
    private Long iddest;
    private CompteBancaire src;
    private CompteBancaire dest;
    private int mnt;
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of tranfert
     */
    public Transfert() {
    }

    public String transctionBancaire() {
        src = gestionnaireCompte.findById(idsrc);
        dest = gestionnaireCompte.findById(iddest);
        if (src != null && dest != null && src.getSolde() >= mnt) {
            gestionnaireCompte.transfert(src, dest, mnt);
            Util.addFlashInfoMessage("Transfert de " + src.getNom() + " vers "
                    + dest.getNom()
                    + " pour un montant de " + mnt + " correctement effectué");
            return "listeComptes?faces-redirect=true";
        }
        if (src == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
        }
        if (dest == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
        }
        if (src != null && src.getSolde() <= mnt) {
            Util.messageErreur("Montant insuffisant !", "Montant insuffisant  !", "form:montant");
        }
        return null;

    }

    public String check() {
        src = gestionnaireCompte.findById(idsrc);
        dest = gestionnaireCompte.findById(iddest);
       
        if (src == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
        }else
        {
            Util.messageErreur("compte de M/Mme " + src.getNom() +"avec un solde de " + src.getSolde(), "compte existe !", "form:source");
        }
        if (dest == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
        }else{
            Util.messageErreur("compte de M/Mme " + dest.getNom() +"avec un solde de " + dest.getSolde(), "compte existe !", "form:destination");
        }
        if (src != null && src.getSolde() <= mnt){
            Util.messageErreur("Montant insuffisant le maximum et "+src.getSolde()+" !", "Montant insuffisant  !", "form:montant");
        }else {
             Util.messageErreur("Montant est suffisant por cette transaction !", "Montant suffisant  !", "form:montant");
        }
        return null;

    }

    public Long getIdsrc() {
        return idsrc;
    }

    public void setIdsrc(Long idsrc) {
        this.idsrc = idsrc;
    }

    public Long getIddest() {
        return iddest;
    }

    public void setIddest(Long iddest) {
        this.iddest = iddest;
    }

    public CompteBancaire getSrc() {
        return src;
    }

    public void setSrc(CompteBancaire src) {
        this.src = src;
    }

    public CompteBancaire getDest() {
        return dest;
    }

    public void setDest(CompteBancaire dest) {
        this.dest = dest;
    }

    public int getMnt() {
        return mnt;
    }

    public void setMnt(int mnt) {
        this.mnt = mnt;
    }

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }

    public void setGestionnaireCompte(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }

}
