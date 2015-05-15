/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.model.Cliente;
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
    @Inject ClienteMB clienteMB;
    
    public String contasPagar(){
        clienteMB.setCliente(null);
        return "consConPagar";
    }
    
    public String contasReceber(){
        clienteMB.setCliente(null);
        return "consConReceber";
    }
    
    public String outrosLancamento(){
        clienteMB.setCliente(null);
        return "consOutrosLancamentos";
    }
    public String tipoPlanoContas(){
        clienteMB.setCliente(null);
        return "consTipoPlanoConta";
    }
    
    public String vendas(){
        clienteMB.setCliente(null);
        return "consVendas";
    }
    
    public String home(){
        clienteMB.setCliente(null);
        return "principal";
    }
    
    public String relatorioContasPagar(){
        clienteMB.setCliente(new Cliente());
        return "relContasPagar";
    } 
}
