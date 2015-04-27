/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */

@Named
@SessionScoped
public class MenuMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    
    public String contasPagar(){
        return "consConPagar";
    }
    
    public String contasReceber(){
        return "consConReceber";
    }
    
    public String OutrosLancamento(){
        return "consOutrosLancamentos";
    }
    
    public String Vendas(){
        return "consVendas";
    }
    
    public String home(){
        return "principal";
    }
    
    public String relatorioContasPagar(){
        return "relContasPagar";
    }
    
}
