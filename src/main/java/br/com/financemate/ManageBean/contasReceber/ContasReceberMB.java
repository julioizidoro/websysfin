/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean.contasReceber;

import br.com.financemate.ManageBean.UsuarioLogadoBean;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.facade.ClienteFacade;
import br.com.financemate.facade.ContasReceberFacade;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contasreceber;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Greici
 */
@Named
@ViewScoped
public class ContasReceberMB implements Serializable{
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    private List<Cliente> listaCliente;
    private Cliente cliente;
    private Date dataInicial;
    private Date dataFinal;
    private String sql;
    private List<Contasreceber> listaContasReceber;
    private Contasreceber Contasreceber;
    private boolean recebidas;
    private int quantidadeTitulos;
    private float totalContasReceber;
    private float totalJurosReceber;
    private float totalDescontosReceber;
    private float valorTotalRecebido;

    @PostConstruct
    public void init(){
        gerarListaCliente();
        criarConsultaContaReceber();
        gerarListaContas();
        quantidadeTitulos();
       
      
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Contasreceber> getListaContasReceber() {
        return listaContasReceber;
    }

    public void setListaContasReceber(List<Contasreceber> listaContasReceber) {
        this.listaContasReceber = listaContasReceber;
    }

    public Contasreceber getContasreceber() {
        return Contasreceber;
    }

    public void setContasreceber(Contasreceber Contasreceber) {
        this.Contasreceber = Contasreceber;
    }

    public boolean isRecebidas() {
        return recebidas;
    }

    public void setRecebidas(boolean recebidas) {
        this.recebidas = recebidas;
    }

    public int getQuantidadeTitulos() {
        return quantidadeTitulos;
    }

    public void setQuantidadeTitulos(int quantidadeTitulos) {
        this.quantidadeTitulos = quantidadeTitulos;
    }

    public float getTotalContasReceber() {
        return totalContasReceber;
    }

    public void setTotalContasReceber(float totalContasReceber) {
        this.totalContasReceber = totalContasReceber;
    }

    public float getTotalJurosReceber() {
        return totalJurosReceber;
    }

    public void setTotalJurosReceber(float totalJurosReceber) {
        this.totalJurosReceber = totalJurosReceber;
    }

    public float getTotalDescontosReceber() {
        return totalDescontosReceber;
    }

    public void setTotalDescontosReceber(float totalDescontosReceber) {
        this.totalDescontosReceber = totalDescontosReceber;
    }

    public float getValorTotalRecebido() {
        return valorTotalRecebido;
    }

    public void setValorTotalRecebido(float valorTotalRecebido) {
        this.valorTotalRecebido = valorTotalRecebido;
    }

    
    
    public void gerarListaCliente(){
        ClienteFacade clienteFacade = new ClienteFacade();
        try {
            listaCliente = clienteFacade.listar("");
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Listar Clientes", "Erro");
        }
    }
     
    public void mostrarMensagem(Exception ex, String erro, String titulo){
        FacesContext context = FacesContext.getCurrentInstance();
        erro = erro + " - " + ex;
        context.addMessage(null, new FacesMessage(titulo, erro));
    }
    
    public void criarConsultaContaReceber(){
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
        setDataInicial(Formatacao.ConvercaoStringData(dataInicial));
        setDataFinal(Formatacao.ConvercaoStringData(dataFinal));
        if (usuarioLogadoBean.getUsuario().getCliente()>0){
            sql = " Select v from Contasreceber v where v.dataVencimento<='" + dataFinal + 
                "' and v.valorPago=0 and v.cliente.idcliente=" + usuarioLogadoBean.getUsuario().getCliente() + 
                    " order by v.dataVencimento";
        }else {
            sql = " Select v from Contasreceber v where v.cliente.visualizacao='Operacional' and "
                    + "v.dataVencimento<='" + dataFinal + 
                "' and v.valorPago=0 order by v.dataVencimento";
        }
        
    }
    
    public void gerarListaContas() {
        try {
            ContasReceberFacade contasReceberFacadece = new ContasReceberFacade();
            listaContasReceber = contasReceberFacadece.listar(sql);
            if (listaContasReceber == null) {
                listaContasReceber = new ArrayList<Contasreceber>();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Listar Contas", "Erro");
        }
        quantidadeTitulos();
    }

    public void pesquisarContas() {
        sql = "Select v from Contasreceber v where ";
        if (recebidas) {
            if (cliente != null) {
                sql = sql + " v.cliente.idcliente=" + cliente.getIdcliente() + " and ";
            }
            sql = sql + "v.dataPagamento>='" + Formatacao.ConvercaoDataSql(getDataInicial())
                    + "' and v.dataPagamento<='" + Formatacao.ConvercaoDataSql(getDataFinal())
                    + "' and v.valorPago>0 order by v.dataVencimento";
        } else {
            if (cliente != null) {
                sql = sql + " v.cliente.idcliente=" + cliente.getIdcliente() + " and ";
            } else {
                sql = sql + " v.cliente.visualizacao='Operacional' and ";
            }
            sql = sql + "v.dataVencimento>='" + Formatacao.ConvercaoDataSql(getDataInicial())
                    + "' and v.dataVencimento<='" + Formatacao.ConvercaoDataSql(getDataFinal())
                    + "' and v.valorPago=0 order by v.dataVencimento";
        }
        gerarListaContas();
    }

    public void quantidadeTitulos(){
        quantidadeTitulos = listaContasReceber.size();
        gerarTotalContas();
        gerarTotalJuros();
        gerarTotalDesconto();
        gerarValorTotalRecebido();
    }
    
    public void gerarTotalContas(){
        if(totalContasReceber!=0.0){
                totalContasReceber = 0;
        }
        for (int i = 0; i < listaContasReceber.size(); i++) {
            totalContasReceber = totalContasReceber + listaContasReceber.get(i).getValorParcela();
         }
    }
       
    public void gerarTotalJuros(){
        for (int i = 0; i <listaContasReceber.size(); i++) {
            totalJurosReceber = totalJurosReceber + listaContasReceber.get(i).getJuros();
        }
    }
    
        
    public void gerarTotalDesconto(){
        for (int i = 0; i <listaContasReceber.size(); i++) {
            totalDescontosReceber = totalDescontosReceber + listaContasReceber.get(i).getDesagio();
        }
    }
    
    public void gerarValorTotalRecebido(){
        for (int i = 0; i <listaContasReceber.size(); i++) {
            valorTotalRecebido = valorTotalRecebido + listaContasReceber.get(i).getValorPago();
        }
    }
    
    public String limparDadosPesquisa() {
        try {
            ContasReceberFacade contasReceberFacadece = new ContasReceberFacade();
            listaContasReceber = contasReceberFacadece.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceberMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Listar Contas", "Erro");
        }
        return "consConReceber";

    }
    
    public String novo(){
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 580);
            RequestContext.getCurrentInstance().openDialog("cadConReceber");
            return "";
    }
    
   
}
