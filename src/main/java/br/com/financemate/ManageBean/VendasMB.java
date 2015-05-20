/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.VendasController;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.model.Vendas;
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
@Named("VendasMB")
@SessionScoped
public class VendasMB implements Serializable{
    
    @Inject private UsuarioLogadoBean usuarioLogadoBean;
    @Inject private ClienteMB clienteMB;
    private Vendas venda;
    private List<Vendas> listaVendas;
    private String sql;
    private String order;

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public List<Vendas> getListaVendas() {
        if (listaVendas==null){
            gerarDataInicial();
            gerarListaVendas();
        }
        return listaVendas;
    }

    public void setListaVendas(List<Vendas> listaVendas) {
        this.listaVendas = listaVendas;
    }
    
     public String novo(){
        return "cadLancarVendas";
    }
    
    public String cancelar(){
        return "consVendas";
    }
    
    public void gerarListaVendas(){
        sql = sql + order;
        VendasController vendasController = new VendasController();
        listaVendas = vendasController.listar(sql);
        if (listaVendas==null){
            listaVendas = new ArrayList<Vendas>();
        }
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
        String dataInicial = anocInicio + "-" + Formatacao.retornaDataInicia(mescInicio);
        String dataFinal = anocFinal + "-" + Formatacao.retornaDataFinal(mescFinal);
         sql =null;
        if (usuarioLogadoBean.getUsuario().getCliente()>0){
            sql = " Select v from Viewvendas v where v.dataVenda>='" + dataInicial + 
                "' and v.dataVenda<='" + dataFinal + "' and situacao<>'verde' and clienteIdcliente=" + 
                    usuarioLogadoBean.getUsuario().getCliente();
            order = " order by v.dataVenda";
        }else {
            sql = " Select v from Viewvendas v where v.visualizacao='Operacional' and "
                    + "v.dataVenda>='" + dataInicial + 
                "' and v.dataVenda<='" + dataFinal + "' and v.situacao<>'verde'";
            order = " order by v.dataVenda";
        }
    }
    
     public String selecionarUnidade() {
        clienteMB.setPagina("relVendas");
        return "selecionarUnidade";
    }
    
}
