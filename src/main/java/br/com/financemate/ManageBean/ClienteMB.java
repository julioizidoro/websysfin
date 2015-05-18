/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.ClienteController;
import br.com.financemate.facade.ClienteFacade;
import br.com.financemate.facade.TipoPlanoContasFacede;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Tipoplanocontas;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String idTipoPlanoConta="0";
    private List<Tipoplanocontas> listarTipoPlanoContas;

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

    public String getIdTipoPlanoConta() {
        return idTipoPlanoConta;
    }

    public void setIdTipoPlanoConta(String idTipoPlanoConta) {
        this.idTipoPlanoConta = idTipoPlanoConta;
    }

    public List<Tipoplanocontas> getListarTipoPlanoContas() {
        return listarTipoPlanoContas;
    }

    public void setListarTipoPlanoContas(List<Tipoplanocontas> listarTipoPlanoContas) {
        this.listarTipoPlanoContas = listarTipoPlanoContas;
    }
    
    public void listarTipoPlanoContas() throws SQLException{
        TipoPlanoContasFacede tipoPlanoContasFacede = new TipoPlanoContasFacede();
        listarTipoPlanoContas = tipoPlanoContasFacede.listar();
        if (listarTipoPlanoContas==null){
            listarTipoPlanoContas = new ArrayList<Tipoplanocontas>();
        }
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
        return "consCliente";
    
    }  
    
    public String selecionarCliente(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        int idCliente=  Integer.parseInt(params.get("id_cliente"));
        cliente = listaClientes.get(idCliente);
        return pagina;
    }
    
    public String novo() throws SQLException{
        listarTipoPlanoContas();
        cliente = new Cliente();
        return "cadCliente";
    }
    public String cancelar(){
        return "consCliente";
    }
    public String salvar() throws SQLException{
        ClienteController clienteController = new ClienteController();
        TipoPlanoContasFacede tipoPlanoContasFacede = new TipoPlanoContasFacede();
        Tipoplanocontas tipo = tipoPlanoContasFacede.consultar(Integer.parseInt(idTipoPlanoConta));
        cliente.setTipoplanocontas(tipo);
        clienteController.salvar(cliente);
        cliente = new Cliente();
        gerarListaClientes();
        return "consCliente";
    }
    
    public String consultarTipoPlanoContas() throws SQLException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int idCliente = Integer.parseInt(params.get("idCliente"));
        if (idCliente > 0) {
            ClienteFacade clienteFacade = new ClienteFacade();
            cliente = clienteFacade.consultar(idCliente);
            if (cliente != null) {
                return "cadCliente";
            }
        }
        return null;
    }
    
    public String editar() throws SQLException{
        if (listaClientes!=null){
            for(int i=0;i<listaClientes.size();i++){
                if (listaClientes.get(i).isSelecionado()){
                    cliente = listaClientes.get(i);
                    listaClientes.get(i).setSelecionado(false);
                    listarTipoPlanoContas();
                    idTipoPlanoConta = String.valueOf(cliente.getTipoplanocontas().getIdtipoplanocontas());
                    i=100000;
                    return "cadCliente";
                }
            }
        }
        return  "";
    }
}
    

