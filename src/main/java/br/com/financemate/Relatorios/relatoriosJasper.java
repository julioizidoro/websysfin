/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Relatorios;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Acer
 */
public final class relatoriosJasper {

    private String url;
    private Map parameters = new HashMap();
    
    
    

    public relatoriosJasper(Map parameters) {
        
        this.parameters = parameters;
        gerarRelatorios();
    }
    
    
    public void gerarRelatorios(){
       // URL is = this.getClass().getClassLoader().getResource(url);
        Connection conn = null;
        try {

            
          //  JasperReport relatoriosJasper = (JasperReport) JRLoader.loadObject(is);
            this.parameters.put("REPORT_CONNECTION", getConexao());
            
            //JasperPrint jasperPrint = JasperFillManager.fillReport(relatoriosJasper, this.parameters);
            JasperRunManager.runReportToPdfFile("relatorios/reportcontasvencidas.jasper", parameters); 
            JasperViewer.viewReport("reportcontasvencidas.pdf", false);
            //JasperViewer jrViewer = new JasperViewer(jasperPrint, true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório " + ex);
            ex.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }
    
    private Connection getConexao() throws Exception {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/websysfinDS");
            if (dataSource.getConnection()==null){
                System.out.println("nulo");
            }
            Connection conn =dataSource.getConnection();
            return conn;
    }

}
