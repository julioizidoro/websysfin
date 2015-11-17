package br.com.financemate.ManageBean.banco;

import br.com.financemate.ManageBean.UsuarioLogadoBean;
import br.com.financemate.facade.BancoFacade;
import br.com.financemate.model.Banco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class BancoMB implements Serializable {

    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    private Banco banco;
    private List<Banco> listaBanco;
    
    @PostConstruct
    public void init(){
        gerarListaBanco();
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Banco> getListaBanco() {
        return listaBanco;
    }

    public void setListaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }
    
    
    public void gerarListaBanco(){
        BancoFacade bancoFacade = new BancoFacade();
        listaBanco = bancoFacade.listar();
        if (listaBanco==null){
            listaBanco = new ArrayList<Banco>();
        }
    } 
    
    public String novoBanco() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadBanco");
        return "";
    }
    
     public String editar(Banco banco ){
         if (banco!=null){
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("banco", banco);       
            RequestContext.getCurrentInstance().openDialog("cadBanco");
            
        }
        return "";
   }
    
      public void retornoDialog(SelectEvent event) {
         gerarListaBanco();
     }
    
}
