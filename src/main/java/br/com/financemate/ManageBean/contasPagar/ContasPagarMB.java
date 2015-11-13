/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean.contasPagar;

import br.com.financemate.ManageBean.UsuarioLogadoBean;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.facade.ClienteFacade;
import br.com.financemate.facade.ContasPagarFacade;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contaspagar;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Julio
 */

@Named()
@SessionScoped
public class ContasPagarMB implements Serializable{
    
    private Date dataInicio;
    private Date dataFinal;
    private String sql;
    private Cliente cliente;
    private List<Contaspagar> listaContasPagar;
    private List<Cliente> listaCliente;
    private Boolean liberadas;
    private Boolean autorizadas;
    private String totalVencidas;
    private String totalVencer;
    private String totalVencendo;
    private String total;
    private Contaspagar contasPagar;
    private UsuarioLogadoBean usuarioLogadoBean;
    
    @PostConstruct
    public void init(){
        setLiberadas(false);
        setAutorizadas(false);
        criarConsultaContasPagarInicial();
        gerarListaContas();
        gerarListaCliente();
    }

    public Contaspagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(Contaspagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public String getTotalVencidas() {
        return totalVencidas;
    }

    public void setTotalVencidas(String totalVencidas) {
        this.totalVencidas = totalVencidas;
    }

    public String getTotalVencer() {
        return totalVencer;
    }

    public void setTotalVencer(String totalVencer) {
        this.totalVencer = totalVencer;
    }

    public String getTotalVencendo() {
        return totalVencendo;
    }

    public void setTotalVencendo(String totalVencendo) {
        this.totalVencendo = totalVencendo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public Boolean getLiberadas() {
        return liberadas;
    }

    public void setLiberadas(Boolean liberadas) {
        this.liberadas = liberadas;
    }

    public Boolean getAutorizadas() {
        return autorizadas;
    }

    public void setAutorizadas(Boolean autorizadas) {
        this.autorizadas = autorizadas;
    }

    
    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Contaspagar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<Contaspagar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
    }
    
    public void criarConsultaContasPagarInicial(){
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
        String dataTermino = anocFinal + "-" + Formatacao.retornaDataFinal(mescFinal);
        setDataInicio(Formatacao.ConvercaoStringData(dataInicial));
        setDataFinal(Formatacao.ConvercaoStringData(dataTermino));
        sql = " Select v from Contaspagar v where v.dataVencimento>='" + dataInicial + 
                "' and v.dataVencimento<='" + dataTermino + "' and v.contaPaga='N' ";
         if (cliente!=null){
            sql = sql + " and v.cliente.idcliente=" + cliente.getIdcliente();
        }else {
             sql = sql + "  and v.cliente.visualizacao='Operacional' ";
         }
        sql = sql + " order by v.dataVencimento";
        
    }
    
    public void gerarListaContas() {
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        try {
            listaContasPagar = contasPagarFacade.listar(sql);
            if (listaContasPagar == null) {
                listaContasPagar = new ArrayList<Contaspagar>();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro a listar contas a pagar", "Erro");
        }
        calcularTotal();
    }
    
    public void gerarListaCliente() {
        ClienteFacade clienteFacade = new ClienteFacade();
        try {
            listaCliente = clienteFacade.listar("");
            if (listaCliente == null) {
                listaCliente = new ArrayList<Cliente>();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro ao listar o cliente:", "Erro");
        }

    }
    
    public void mostrarMensagem(Exception ex, String erro, String titulo){
        FacesContext context = FacesContext.getCurrentInstance();
        erro = erro + " - " + ex;
        context.addMessage(null, new FacesMessage(titulo, erro));
    }  
    
    public void pesquisar(){
        sql = "Select v from Contaspagar v where ";
        if (liberadas){
            sql = sql + " v.contaPaga='S' and ";
        }else sql = sql + " v.contaPaga='N' and ";
        if (autorizadas){
            sql = sql + " v.autorizarPagamento='S' and ";
        }
        if (cliente!=null){
            sql = sql + " v.cliente.idcliente=" + cliente.getIdcliente() + " and ";
        }else {
            sql = sql + " v.cliente.visualizacao='Operacional' and ";
        }
        if (liberadas){
            sql = sql + "v.dataLiberacao>='" + Formatacao.ConvercaoDataSql(getDataInicio()) + 
                "' and v.dataLiberacao<='" + Formatacao.ConvercaoDataSql(getDataFinal()) + 
                "' order by v.dataLiberacao";
        }else {
            sql = sql + "v.dataVencimento>='" + Formatacao.ConvercaoDataSql(getDataInicio()) + 
                "' and v.dataVencimento<='" + Formatacao.ConvercaoDataSql(getDataFinal()) + 
                "' order by v.dataVencimento";
        }
        
        gerarListaContas();
    }
    
    public String limparConsulta(){
        try {
            ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
            listaContasPagar = contasPagarFacade.listar(sql);
            setLiberadas(false);
            setAutorizadas(false);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            mostrarMensagem(ex, "Erro Listar Contas", "Erro");
        }
        return "consConPagar";

    }
    public void calcularTotal(){
        float vencida = 0.0f;
        float vencendo = 0.0f;
        float vencer = 0.0f;
        Date data = new Date();
        String diaData = Formatacao.ConvercaoDataPadrao(data);
        for(int i=0;i<listaContasPagar.size();i++){
            String vencData = Formatacao.ConvercaoDataPadrao(listaContasPagar.get(i).getDataVencimento());
            if (diaData.equalsIgnoreCase(vencData)){
                vencendo = vencendo + listaContasPagar.get(i).getValor();
            }else if (listaContasPagar.get(i).getDataVencimento().before(data)){
                vencida = vencida + listaContasPagar.get(i).getValor();
            }else if (listaContasPagar.get(i).getDataVencimento().after(data)){
                vencer = vencer + listaContasPagar.get(i).getValor();
            }
        }
        setTotalVencidas(Formatacao.foramtarFloatString(vencida));
        setTotalVencendo(Formatacao.foramtarFloatString(vencendo));
        setTotalVencer(Formatacao.foramtarFloatString(vencer));
        setTotal(Formatacao.foramtarFloatString(vencida+vencer+vencendo));
    }
    
    public String novaConta() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadConPagar");
        return "";
    }
    
    public void retornoDialogNovo(SelectEvent event){
       Contaspagar contaspagar = (Contaspagar) event.getObject();
       listaContasPagar.add(contaspagar);
   }
    
    public String editar(Contaspagar contaspagar){
        if (contaspagar!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("contapagar", contaspagar);     
            RequestContext.getCurrentInstance().openDialog("cadConPagar");
        }
        return "";
    }  
    
    public String excluir(){
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        contasPagarFacade.excluir(contasPagar.getIdcontasPagar());
        gerarListaContas();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Excluido com Sucesso", ""));
        return "";
     }
    
    
    
    public void autorizarPagamentoContasPagar(Contaspagar contaspagar){
        contaspagar.setAutorizarPagamento("S");
    }
    
    public String novaLiberacao() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("liberacaoConPagar");
        return "";
    }
}
