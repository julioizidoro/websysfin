/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.Controller.MovimentoBancoController;
import br.com.financemate.Controller.PlanoContasController;
import br.com.financemate.facade.MovimentoBancoFacade;
import br.com.financemate.facade.PlanoContasFacade;
import br.com.financemate.model.Banco;
import br.com.financemate.model.Movimentobanco;
import br.com.financemate.model.Planocontas;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class OutrosLancamentoMB implements Serializable {

    private List<Planocontas> listarPlanoContas;
    private Planocontas planocontas;
    private Movimentobanco movimentobanco;
    private List<Movimentobanco> listarMovimentobancos;
    @Inject private ClienteMB clienteMB;
    private List<Banco> listaBanco;
    private String idBanco;
    private Date dataInicial;
    private Date dataFinal;

    public List<Movimentobanco> getListarMovimentobancos() throws SQLException {
        if (listarMovimentobancos == null) {
            gerarListaoutroslancamentos();
        }
        return listarMovimentobancos;
    }

    public void setListarMovimentobancos(List<Movimentobanco> listarMovimentobancos) {
        this.listarMovimentobancos = listarMovimentobancos;
    }

    public Movimentobanco getMovimentobanco() {
        return movimentobanco;
    }

    public void setMovimentobanco(Movimentobanco movimentobanco) {
        this.movimentobanco = movimentobanco;
    }
    private String idPlanoConta = "0";
    private String descricao;

    public List<Planocontas> getListarPlanoContas() {
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

    public String getIdPlanoConta() {
        return idPlanoConta;
    }

    public void setIdPlanoConta(String idPlanoConta) {
        this.idPlanoConta = idPlanoConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public List<Banco> getListaBanco() {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            if ((listaBanco == null) || (listaBanco.isEmpty())) {
                carregarListaBanco();
            }
        }
        return listaBanco;
    }

    public void setListaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }

    public String getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(String idBanco) {
        this.idBanco = idBanco;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    

    public String novo() throws SQLException {
        carregarListaPlanoContas();
        carregarListaBanco();
        return "cadOutrosLancamentos";
    }

    public String cancelar() {
        return "consOutrosLancamentos";
    }

     
    
    public void carregarListaPlanoContas(){
        PlanoContasController planoContasController = new PlanoContasController();
        listarPlanoContas = planoContasController.listar(clienteMB.getCliente().getTipoplanocontas().getIdtipoplanocontas());
        if (listarPlanoContas==null){
            listarPlanoContas = new ArrayList<Planocontas>();
        }
    }
    
     public void carregarListaBanco(){
        BancoController bancoController = new BancoController();
        listaBanco = bancoController.listar(clienteMB.getCliente().getIdcliente());
        if (listaBanco==null){
            listaBanco = new ArrayList<Banco>();
        }
    }

     public String salvar(){
        MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
        movimentobanco.setCliente(clienteMB.getCliente());
        movimentoBancoController.salvar(movimentobanco);
        movimentobanco = new Movimentobanco();
        return "consOutrosLancamentos";
    }

    public void gerarListaoutroslancamentos() throws SQLException {
       if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
            listarMovimentobancos = movimentoBancoController.listaMovimento(idBanco);
            if (listarMovimentobancos == null) {
                listarMovimentobancos = new ArrayList<Movimentobanco>();
            }
        }
    }
    public String selecionarUnidade(){
        clienteMB.setPagina("consOutrosLancamentos");
        return "selecionarUnidade";
    }
     public String editar() throws SQLException{
        if (listarMovimentobancos!=null){
            for(int i=0;i<listarMovimentobancos.size();i++){
                if (listarMovimentobancos.get(i).isSelecionado()){
                    movimentobanco = listarMovimentobancos.get(i);
                    listarMovimentobancos.get(i).setSelecionado(false);
                    i=100000;
                    return "cadOutrosLancamentos";
                }
            }
        }
        return  "";
    }
}
