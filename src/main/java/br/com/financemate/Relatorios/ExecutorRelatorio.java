package br.com.financemate.Relatorios;

import br.com.financemate.Connection.ConectionFactory;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;


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
                execute();
            } catch (Exception ex) {
                Logger.getLogger(ExecutorRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	
	public void execute() throws SQLException, Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
           //     String reportName =  contexto.getRealPath(this.caminhoRelatorio);
                //verificarArquivo(reportName);
                byte[] bytes = null;
                JasperPrint arquivoPrint = null;
                InputStream reportStream= session.getServletContext().getResourceAsStream(this.caminhoRelatorio);
                Connection conn = getConexao();
                arquivoPrint = JasperFillManager.fillReport(reportStream, parametros, conn);
                
                bytes = JasperRunManager.runReportToPdf(reportStream, parametros, conn);

                if (bytes != null && bytes.length > 0 && arquivoPrint != null && arquivoPrint.getPages().size() > 0) {
                    response.setHeader("Content-Disposition", "attachment; filename=" + this.nomeArquivoSaida + "_" + new Date().getTime() + ".pdf");
                    response.setContentType("application/pdf");
                    response.setContentLength(bytes.length);
                    ServletOutputStream outputStream;
                }
    }

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}
        
        private Connection getConexao() throws Exception {
//            InitialContext context = new InitialContext();  
//            Context ct = (Context)context.lookup("conexao");  
//            DataSource dataSource = (DataSource) ct.lookup("jdbc/websysfinDS");
//            if (dataSource.getConnection()==null){
//                System.out.println("nulo");
//            }
            Connection conn = ConectionFactory.getConexao();
            System.out.println(conn);
            if (conn.isValid(0)){
                System.out.println("tudo ok");
            }else System.out.println("erro conexao");
            return conn;
    }
        
    public void verificarArquivo(String arquivo){
        File file = new File(arquivo);
        if (file.exists()){
            System.out.println("Arquivo existe");
        }else System.out.println("arquivo nulo");
    }

}
