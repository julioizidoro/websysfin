/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.Controller.MovimentoBancoController;
import br.com.financemate.Controller.PlanoContasController;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.facade.BancoFacade;
import br.com.financemate.model.Banco;
import br.com.financemate.model.Movimentobanco;
import br.com.financemate.model.Planocontas;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

/**
 *
 * @author Wolverine
 */
@Named("OutrosLancamentosMB")
@SessionScoped
public class OutrosLancamentoMB implements Serializable {

    @Inject
    UsuarioLogadoBean usuarioLogadoBean;
    @Inject
    private ClienteMB clienteMB;
    private List<Planocontas> listarPlanoContas;
    private Planocontas planocontas;
    private Movimentobanco movimentobanco;
    private List<Movimentobanco> listarMovimentobancos;
    private List<Banco> listaBanco;
    private String idBanco = "0";
    private Banco banco;
    String sql;
    private Date dataInicial;
    private Date dataFinal;
    private String idPlanoConta = "0";
    private String descricao;

    public OutrosLancamentoMB() {
        gerarDataInicial();
    }

    public List<Movimentobanco> getListarMovimentobancos() throws SQLException {
        return listarMovimentobancos;
    }

    public void setListarMovimentobancos(List<Movimentobanco> listarMovimentobancos) {
        this.listarMovimentobancos = listarMovimentobancos;
    }

    public Movimentobanco getMovimentobanco() {
        return movimentobanco;
    }

    public void setMovimentobanco(Movimentobanco movimentobanco) {
        this.movimentobanco = movimentobanco;
    }

    public List<Planocontas> getListarPlanoContas() {
        return listarPlanoContas;
    }

    public void setListarPlanoContas(List<Planocontas> listarPlanoContas) {
        this.listarPlanoContas = listarPlanoContas;
    }

    public Planocontas getPlanocontas() {
        return planocontas;
    }

    public void setPlanocontas(Planocontas planocontas) {
        this.planocontas = planocontas;
    }

    public String getIdPlanoConta() {
        return idPlanoConta;
    }

    public void setIdPlanoConta(String idPlanoConta) {
        this.idPlanoConta = idPlanoConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public List<Banco> getListaBanco() {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            carregarListaBanco();
        }
        return listaBanco;
    }

    public void setListaBanco(List<Banco> listaBanco) {
        this.listaBanco = listaBanco;
    }

    public String getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(String idBanco) {
        this.idBanco = idBanco;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public String novo() throws SQLException {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            movimentobanco = new Movimentobanco();
            carregarListaBanco();
            carregarListaPlanoContas();
            return "cadOutrosLancamentos";
        } else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Selecione um cliente");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }

    public String cancelar() {
        return "consOutrosLancamentos";
    }

    public String pesquisar() {
        gerarPesquisa();
        return "consOutrosLancamentos";
    }

    public void carregarListaPlanoContas() {
        PlanoContasController planoContasController = new PlanoContasController();
        listarPlanoContas = planoContasController.listar(clienteMB.getCliente().getTipoplanocontas().getIdtipoplanocontas());
        if (listarPlanoContas == null) {
            listarPlanoContas = new ArrayList<Planocontas>();
        }
    }

    public void carregarListaBanco() {
        gerarDataInicial();
        BancoController bancoController = new BancoController();
        listaBanco = bancoController.listar(clienteMB.getCliente().getIdcliente());
        if (listaBanco == null) {
            listaBanco = new ArrayList<Banco>();
        }
    }

    public String salvar() {
        MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
        movimentobanco.setCliente(clienteMB.getCliente());
        BancoController bancoController = new BancoController();
        Banco banco = bancoController.consultar(Integer.parseInt(idBanco));
        movimentobanco.setBanco(banco);
        PlanoContasController planoContasController = new PlanoContasController();
        Planocontas planocontas = planoContasController.consultar(Integer.parseInt(idPlanoConta));
        movimentobanco.setPlanocontas(planocontas);
        movimentobanco.setUsuario(usuarioLogadoBean.getUsuario());
        movimentoBancoController.salvar(movimentobanco);
        movimentobanco = new Movimentobanco();
        return "consOutrosLancamentos";
    }

    public String selecionarUnidade() {
        clienteMB.setPagina("consOutrosLancamentos");
        listaBanco = null;
        return "selecionarUnidade";
    }

    public String editar() throws SQLException {
        if (listarMovimentobancos != null) {
            for (int i = 0; i < listarMovimentobancos.size(); i++) {
                if (listarMovimentobancos.get(i).isSelecionado()) {
                    movimentobanco = listarMovimentobancos.get(i);
                    listarMovimentobancos.get(i).setSelecionado(false);
                    i = 100000;
                    return "cadOutrosLancamentos";
                }
            }
        }
        return "";
    }

    public void gerarDataInicial() {
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
        String sdataInicial = anocInicio + "-" + Formatacao.retornaDataInicia(mescInicio);
        String sdataFinal = anocFinal + "-" + Formatacao.retornaDataFinal(mescFinal);
        dataInicial = (Formatacao.ConvercaoStringData(sdataInicial));
        dataFinal = (Formatacao.ConvercaoStringData(sdataFinal));
    }

    public void gerarPesquisa() {
        if ((!idBanco.equalsIgnoreCase("0")) && (dataFinal != null) && (dataInicial != null)
                && (clienteMB.getCliente() != null)) {
            sql = "Select m from Movimentobanco m where m.banco.idbanco=" + Integer.parseInt(idBanco)
                    + "  and m.dataCompensacao>='" + Formatacao.ConvercaoDataSql(dataInicial)
                    + "'  and m.dataCompensacao<='" + Formatacao.ConvercaoDataSql(dataFinal)
                    + "' and m.cliente.idcliente=" + clienteMB.getCliente().getIdcliente();

            MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
            listarMovimentobancos = movimentoBancoController.listaMovimento(sql);
            if (listarMovimentobancos == null) {
                listarMovimentobancos = new ArrayList<Movimentobanco>();
            }
        }
    }

    public String excluir() throws SQLException {
        MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
        for (int i = 0; i < listarMovimentobancos.size(); i++) {
            if (listarMovimentobancos.get(i).isSelecionado()) {
                movimentoBancoController.excluir(listarMovimentobancos.get(i).getIdmovimentoBanco());
                gerarPesquisa();
                return "consOutrosLancamentos";
            }
        }
        return "";
    }
}
