/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Util;

import br.com.financemate.Connection.ConectionFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Wolverine
 */
public class GerarRelatorio {
    
    public void gerarRelatorioDSPDF(String caminhoRelatorio, Map parameters, JRDataSource jrds, String nomeArquivo ) throws JRException, IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        caminhoRelatorio = servletContext.getRealPath(caminhoRelatorio);  
        
        JasperPrint arquivoPrint=null;
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=\"" + nomeArquivo + ".pdf\"");
        arquivoPrint = JasperFillManager.fillReport(caminhoRelatorio, parameters, jrds);
        JasperExportManager.exportReportToPdfStream(arquivoPrint, response.getOutputStream());
        facesContext.getApplication().getStateManager().saveView(facesContext);
        facesContext.renderResponse();
        facesContext.responseComplete();
    }
    
    public void gerarRelatorioSqlPDF(String caminhoRelatorio, Map parameters, String nomeArquivo, String subDir ) throws JRException, IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        caminhoRelatorio = servletContext.getRealPath(caminhoRelatorio);  
        Connection conn = ConectionFactory.getConexao();
        if (subDir!=null){
            subDir = servletContext.getRealPath(subDir);
            subDir = subDir + File.separator + "a";
            subDir = subDir.substring(0, (subDir.length()-1));
            System.out.println(subDir);
            parameters.put("SUBREPORT_DIR", subDir);
        }
        JasperPrint arquivoPrint=null;
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=\"" + nomeArquivo + ".pdf\"");
        arquivoPrint = JasperFillManager.fillReport(caminhoRelatorio, parameters, conn);
        JasperExportManager.exportReportToPdfStream(arquivoPrint, response.getOutputStream());
        facesContext.getApplication().getStateManager().saveView(facesContext);
        
        facesContext.renderResponse();
        facesContext.responseComplete();
    }
    
    public void gerarRelatorioSqlPDF2(String caminhoRelatorio, Map parameters, String nomeArquivo, String subDir ) throws JRException, IOException{
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Connection conn = ConectionFactory.getConexao();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        try {
            InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(caminhoRelatorio);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameters, conn);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            response.getOutputStream().close();
        }
        facesContext.responseComplete();
    }
}
