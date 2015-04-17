package br.com.financemate.Relatorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.primefaces.model.DefaultStreamedContent;

public class ExecutorRelatorio  {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;
	
	private boolean relatorioGerado;
	
	public ExecutorRelatorio(String caminhoRelatorio,
			HttpServletResponse response, Map<String, Object> parametros,
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;
		
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            try {
                    try {
                        execute();
                    } catch (Exception ex) {
                        Logger.getLogger(ExecutorRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (Exception ex) {
                Logger.getLogger(ExecutorRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	public void execute() throws  Exception  {
		
                        FacesContext context = FacesContext.getCurrentInstance();
                        String camimhoRelatorio = context.getExternalContext().getRealPath("relatorios");
                        String caminhoArquivoJasper = camimhoRelatorio + File.separator + "reportpagamentovencidas.jasper";
                        String caminhoArquivoRelatorio=null;
                         this.parametros.put("REPORT_CONNECTION",getConexao());
                         InputStream relatorioStream = this.getClass().getResourceAsStream(caminhoArquivoJasper);
                       JasperReport relatorioJaspre = (JasperReport) JRLoader.loadObject(caminhoArquivoJasper);
                       JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJaspre, parametros);
                       JRExporter exportador = new JRPdfExporter();
                       String caminhoArquivoRelativo = camimhoRelatorio + File.separator + nomeArquivoSaida;
                       File arquivoGerado = new File(caminhoArquivoRelativo);
                       	exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, arquivoGerado);
			exportador.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
                        exportador.exportReport();
                        arquivoGerado.deleteOnExit();
//			InputStream conteudoRelarorio = new FileInputStream(arquivoGerado);
//                        DefaultStreamedContent arquivoRetorno = new DefaultStreamedContent(conteudoRelarorio, "application/pdf", nomeArquivoSaida);
                        
                        
                  
//			
//			if (this.relatorioGerado) {
//				
//				
//				response.setContentType("application/pdf");
//				response.setHeader("Content-Disposition", "attachment; filename=\"" 
//						+ this.nomeArquivoSaida  + "\"");
//				
//				exportador.exportReport();
//			}
		
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
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
