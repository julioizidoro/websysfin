/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.facade.PlanoContasFacade;
import br.com.financemate.facade.TipoPlanoContasFacede;
import br.com.financemate.model.Planocontas;
import br.com.financemate.model.Tipoplanocontas;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class PlanoContasMB implements Serializable {

    private List<Planocontas> listarPlanoContas;
    private Planocontas planocontas;
    private String idTipoPlanoConta="0";
    private List<Tipoplanocontas> listarTipoPlanoContas;
    private String descricao;
    
    public PlanoContasMB() throws SQLException {
        planocontas = new Planocontas();
        listarTipoPlanoContas();
    }

    public List<Planocontas> getListarPlanoContas() throws SQLException {
        if(listarPlanoContas==null){
            gerarListaPlanoConta();
            listarTipoPlanoContas();
        }
        return listarPlanoContas;
    }

    public void setListarPlanoContas(List<Planocontas> listarPlanoContas) {
        this.listarPlanoContas = listarPlanoContas;
    }

    public Planocontas getPlanocontas() {
        return planocontas;
    }

    public void setPlanocontas(Planocontas planocontas) {
        this.planocontas = planocontas;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    public String salvarPlanoContas() throws SQLException {
        PlanoContasFacade planoContasFacede = new PlanoContasFacade();
        TipoPlanoContasFacede tipoPlanoContasFacede = new TipoPlanoContasFacede();
        Tipoplanocontas tipo = tipoPlanoContasFacede.consultar(Integer.parseInt(idTipoPlanoConta));
        planocontas.setTipoplanocontas(tipo);
        planoContasFacede.salvar(planocontas);
        gerarListaPlanoConta();
        return "consPlanoConta";
    }

    public String novo() throws SQLException {
        listarTipoPlanoContas();
        return "cadplanocontas";
    }

    public String cancelar() {
        return "consPlanoConta";
    }

    public String editar() throws SQLException {
         if (listarPlanoContas!=null){
            for(int i=0;i<listarPlanoContas.size();i++){
                if (listarPlanoContas.get(i).isSelecionado()){
                    planocontas = listarPlanoContas.get(i);
                    listarPlanoContas.get(i).setSelecionado(false);
                    i=100000;
                    return "cadplanocontas";
                }
            }
        }
        return  "";
    }
    public void listarTipoPlanoContas() throws SQLException{
        TipoPlanoContasFacede tipoPlanoContasFacede = new TipoPlanoContasFacede();
        listarTipoPlanoContas = tipoPlanoContasFacede.listar();
        if (listarTipoPlanoContas==null){
            listarTipoPlanoContas = new ArrayList<Tipoplanocontas>();
        }
    }
    public void gerarListaPlanoConta() throws SQLException {
        PlanoContasFacade planoContasFacade = new PlanoContasFacade();
        listarPlanoContas = planoContasFacade.listar(descricao, Integer.parseInt(idTipoPlanoConta));
        if (listarPlanoContas == null) {
            listarPlanoContas = new ArrayList<Planocontas>();
        }
    }
    public String pesquisar() throws SQLException{
        gerarListaPlanoConta();
        return "consPlanoConta";
    }
}
