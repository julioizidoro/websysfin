/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.ContasPagarController;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contaspagar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named("ContasPagarMB")
@SessionScoped
public class ContasPagarMB implements Serializable {

    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    @Inject
    private ClienteMB clienteMB;
    private List<Contaspagar> listaContaPagar;
    private Contaspagar contasPagar;
    private Date dataInicial;
    private Date dataFinal;
    private List<Cliente> listaClientes;
    private String stotalvencida;
    private String stotalvencendo;
    private String stotal;
    private String stotalvencer;
    private boolean autorizadas;
    private boolean liberadas;
    private String sql;
    private String imagemAutorizado = " ";
    
    public ContasPagarMB() {
        gerarDataInicia();
    }


    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public boolean isAutorizadas() {
        return autorizadas;
    }

    public void setAutorizadas(boolean autorizadas) {
        this.autorizadas = autorizadas;
    }

    public boolean isLiberadas() {
        return liberadas;
    }

    public Contaspagar getContasPagar() {
        return contasPagar;
    }

    public String getImagemAutorizado() {
        return imagemAutorizado;
    }

    public void setImagemAutorizado(String imagemAutorizado) {
        this.imagemAutorizado = imagemAutorizado;
    }

    public void setContasPagar(Contaspagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public void setLiberadas(boolean liberadas) {
        this.liberadas = liberadas;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getStotalvencida() {
        return stotalvencida;
    }

    public void setStotalvencida(String stotalvencida) {
        this.stotalvencida = stotalvencida;
    }

    public String getStotalvencendo() {
        return stotalvencendo;
    }

    public void setStotalvencendo(String stotalvencendo) {
        this.stotalvencendo = stotalvencendo;
    }

    public String getStotal() {
        return stotal;
    }

    public void setStotal(String stotal) {
        this.stotal = stotal;
    }

    public String getStotalvencer() {
        return stotalvencer;
    }

    public void setStotalvencer(String stotalvencer) {
        this.stotalvencer = stotalvencer;
    }

  
    public String verStatus(Contaspagar conta) {
        Date data = new Date();
        if (conta.getDataLiberacao() != null) {
            return "verde";
        } else {
            if (!conta.getDataVencimento().after(data)) {
                return "vermelho";
            } else {
                if (conta.getDataAgendamento() == null) {
                    return "amarelo";
                } else {
                    return null;
                }
            }
        }

    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    
    public List<Contaspagar> getListaContaPagar() {
        if (listaContaPagar == null) {
            gerarSqlInicial();
        }
        return listaContaPagar;
    }

    public void setListaContaPagar(List<Contaspagar> listaContaPagar) {
        this.listaContaPagar = listaContaPagar;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String novo() {
        return "cadConPagar";
    }

    public String cancelar() {
        return "consConPagar";
    }

    public String selecionarUnidade() {
        clienteMB.setPagina("consConPagar");
        return "selecionarUnidade";
    }

    public void gerarListaContasPagar() {
        ContasPagarController contasPagarController = new ContasPagarController();
        listaContaPagar = contasPagarController.listar(sql);
        if (listaContaPagar == null) {
            listaContaPagar = new ArrayList<Contaspagar>();
        }
        for (int i = 0; i < listaContaPagar.size(); i++) {
            listaContaPagar.get(i).setStatus(verStatus(listaContaPagar.get(i)));
        }
        calcularTotal();
    }
    
    public void gerarDataInicia(){
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        String mesString = data.substring(3, 5);
        String anoString = data.substring(6, 10);
        int mesInicio = Integer.parseInt(mesString);
        int anoInicio = Integer.parseInt(anoString);
        int mescInicio;
        int mescFinal;
        int anocInicio = 0;
        int anocFinal = 0;
        if (mesInicio == 1) {
            mescInicio = 12;
            anocInicio = anoInicio - 1;
        } else {
            mescInicio = mesInicio - 1;
            anocInicio = anoInicio;
        }
        if (mesInicio == 12) {
            mescFinal = 1;
            anocFinal = anoInicio + 1;
        } else {
            mescFinal = mesInicio + 1;
            anocFinal = anoInicio;
        }
        String dInicial = anocInicio + "-" + Formatacao.retornaDataInicia(mescInicio);
        String dFinal = anocFinal + "-" + Formatacao.retornaDataFinal(mescFinal);
        dataInicial = Formatacao.ConvercaoStringData(dInicial);
        dataFinal = Formatacao.ConvercaoStringData(dFinal);
    }

    public void gerarSqlInicial() {
        sql = " Select v from Contaspagar v where v.dataVencimento>='" + Formatacao.ConvercaoDataSql(dataInicial)
                + "' and v.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal) + "' and v.contaPaga='N' ";
        if (clienteMB.getCliente() != null) {
            if (clienteMB.getCliente().getIdcliente()!=null){
                sql = sql + " and v.cliente.idcliente=" + clienteMB.getCliente().getIdcliente();
            }else sql = sql + "  and v.cliente.visualizacao='Operacional' ";
        } else {
            sql = sql + "  and v.cliente.visualizacao='Operacional' ";
        }
        sql = sql + " order by v.dataVencimento";
        gerarListaContasPagar();
    }
    
    public String pesquisar(){
        sql = "Select v from Contaspagar v where ";
        if (liberadas){
            sql = sql + " v.contaPaga='S' and ";
        }else sql = sql + " v.contaPaga='N' and ";
        if (autorizadas){
            sql = sql + " v.autorizarPagamento='S' and ";
        }
        if (clienteMB.getCliente()!=null){
            if (clienteMB.getCliente().getIdcliente()!=null){
                sql = sql + " v.cliente.idcliente=" + clienteMB.getCliente().getIdcliente() + " and ";
            }else sql = sql + " v.cliente.visualizacao='Operacional' and ";    
        }else {
            sql = sql + " v.cliente.visualizacao='Operacional' and ";
        }
        if (liberadas){
            sql = sql + "v.dataLiberacao>='" + Formatacao.ConvercaoDataSql(dataInicial) + 
                "' and v.dataLiberacao<='" + Formatacao.ConvercaoDataSql(dataFinal) + 
                "' order by v.dataLiberacao";
        }else {
            sql = sql + "v.dataVencimento>='" + Formatacao.ConvercaoDataSql(dataInicial) + 
                "' and v.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal) + 
                "' order by v.dataVencimento";
        }
        gerarListaContasPagar();
        return "consConPagar";
    }
    
    public void calcularTotal() {
        float totalvencida = 0.0f;
        float totalvencendo = 0.0f;
        float totalvencer = 0.0f;
        float total =0.0f;
        Date data = new Date();
        String diaData = Formatacao.ConvercaoDataPadrao(data);
        for (int i = 0; i < listaContaPagar.size(); i++) {
            String vencData = Formatacao.ConvercaoDataPadrao(listaContaPagar.get(i).getDataVencimento());
            if (diaData.equalsIgnoreCase(vencData)) {
                totalvencendo = totalvencendo + listaContaPagar.get(i).getValor();
            } else if (listaContaPagar.get(i).getDataVencimento().before(data)) {
                totalvencida = totalvencida + listaContaPagar.get(i).getValor();
            } else if (listaContaPagar.get(i).getDataVencimento().after(data)) {
                totalvencer = totalvencer + listaContaPagar.get(i).getValor();
            }
        }
        total = totalvencida + totalvencer + totalvencendo;
        setStotal(Formatacao.foramtarFloatString(total));
        stotalvencendo = Formatacao.foramtarFloatString(totalvencendo);
        stotalvencer = Formatacao.foramtarFloatString(totalvencer);
        stotalvencida = Formatacao.foramtarFloatString(totalvencida);
    }
    
    public String operacaoUsuario(){
        for(int i=0;i<listaContaPagar.size();i++){
            if (listaContaPagar.get(i).getSelecionado()){
                contasPagar = listaContaPagar.get(i);
                return "operacoesUsuario";
            }
        }
        return null;
    }
    
    public String imagem(Contaspagar conta) {
        if (conta.getAutorizarPagamento()==null){
            return  "resources/img/cancel.png";
        }else if (conta.getAutorizarPagamento().equalsIgnoreCase("s")) {
            return "resources/img/confirmar.png";
        } else {
            return  "resources/img/cancel.png";
        }
    }
}