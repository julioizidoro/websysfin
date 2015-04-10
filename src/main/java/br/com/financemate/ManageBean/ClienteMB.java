/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.ClienteController;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contaspagar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */

@Named("ClienteMB")
@SessionScoped
public class ClienteMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    private Cliente cliente;
    private String nomeCliente;
    private List<Cliente> listaClientes;
    private String pagina;

    public ClienteMB() {
        cliente = new Cliente();
    }
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public List<Cliente> getListaClientes() {
       if (listaClientes==null){
            gerarListaClientes();
        }
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
    public void gerarListaClientes() {
        ClienteController clienteController = new ClienteController();
        if (nomeCliente == null) {
            nomeCliente = "";
        }
        listaClientes = clienteController.listar(nomeCliente);
        if (listaClientes == null) {
            listaClientes = new ArrayList<Cliente>();
        }
    }
    
    public String pesquisarNome(){
        
        gerarListaClientes();
        return "selecionarUnidade";
    
    }  
    
    public String selecionarCliente(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        int idCliente=  Integer.parseInt(params.get("id_cliente"));
        cliente = listaClientes.get(idCliente);
        return pagina;
    }
}
    

