/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.benbarara.tpcomptebancaire.managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import ma.benbarara.tpcomptebancaire.ejb.GestionnaireCompte;
import ma.benbarara.tpcomptebancaire.entities.CompteBancaire;
import ma.benbarara.tpcomptebancaire.entities.OperationBancaire;

/**
 *
 * @author Benbarara
 */
@Named(value = "historique")
@RequestScoped
public class Historique {
    List<OperationBancaire> operation;
    private Long id;
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    public Historique() {}
    public List<OperationBancaire> getOprations(){
        CompteBancaire c = gestionnaireCompte.findById(id);
        if(c!=null){
            operation =c.getOperations();
            return operation;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
