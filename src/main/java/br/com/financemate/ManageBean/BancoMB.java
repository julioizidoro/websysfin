package br.com.financemate.ManageBean;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.model.Banco;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("BancoMB")
@SessionScoped
public class BancoMB  implements Serializable {
     @Inject
     private UsuarioLogadoBean usuarioLogadoBean;
     private Banco banco;
     private String nomeBanco;
     private List<Banco> listaBancos;
     @Inject
     private ClienteMB clienteMB;
     

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Banco> getListaBancos() {
        if (listaBancos==null){
            gerarListaBanco();
        }
        return listaBancos;
    }

    public void setListaBancos(List<Banco> listaBancos) {
        this.listaBancos = listaBancos;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }
    
     public void gerarListaBanco() {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            BancoController bancoController = new BancoController();
            listaBancos = bancoController.listar(clienteMB.getCliente().getIdcliente());
            if (listaBancos == null) {
                listaBancos = new ArrayList<Banco>();
            }
        }
    }
     public String salvar(){
         if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIbanco()){
            BancoController bancoController = new BancoController();
            banco.setCliente(clienteMB.getCliente());
            bancoController.salvar(banco);
            banco = new Banco();
            return "consbanco";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }
     public String cancelar(){
        return "consbanco";
    }
     
      public String novo() {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIbanco()) {
            if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
                banco = new Banco();
                return "cadbanco";
            } else {
                FacesMessage mensagem = new FacesMessage("Erro! ", "Selecione um cliente");
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
                return "";
            }
        } else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }

    }
    public String pesquisar(){
       return "consbanco";
    }
    public String selecionarUnidade(){
        clienteMB.setPagina("consbanco");
        listaBancos = null;
        return "selecionarUnidade";
    }
    public String editar() throws SQLException {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getAbanco()) {
            if (listaBancos != null) {
                for (int i = 0; i < listaBancos.size(); i++) {
                    if (listaBancos.get(i).isSelecionado()) {
                        banco = listaBancos.get(i);
                        listaBancos.get(i).setSelecionado(false);
                        i = 100000;
                        return "cadbanco";
                    }
                }
            }
            return "";
        } else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }
}
    