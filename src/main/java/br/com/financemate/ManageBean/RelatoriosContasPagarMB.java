/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Connection.ConectionFactory;
import br.com.financemate.Relatorios.ExecutorRelatorio;
import br.com.financemate.Relatorios.relatoriosJasper;
import br.com.financemate.Util.Formatacao;
import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import org.eclipse.persistence.sessions.Session;


/**
 *
 * @author Wolverine
 */

@Named("RelatoriosContasPagarMB")
@SessionScoped
public class RelatoriosContasPagarMB implements Serializable{
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    @Inject
    private ClienteMB clienteMB;
    private Date dataInicio;
    private Date dataTermino;
    private String competencia;
    private String tipoRelatorio;

    public RelatoriosContasPagarMB() {
        dataInicio = new Date();
        dataTermino = new Date();
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

   

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
    
    
    
    public String exportarExcel(){
        return null;
    }
    
    public String gerarRelatirio(){
        return null;
    }
    
    public String selecionarUnidade() {
        clienteMB.setPagina("relContasPagar");
        return "selecionarUnidade";
    }
    
    public String iniciarRelatorio(){
        if (tipoRelatorio.equalsIgnoreCase("contasVencidas")){
            relatorioContasVencidas();
        }
        return null;
    }
    
    public String cancelar(){
        return "index";
    }
    
    public String gerarSqlRelatorioContasVencidas(){
        String sql = "Select distinct contasPagar.dataVencimento, contasPagar.descricao, contasPagar.valor, contasPagar.dataAgendamento,cliente.nomeFantasia, contasPagar.fornecedor, contasPagar.numeroDocumento";
        sql = sql + " From ";
        sql = sql + " contasPagar join cliente on contasPagar.cliente_idcliente = cliente.idcliente ";
        sql = sql +" where ";
        if ((dataInicio!=null) && (dataTermino!=null)){
            sql = sql + "contasPagar.dataVencimento>='" +  Formatacao.ConvercaoDataSql(dataInicio) +
                    "' and contasPagar.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataTermino) + "' and ";
        }
	sql = sql + " contasPagar.cliente_idcliente=" + clienteMB.getCliente().getIdcliente() + " and contasPagar.contaPaga='N'";
        sql = sql + " order by contasPagar.dataVencimento";
        return sql;
    }
    
    public void relatorioContasVencidas() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String localLogo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "img" +
                                    File.separator + "logo.jpg";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","inline; filename=\"arquivo.pdf\"");
        Map parameters = new HashMap();
        Image logo = new ImageIcon(localLogo).getImage();
        parameters.put("logo", logo);
        String periodo = null;
        periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
            + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
        parameters.put("periodo", periodo);
        parameters.put("sql",gerarSqlRelatorioContasVencidas());
        new relatoriosJasper(parameters);

//        ExecutorRelatorio executor = new ExecutorRelatorio("relatorios/reportpagamentovencidas.jasper",
//        response, parameters, "Conntas Pagar Vencidas.pdf");
//        new relatoriosJasper(null, parameters);
//        
//
//        if (executor.isRelatorioGerado()) {
//            facesContext.responseComplete();
//        } else {
//            System.out.println("Erro");
//        }
    }

}
