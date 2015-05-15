package br.com.financemate.ManageBean;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.model.Banco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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
        if (listaBancos == null) {
          gerarListaBanco();
        }
        return listaBancos;
    }

    public void setListaBancos(List<Banco> listaBancos) {
        this.listaBancos = listaBancos;
    }
    
     public void gerarListaBanco() {
        BancoController bancoController = new BancoController();
        if (nomeBanco == null) {
            nomeBanco = "";
        }
        listaBancos = bancoController.listar(nomeBanco);
        if (listaBancos == null) {
            listaBancos = new ArrayList<Banco>();
        }
       
    }
     public String salvar(){
        BancoController bancoController = new BancoController();
        bancoController.salvar(banco);
        banco = new Banco();
        return "consbanco";
    }
     
     public String cancelar(){
        return "consbanco";
    }
     
      public String novo(){
        return "cadbanco";
    }
}
    