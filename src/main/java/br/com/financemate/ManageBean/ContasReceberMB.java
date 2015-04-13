/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.Controller.ContasReceberController;
import br.com.financemate.Controller.PlanoContasController;
import br.com.financemate.Util.Formatacao;
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
    @Inject private ClienteMB clienteMB;
    private List<Contasreceber> listaContasReceber;
    private Contasreceber contasReceber;
    private Date dataInicial;
    private Date dataFinal;
    private boolean recebida;
    private List<Planocontas> listaPlanoContas;
    private List<Banco> listaBanco;
    private String quantidade;
    private String totalconta;
    private String totaljuros;
    private String totaldesconto;
    private String totalrecebido;
    String sql;

   

    public ContasReceberMB() {
        gerarDataInicial();
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
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

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getTotalconta() {
        return totalconta;
    }

    public void setTotalconta(String totalconta) {
        this.totalconta = totalconta;
    }

    public String getTotaljuros() {
        return totaljuros;
    }

    public void setTotaljuros(String totaljuros) {
        this.totaljuros = totaljuros;
    }

    public String getTotaldesconto() {
        return totaldesconto;
    }

    public void setTotaldesconto(String totaldesconto) {
        this.totaldesconto = totaldesconto;
    }

    public String getTotalrecebido() {
        return totalrecebido;
    }

    public void setTotalrecebido(String totalrecebido) {
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
             criarConsultaContasReceberInicial();
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

    
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    public void carregarListaBanco(){
        BancoController bancoController = new BancoController();
        listaBanco = bancoController.listar(clienteMB.getCliente().getIdcliente());
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
    
    public void criarConsultaContasReceberInicial(){
        if ((clienteMB.getCliente()!=null) && (clienteMB.getCliente().getIdcliente()!=null)){
            sql = " Select c from Contasreceber c where c.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal) + 
                "' and c.valorPago=0 and c.cliente.idcliente=" + clienteMB.getCliente().getIdcliente() + 
                    " order by c.dataVencimento";
        }else {
            sql = " Select c from Contasreceber c where c.cliente.visualizacao='Operacional' and "
                    + "c.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal) + 
                "' and c.valorPago=0 order by c.dataVencimento";
        }
        carregarLista();
    }
    
    public void carregarLista() {
        if (listaContasReceber == null) {
            ContasReceberController contasReceberController = new ContasReceberController();
            listaContasReceber = contasReceberController.listar(sql);
            if (listaContasReceber == null) {
                listaContasReceber = new ArrayList<Contasreceber>();
            }
        }
    }
    
    public void pesquisarContasReceber(Date dataInicial, Date dataFinal, boolean recebida, Cliente cliente ){
        this.listaContasReceber=null;
        sql = "Select c from Contasreceber c where ";
        if (recebida) {
            if (cliente != null) {
                sql = sql + " c.cliente.idcliente=" + cliente.getIdcliente() + " and ";
            }
            sql = sql + "c.dataPagamento>='" + Formatacao.ConvercaoDataSql(dataInicial)
                    + "' and c.dataPagamento<='" + Formatacao.ConvercaoDataSql(dataFinal)
                    + "' and c.valorPago>0 order by c.dataVencimento";
        } else {
            if (cliente != null) {
                sql = sql + " c.cliente,idcliente=" + cliente.getIdcliente() + " and ";
            }else {
                sql = sql + " c.cliente.visualizacao='Operacional' and ";
            }
            sql = sql + "c.dataVencimento>='" + Formatacao.ConvercaoDataSql(dataInicial)
                    + "' and c.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal)
                    + "' and c.valorPago=0 order by c.dataVencimento";
        }
        carregarLista();
    }
    
    
    public void gerarDataInicial(){
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        String mesString = data.substring(3, 5);
        String anoString = data.substring(6, 10);
        int mesInicio = Integer.parseInt(mesString);
        int anoInicio = Integer.parseInt(anoString);
        int mescInicio;
        int mescFinal;
        int anocInicio = 0;
        int anocFinal = 0;
        if (mesInicio==1){
            mescInicio=12;
            anocInicio=anoInicio - 1;
        }else {
            mescInicio = mesInicio - 1;
            anocInicio = anoInicio;
        }
        if (mesInicio==12){
            mescFinal=1;
            anocFinal=anoInicio+1;
        }else {
            mescFinal = mesInicio  + 1;
            anocFinal = anoInicio;
        }
        String sdataInicial = anocInicio + "-" + Formatacao.retornaDataInicia(mescInicio);
        String sdataFinal = anocFinal + "-" + Formatacao.retornaDataFinal(mescFinal);
        dataInicial =(Formatacao.ConvercaoStringData(sdataInicial));
        dataFinal = (Formatacao.ConvercaoStringData(sdataFinal));
    }
}
