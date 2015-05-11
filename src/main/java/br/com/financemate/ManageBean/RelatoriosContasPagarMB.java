/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Connection.ConectionFactory;
import br.com.financemate.Controller.ContasPagarController;
import br.com.financemate.Controller.FluxoCaixaController;
import br.com.financemate.Controller.MovimentoBancoController;

import br.com.financemate.Util.Formatacao;
import br.com.financemate.model.Contaspagar;
import br.com.financemate.model.Fluxocaixa;
import br.com.financemate.model.Movimentobanco;
import static groovy.inspect.Inspector.print;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;


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
    private List<Contaspagar> listaContasPagar;
    private String titulo;
    private String periodo;
    private List<Fluxocaixa> listaFluxoCaixa;
    private List<Movimentobanco> listaMovimentoBanco;
    
    
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

    public String getTitulo() {
        return titulo;
    }

    public List<Movimentobanco> getListaMovimentoBanco() {
        return listaMovimentoBanco;
    }

    public void setListaMovimentoBanco(List<Movimentobanco> listaMovimentoBanco) {
        this.listaMovimentoBanco = listaMovimentoBanco;
    }

    public String getPeriodo() {
        return periodo;
    }

    public List<Fluxocaixa> getListaFluxoCaixa() {
        return listaFluxoCaixa;
    }

    public void setListaFluxoCaixa(List<Fluxocaixa> listaFluxoCaixa) {
        this.listaFluxoCaixa = listaFluxoCaixa;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Contaspagar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<Contaspagar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
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
            try {
                relatorioContasVencidas();
            } catch (JRException ex) {
                Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (tipoRelatorio.equalsIgnoreCase("fluxoCaixa")){
            
        }
        return null;
    }
    
    public String cancelar(){
        setTitulo("");
        setCompetencia("");
        setDataInicio(new Date());
        setDataTermino(new Date());
        setPeriodo("");
        setTipoRelatorio("Selecione");
        clienteMB = new ClienteMB();
        return "principal";
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
    
    public void relatorioContasVencidas() throws JRException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext(); 
        String pathRel = servletContext.getRealPath("/resources/report/reportPagamentoVencidas.jasper");  
        Map parameters = new HashMap();
         String localLogo = "/resources" + File.separator + "img" +
                                    File.separator + "logo.jpg";
        Image logo = new ImageIcon(localLogo).getImage();
        //parameters.put("logo", logo);
        String periodo = null;
        periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
            + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
        parameters.put("periodo", periodo);
        parameters.put("sql",gerarSqlRelatorioContasVencidas());
        //parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
        JasperPrint arquivoPrint=null;
        Connection conn = ConectionFactory.getConexao();
        byte[] bytes = null;
        arquivoPrint = JasperFillManager.fillReport(pathRel, parameters, conn);
            HttpServletResponse response=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, arquivoPrint);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ "teste.pdf"  + "\"");
				
				exportador.exportReport();
//         HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
//      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
//       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
//       JasperExportManager.exportReportToPdfStream(arquivoPrint, servletOutputStream);
       
       
       
//        bytes = JasperRunManager.runReportToPdf(pathRel, parameters, conn);
//        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();  
//        if (bytes != null && bytes.length > 0 && arquivoPrint != null && arquivoPrint.getPages().size() > 0) {
//            response.setHeader("Content-Disposition", "attachment; ContasVencidas" + "_"+new Date().getTime()+".pdf");
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            ServletOutputStream outputStream;
//
//            try {
//                outputStream = response.getOutputStream();
//                outputStream.write(bytes, 0, bytes.length);
//                outputStream.flush();
//                outputStream.close();
//                FacesContext.getCurrentInstance().responseComplete();
//            } catch (IOException e) {
//                System.out.println("Erro");
//            }
//        }
        
