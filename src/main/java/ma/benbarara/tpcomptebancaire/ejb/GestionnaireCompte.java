/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.benbarara.tpcomptebancaire.ejb;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ma.benbarara.tpcomptebancaire.entities.CompteBancaire;
import ma.benbarara.tpcomptebancaire.entities.OperationBancaire;

/**
 *
 * @author Benbarara
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "@admin123papa", // mot de passe 
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    public CompteBancaire getCompteBancaire(Long idcmpt) {
        return em.find(CompteBancaire.class, idcmpt);
    }

    public CompteBancaire update(CompteBancaire cmptB) {
        return em.merge(cmptB);
    }

    public CompteBancaire findById(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }

    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }

    public void supprimer(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    public void transfert(CompteBancaire src, CompteBancaire dest, int mnt) {
        src.retirer(mnt);
        dest.deposer(mnt);
        update(src);
        update(dest);
    }

}
