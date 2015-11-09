/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean.cadastro;

import br.com.financemate.ManageBean.UsuarioLogadoBean;
import br.com.financemate.facade.TipoPlanoContasFacede;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Tipoplanocontas;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class TipoPlanoContasMB implements Serializable {

    private List<Tipoplanocontas> listarTipoPlanoContas;
    private Tipoplanocontas tipoplanocontas;
    private UsuarioLogadoBean usuarioLogadoBean;

    public TipoPlanoContasMB() {
        tipoplanocontas = new Tipoplanocontas();
      
    }

    public List<Tipoplanocontas> getListarTipoPlanoContas() throws SQLException {
        if(listarTipoPlanoContas==null){
            gerarListaTipoPlanoConta();
        }
        return listarTipoPlanoContas;
    }

    public void setListarTipoPlanoContas(List<Tipoplanocontas> listarTipoPlanoContas) {
        this.listarTipoPlanoContas = listarTipoPlanoContas;
    }

    public Tipoplanocontas getTipoplanocontas() {
        return tipoplanocontas;
    }

    public void setTipoplanocontas(Tipoplanocontas tipoplanocontas) {
        this.tipoplanocontas = tipoplanocontas;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }
    
    

    public String salvarTipoPlanoContas() throws SQLException {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getItipoplanocontas()){
            TipoPlanoContasFacede tipoPlanoContasFacede = new TipoPlanoContasFacede();
            tipoPlanoContasFacede.salvar(tipoplanocontas);
            gerarListaTipoPlanoConta();
            return "consTipoPlanoConta";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }

    public String cancelar() {
        return "consTipoPlanoConta";
    }

    public String novo() {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getItipoplanocontas()){
            return "cadTipoPlanoConta";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }

    public String editar() throws SQLException {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getAtipoplanocontas()){
            if (listarTipoPlanoContas!=null){
                for(int i=0;i<listarTipoPlanoContas.size();i++){
                    if (listarTipoPlanoContas.get(i).isSelecionado()){
                        tipoplanocontas = listarTipoPlanoContas.get(i);
                        listarTipoPlanoContas.get(i).setSelecionado(false);
                        i=100000;
                        return "cadTipoPlanoConta";
                    }
                }
            }
            return  "";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
         
    }

    public void gerarListaTipoPlanoConta() throws SQLException {
        TipoPlanoContasFacede tipoPlanoContasFacede = new TipoPlanoContasFacede();
        listarTipoPlanoContas = tipoPlanoContasFacede.listar();
        if (listarTipoPlanoContas == null) {
            listarTipoPlanoContas = new ArrayList<Tipoplanocontas>();
        }
    }
}
