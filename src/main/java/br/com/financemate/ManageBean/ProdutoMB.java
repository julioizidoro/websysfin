package br.com.financemate.ManageBean;

import br.com.financemate.Controller.ProdutoController;
import br.com.financemate.model.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named("ProdutoMB")
@SessionScoped
public class ProdutoMB implements Serializable {
    @Inject private ClienteMB clienteMB;
    private Produto produto;
    private List<Produto> listaProduto;
    private UsuarioLogadoBean usuarioLogadoBean;

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProduto() {
        if (listaProduto==null){
            gerarListaProduto();
        }
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }
    
    
    
    public String cancelar(){
        return "consProduto";
    }
    
    public String novo(){
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIproduto()){
            if ((clienteMB.getCliente()!=null) && (clienteMB.getCliente().getIdcliente()!=null)){
                produto = new Produto();
                return "cadProduto";
            }else {
                FacesMessage mensagem = new FacesMessage("Erro! ", "Selecione um cliente");
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
                return "";
            }
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }
    public String salvar(){
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIproduto()){
            ProdutoController produtoController = new ProdutoController();
            produto.setCliente(clienteMB.getCliente());
            produtoController.salvar(produto);
            produto = new Produto();
            gerarListaProduto();
            return "consProduto";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }
    
    public void gerarListaProduto() {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            ProdutoController produtoController = new ProdutoController();
            listaProduto = produtoController.listar(clienteMB.getCliente().getIdcliente());
            if (listaProduto == null) {
                listaProduto = new ArrayList<Produto>();
            }
        }
    }
    public String editar(){
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getAproduto()){
            if (listaProduto!=null){
                for(int i=0;i<listaProduto.size();i++){
                    if (listaProduto.get(i).isSelecionado()){
                        produto = listaProduto.get(i);
                        listaProduto.get(i).setSelecionado(false);
                        i=100000;
                        return "cadProduto";
                    }
                }
            }
            return  "";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }
    
    public String voltar(){
        return "principal";
    }
    
    public String selecionarUnidade(){
        clienteMB.setPagina("consProduto");
        listaProduto = null;
        return "selecionarUnidade";
    }
}
