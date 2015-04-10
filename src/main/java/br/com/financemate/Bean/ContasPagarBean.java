/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Bean;

import br.com.financemate.Controller.ContasPagarController;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contaspagar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kamila
 */
public class ContasPagarBean {

    private List<Contaspagar> listaContaPagar;
    private Cliente cliente;
    private Date dataInicial;
    private Date dataFinal;
    private String sql;

    public List<Contaspagar> getListaContaPagar(Cliente cliente) {
        if (listaContaPagar == null) {
            gerarListaContasPagar(cliente);
        }
        return listaContaPagar;
    }

    public void gerarListaContasPagar() {
        ContasPagarController contasPagarController = new ContasPagarController();
        String sql = gerarSqlInicial();
        listaContaPagar = contasPagarController.listarContas(sql);
        if (listaContaPagar == null) {
            listaContaPagar = new ArrayList<Contaspagar>();
        }
    }

    public String gerarSqlInicial() {
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        String mesString = data.substring(3, 5);
        String anoString = data.substring(6, 10);
        int mesInicio = Integer.parseInt(mesString);
        int anoInicio = Integer.parseInt(anoString);
        int mescInicio;
        int mescFinal;
        int anocInicio = 0;
        int anocFinal = 0;
        if (mesInicio == 1) {
            mescInicio = 12;
            anocInicio = anoInicio - 1;
        } else {
            mescInicio = mesInicio - 1;
            anocInicio = anoInicio;
        }
        if (mesInicio == 12) {
            mescFinal = 1;
            anocFinal = anoInicio + 1;
        } else {
            mescFinal = mesInicio + 1;
            anocFinal = anoInicio;
        }
        String dInicial = anocInicio + "-" + Formatacao.retornaDataInicia(mescInicio);
        String dFinal = anocFinal + "-" + Formatacao.retornaDataFinal(mescFinal);
        dataInicial = Formatacao.ConvercaoStringData(dInicial);
        dataFinal = Formatacao.ConvercaoStringData(dFinal);
        String sql = " Select v from Contaspagar v where v.dataVencimento>='" + Formatacao.ConvercaoDataSql(dataInicial)
                + "' and v.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal) + "' and v.contaPaga='N' ";
        if (cliente != null) {
            sql = sql + " and v.cliente.idcliente=" + cliente.getIdcliente();
        } else {
            sql = sql + "  and v.cliente.visualizacao='Operacional' ";
        }
        sql = sql + " order by v.dataVencimento";
        return sql;
    }

    public void setListaContaPagar(List<Contaspagar> listaContaPagar) {
        this.listaContaPagar = listaContaPagar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    private void gerarListaContasPagar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pesquisarContasPagar(Date dataInicial, Date dataFinal, Cliente cliente) {
        this.listaContaPagar = null;
        sql = "Select c from Contaspagar c where ";
            if (cliente != null) {
                sql = sql + " c.cliente.idcliente=" + cliente.getIdcliente() + " and ";
            }
            sql = sql + "c.dataPagamento>='" + Formatacao.ConvercaoDataSql(dataInicial)
                    + "' and c.dataPagamento<='" + Formatacao.ConvercaoDataSql(dataFinal)
                    + "' and c.valorPago>0 order by c.dataVencimento";
       
        gerarListaContasPagar();
    }
}
