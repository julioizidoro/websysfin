/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class OutrosLancamentoMB implements Serializable{
    
     public String novo(){
        return "cadOutrosLancamentos";
    }
    
    public String cancelar(){
        return "consOutrosLancamentos";
    }
    
}
