/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Bean.ContasReceberBean;
import br.com.financemate.Controller.BancoController;
import br.com.financemate.Controller.PlanoContasController;
import br.com.financemate.model.Banco;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contasreceber;
import br.com.financemate.model.Planocontas;
import java.io.Serializable;
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
@Named("ContasReceberMB")
@SessionScoped
public class ContasReceberMB implements Serializable{
    
    
    @Inject private UsuarioLogadoBean usuarioLogadoBean;
    private ContasReceberBean contasReceberBean;
    private List<Contasreceber> listaContasReceber;
    private Contasreceber contasReceber;
    private Date dataInicial;
    private Date dataFinal;
    private boolean recebida;
    private List<Planocontas> listaPlanoContas;
    private List<Banco> listaBanco;
    private Cliente cliente;
    private float quantidade;
    private float totalconta;
    private float totaljuros;
    private float totaldesconto;
    private float totalrecebido;

   

    public ContasReceberMB() {
        contasReceberBean = new ContasReceberBean();
    }
    
    
    
    public String novo(){
        return "cadConReceber";
    }
    
    public String cancelar(){
        return "consConReceber";
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Planocontas> getListaPlanoContas() {
        return listaPlanoContas;
    }

    public void setListaPlanoContas(List<Planocontas> listaPlanoContas) {
        this.listaPlanoContas = listaPlanoContas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getTotalconta() {
        return totalconta;
    }

    public void setTotalconta(float totalconta) {
        this.totalconta = totalconta;
    }

    public float getTotaljuros() {
        return totaljuros;
    }

    public void setTotaljuros(float totaljuros) {
        this.totaljuros = totaljuros;
    }

    public float getTotaldesconto() {
        return totaldesconto;
    }

    public void setTotaldesconto(float totaldesconto) {
        this.totaldesconto = totaldesconto;
    }

    public float getTotalrecebido() {
        return totalrecebido;
    }

    public void setTotalrecebido(float totalrecebido) {
        this.totalrecebido = totalrecebido;
    }

    public List<Banco> getListaBanco() {
        return listaBanco;
    }

    public void setListaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }

    public List<Contasreceber> getListaContasReceber() {
         if (listaContasReceber==null){
            iniciarContasReceber();
        }
        return listaContasReceber;
    }

    public void setListaContasReceber(List<Contasreceber> listaContasReceber) {
        this.listaContasReceber = listaContasReceber;
    }

    public Contasreceber getContasReceber() {
        return contasReceber;
    }

    public boolean isRecebida() {
        return recebida;
    }

    public void setRecebida(boolean recebida) {
        this.recebida = recebida;
    }

    public void setContasReceber(Contasreceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public ContasReceberBean getContasReceberBean() {
        return contasReceberBean;
    }

    public void setContasReceberBean(ContasReceberBean contasReceberBean) {
        this.contasReceberBean = contasReceberBean;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    
    
    public void iniciarContasReceber(){
        this.listaContasReceber = contasReceberBean.getListaContasReceber(usuarioLogadoBean.getCliente());
        dataInicial = contasReceberBean.getDataInicial();
        dataFinal = contasReceberBean.getDataFinal();
    }
    
    public void pesquisar(){
        contasReceberBean.pesquisarContasReceber(dataInicial, dataFinal, recebida, null);
        listaContasReceber = contasReceberBean.getListaContasReceber(null);
    }
    
    public void carregarListaBanco(){
        BancoController bancoController = new BancoController();
        listaBanco = bancoController.listar(cliente.getIdcliente());
        if (listaBanco==null){
            listaBanco = new ArrayList<Banco>();
        }
    }
    
    public void carregarListaPlanoContas(){
        PlanoContasController planoContasController = new PlanoContasController();
        listaPlanoContas = planoContasController.listar();
        if (listaPlanoContas==null){
            listaPlanoContas = new ArrayList<Planocontas>();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
