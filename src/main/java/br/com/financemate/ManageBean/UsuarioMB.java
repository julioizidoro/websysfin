package br.com.financemate.ManageBean;

import br.com.financemate.Controller.UsuarioController;
import br.com.financemate.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("UsuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {
    
    private Usuario usuario;
    private List<Usuario> listarUsuario;
    private ClienteMB clienteMB;
    private UsuarioLogadoBean usuarioLogadoBean;
    private String nomeUsuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListarUsuario() {
        if (listarUsuario==null){
            gerarListaUsuario();
        }
        return listarUsuario;
    }

    public void setListarUsuario(List<Usuario> listarUsuario) {
        this.listarUsuario = listarUsuario;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    
    
    public void gerarListaUsuario() {
        UsuarioController usuarioController = new UsuarioController();
        if (nomeUsuario == null) {
            nomeUsuario = "";
        }
        listarUsuario = usuarioController.listar(nomeUsuario);
        if (listarUsuario == null) {
            listarUsuario = new ArrayList<Usuario>();
        }
    }
    
    public String novo() {
            usuario = new Usuario();
            return "cadTipoUsuario";
    }
    
    public String editar(){
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getAusuario()) {
            if (listarUsuario != null) {
                for (int i = 0; i < listarUsuario.size(); i++) {
                    if (listarUsuario.get(i).isSelecionado()) {
                        usuario = listarUsuario.get(i);
                        listarUsuario.get(i).setSelecionado(false);
                        i = 100000;
                        return "cadTipoUsuario";
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
    
    public String selecionar(){
        clienteMB.setPagina("cadTipoUsuario");
        return "selecionarUnidade";
    }
    
    public String salvar(){
         if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIusuario()){
            UsuarioController usuarioController = new UsuarioController();
            usuarioController.salvar(usuario);
            usuario = new Usuario();
            return "consTipoUsuario";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
            
    }
    public String cancelar(){
        return "consTipoUsuario";
    }
    
    public String pesquisarnome(){
        gerarListaUsuario();
        return "consTipoUsuario";
    
    }  
}
