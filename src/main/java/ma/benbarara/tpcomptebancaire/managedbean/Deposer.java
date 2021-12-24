/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.benbarara.tpcomptebancaire.managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Benbarara
 */
@Named(value = "deposer")
@RequestScoped
public class Deposer {

    /**
     * Creates a new instance of Deposer
     */
    public Deposer() {
    }
    
}
