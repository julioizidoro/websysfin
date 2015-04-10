/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Bean;

import br.com.financemate.Controller.ContasReceberController;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contasreceber;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class ContasReceberBean {
    
    private List<Contasreceber> listaContasReceber;
    private String sql;
    private Date dataInicial;
    private Date dataFinal;
    

    public List<Contasreceber> getListaContasReceber(Cliente cliente) {
        if (listaContasReceber==null){
            criarConsultaContasPagarInicial(cliente);
        }else {
            
        }
        return listaContasReceber;
    }

    public void setListaContasReceber(List<Contasreceber> listaContasReceber) {
        this.listaContasReceber = listaContasReceber;
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

    

    
    
    
    
    public void criarConsultaContasPagarInicial(Cliente cliente){
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
        System.out.println(sdataFinal);
        if (cliente!=null){
            sql = " Select c from Contasreceber c where c.dataVencimento<='" + sdataFinal + 
                "' and c.valorPago=0 and c.cliente.idcliente=" + cliente.getIdcliente() + 
                    " order by c.dataVencimento";
        }else {
            sql = " Select c from Contasreceber c where c.cliente.visualizacao='Operacional' and "
                    + "c.dataVencimento<='" + sdataFinal + 
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
}