//         
//         
//         String caminho = "/br/com/financemate/reportPagamentoVencidas.jasper";
//         String reportName = "/br/com/financemate/reportPagamentoVencidas.jasper";
//        URL arquivo = this.getClass().getClassLoader().getResource(reportName);
//        File arq = new File(reportName);
//        URL jasper = this.getClass().getResource(reportName); 
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName); 
//         
//         System.out.println(conn);
//        try {
//            if (conn.isValid(0)){
//                System.out.println("tudo ok");
//            }else System.out.println("erro conexao");
//        } catch (SQLException ex) {
//            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            jasperPrint=JasperFillManager.fillReport(pathRel, parameters,conn);
//        } catch (JRException ex) {
//            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           
//         
//       
////         try {  
//////            print = JasperFillManager.fillReport(, parameters, conn);
////        } catch (JRException ex) {
////            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        
//        byte[] bytes = null;                 
//        try {
//            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
//        } catch (JRException ex) {
//            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (bytes != null && bytes.length > 0 && jasperPrint != null && jasperPrint.getPages().size() > 0) {
//            response.setHeader("Content-Disposition", "attachment; filename="+"ContasVencidas"+"_"+new Date().getTime()+".pdf");
//            response.setContentType("application/pdf");
//            response.setContentLength(bytes.length);
//            ServletOutputStream outputStream = null;
//            try {
//                outputStream = response.getOutputStream();
//            } catch (IOException ex) {
//                Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                outputStream.write(bytes, 0, bytes.length);
//            } catch (IOException ex) {
//                Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                outputStream.flush();
//            } catch (IOException ex) {
//                Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                outputStream.close();
//            } catch (IOException ex) {
//                Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//                FacesContext.getCurrentInstance().responseComplete();
//            
//        }
//        //writeBytesAsAttachedTextFile(bytes, "ListaDeDepartamentos.pdf");   
//
//
//        
//        
//        
//        
////        String reportName = "/resources/report/reportPagamentoVencidas.jrxml";
////        URL arquivo = this.getClass().getClassLoader().getResource(reportName);
////        File arq = new File(reportName);
////        URL jasper = this.getClass().getResource(reportName); 
////        InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName); 
////        try {
////            JasperReport report = JasperCompileManager.compileReport(reportName);
////            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
////            JasperExportManager.exportReportToPdfFile(print, "c:/Relatorio_de_Clientes.pdf");
////            
////        } catch (JRException ex) {
////            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        JasperReport jasperReport = null; 
////        try {
////            jasperReport = (JasperReport) JRLoader.loadObject(arquivo);
////        } catch (JRException ex) {
////            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        
//        
//        // byte[] bytes = null;
//         JasperPrint arquivoPrint = null;
////        
////        try {
////            arquivoPrint = JasperFillManager.fillReport( jasperReport, parameters, conn);
////            //arquivoPrint = JasperFillManager.fillReport(reportName, parameters, conn);
////        } catch (JRException ex) {
////            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        try {
////            bytes = JasperRunManager.runReportToPdf(reportName, parameters, conn);
////        } catch (JRException ex) {
////            Logger.getLogger(RelatoriosContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
////        }

//        
    }
    
    public String verificarTipoRelatorioExcel(){
        String pagina="";
        if (tipoRelatorio.equalsIgnoreCase("contasVencidas")){
             pagina = gerarExcelContasPagarVencidas();
        }else if (tipoRelatorio.equalsIgnoreCase("fluxoCaixa")){
            pagina = gerarExcelFluxoCaixa();
        }else if (tipoRelatorio.equalsIgnoreCase("pagamento")){
            pagina = gerarExcelPagamentos();
        }
        return pagina;
    }
    
    public String gerarExcelContasPagarVencidas(){
        listaContasPagar = null;
        titulo = "Contas a Pagar Vencidas";
        this.periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
            + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
        ContasPagarController contasPagarController = new ContasPagarController();
        String sql = "Select c from Contaspagar c where ";
        if ((dataInicio!=null) && (dataTermino!=null)){
            sql = sql + "c.dataVencimento>='" + Formatacao.ConvercaoDataSql(dataInicio) +
                "' and c.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataTermino) + "' and ";
        }
        sql = sql + " c.cliente.idcliente=" + clienteMB.getCliente().getIdcliente() + " and c.contaPaga='N'";
        sql = sql + " order by c.dataVencimento";
        listaContasPagar = contasPagarController.listar(sql);
        if (listaContasPagar!=null){
            return "exportRelatorioContas";
        }
        return null;
    }
    
    public String gerarExcelFluxoCaixa(){
        listaFluxoCaixa = null;
        titulo = "Fluxo de Caixa";
        this.periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
            + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
        FluxoCaixaController fluxoCaixaController = new FluxoCaixaController();
        listaFluxoCaixa =fluxoCaixaController.consultar(clienteMB.getCliente().getIdcliente());
        if (listaFluxoCaixa!=null){
            return "exportFluxoCaixa";
        }
        return null;
    }
    
    public String gerarExcelPagamentos(){
        listaMovimentoBanco = null;
        titulo = "Relatório de Pagamentos";
        this.periodo = "Período : " + Formatacao.ConvercaoDataPadrao(dataInicio) 
            + "    " + Formatacao.ConvercaoDataPadrao(dataTermino);
        MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
        String sql = "Select m from Movimentobanco m where ";
        if ((dataInicio!=null) && (dataTermino!=null)){
            sql = sql + "m.dataCompensacao>='" +  Formatacao.ConvercaoDataSql(dataInicio) +
                    "' and m.dataCompensacao<='" + Formatacao.ConvercaoDataSql(dataTermino) + "' and ";
        }else {
            sql = sql + "m.compentencia='" + competencia + "' and ";
        }
        sql = sql + "m.cliente.idcliente=" + clienteMB.getCliente().getIdcliente();
        sql = sql + " and m.planocontas.idplanoContas<>" + clienteMB.getCliente().getContaRecebimento();
        sql = sql + " and m.planocontas.idplanoContas<>" + clienteMB.getCliente().getContaReceita();
        sql = sql + " Group by m.planocontas.idplanoContas, m.dataCompensacao, m.descricao, m.valorEntrada, m.valorSaida, m.planocontas.descricao, m.banco.nome, m.cliente.nomeFantasia,  m.compentencia ";
        sql = sql + " Order by m.planocontas.idplanoContas, m.dataCompensacao, m.descricao, m.valorEntrada, m.valorSaida, m.planocontas.descricao, m.banco.nome, m.cliente.nomeFantasia,  m.compentencia ";
        listaMovimentoBanco = movimentoBancoController.listaMovimento(sql);
        if (listaMovimentoBanco!=null){
            return "exportPagamentos";
        }
        return null;
    } 

}
