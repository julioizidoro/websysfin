/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.ClienteController;
import br.com.financemate.Controller.UsuarioController;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named("UsuarioLogadoBean")
@SessionScoped
public class UsuarioLogadoBean implements Serializable{
    
    private Usuario usuario;
    private Cliente cliente;
    private int tipoCliente;

    public UsuarioLogadoBean() {
        this.usuario = usuario;
    }
    
    
    
    
    public Usuario getUsuario() {
        if (usuario==null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    
    public String validarUsuario(){
        if ((usuario.getLogin()!=null) && (usuario.getSenha()==null)){
            erroLogin("Erro de Login");
        }else{
                UsuarioController  usuarioController = new UsuarioController();
            usuario = usuarioController.consultar(usuario.getLogin(), usuario.getSenha());
            if (usuario==null){
                erroLogin("Acesso Negado");
            }else {
                if (usuario.getCliente()>0){
                    ClienteController clienteController = new ClienteController();
                    cliente = clienteController.consultar(usuario.getCliente());
                    tipoCliente=1;
                }else {
                    cliente = null;
                    tipoCliente=0;
                }
                return "principal";
            }
        }
        usuario = new Usuario();
        return "";
    }
    
    public void erroLogin(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, ""));
    }
    
}
