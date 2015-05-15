/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.Controller.ContasPagarController;
import br.com.financemate.Controller.MovimentoBancoController;
import br.com.financemate.Controller.PlanoContasController;
import br.com.financemate.Controller.UsuarioController;
import br.com.financemate.Util.Formatacao;
import br.com.financemate.facade.NomeArquivoFacade;
import br.com.financemate.model.Banco;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contaspagar;
import br.com.financemate.model.Movimentobanco;
import br.com.financemate.model.Nomearquivo;
import br.com.financemate.model.Planocontas;
import br.com.financemate.model.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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
    private String usuarioAgendou;
    private String usuarioBaixou;
    private String usuarioAutorizou;
    private String usuarioCadastrou;
    private UploadedFile arquivo01;
    private UploadedFile arquivo02;
    private List<Planocontas> listaPlanoContas;
    private List<Banco> listaBanco;
    private String valorConta = "";
    private String idPlanoConta;
    private String idBanco;
    private List<Contaspagar> listaLiberadas;
    private String totalLiberadas;
    private Date dataLiberacao;
    private String nomeArquivo01;
    private String nomeArquivo02;
    private Nomearquivo nomeArquivo;
    private List<Nomearquivo> listaArquivo;
    private StreamedContent file01;
    private StreamedContent file02;
    private String messagem;

    
    public ContasPagarMB() {
        gerarDataInicia();
        nomeArquivo = new Nomearquivo();
    }

   
    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
    public Nomearquivo getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(Nomearquivo nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }

    public String getNomeArquivo01() {
        return nomeArquivo01;
    }

    public void setNomeArquivo01(String nomeArquivo01) {
        this.nomeArquivo01 = nomeArquivo01;
    }

    public String getNomeArquivo02() {
        return nomeArquivo02;
    }

    public void setNomeArquivo02(String nomeArquivo02) {
        this.nomeArquivo02 = nomeArquivo02;
    }

    public boolean isAutorizadas() {
        return autorizadas;
    }

    public List<Contaspagar> getListaLiberadas() {
        return listaLiberadas;
    }

    public void setListaLiberadas(List<Contaspagar> listaLiberadas) {
        this.listaLiberadas = listaLiberadas;
    }

    public List<Nomearquivo> getListaArquivo() {
        return listaArquivo;
    }

    public void setListaArquivo(List<Nomearquivo> listaArquivo) {
        this.listaArquivo = listaArquivo;
    }

    public String getTotalLiberadas() {
        return totalLiberadas;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public void setTotalLiberadas(String totalLiberadas) {
        this.totalLiberadas = totalLiberadas;
    }

    public void setAutorizadas(boolean autorizadas) {
        this.autorizadas = autorizadas;
    }

    public boolean isLiberadas() {
        return liberadas;
    }

    public String getUsuarioAgendou() {
        return usuarioAgendou;
    }

    public String getIdPlanoConta() {
        return idPlanoConta;
    }

    public void setIdPlanoConta(String idPlanoConta) {
        this.idPlanoConta = idPlanoConta;
    }

    public String getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(String idBanco) {
        this.idBanco = idBanco;
    }

    public String getValorConta() {
        return valorConta;
    }

    public void setValorConta(String valorConta) {
        this.valorConta = valorConta;
    }

    public String getUsuarioCadastrou() {
        return usuarioCadastrou;
    }

    public List<Planocontas> getListaPlanoContas() {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            carregarListaPlanoContas();
        }
        return listaPlanoContas;
    }

    public void setListaPlanoContas(List<Planocontas> listaPlanoContas) {
        this.listaPlanoContas = listaPlanoContas;
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

    public void setUsuarioCadastrou(String usuarioCadastrou) {
        this.usuarioCadastrou = usuarioCadastrou;
    }

    public void setUsuarioAgendou(String usuarioAgendou) {
        this.usuarioAgendou = usuarioAgendou;
    }

    public String getUsuarioBaixou() {
        return usuarioBaixou;
    }

    public void setUsuarioBaixou(String usuarioBaixou) {
        this.usuarioBaixou = usuarioBaixou;
    }

    public String getUsuarioAutorizou() {
        return usuarioAutorizou;
    }

    public void setUsuarioAutorizou(String usuarioAutorizou) {
        this.usuarioAutorizou = usuarioAutorizou;
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

    public UploadedFile getArquivo01() {
        return arquivo01;
    }

    public void setArquivo01(UploadedFile arquivo01) {
        this.arquivo01 = arquivo01;
    }

    public UploadedFile getArquivo02() {
        return arquivo02;
    }

    public void setArquivo02(UploadedFile arquivo02) {
        this.arquivo02 = arquivo02;
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
        String diaData = Formatacao.ConvercaoDataPadrao(data);
        for (int i = 0; i < listaContaPagar.size(); i++) {
            String vencData = Formatacao.ConvercaoDataPadrao(listaContaPagar.get(i).getDataVencimento());
            if (listaContaPagar.get(i).getDataVencimento().after(data)) {
                return "resources/img/bolaVerde.png";
            } else {
                if (!conta.getDataVencimento().after(data)) {
                    return "resources/img/bolaVermelha.png";
                } else {
                    if (diaData.equalsIgnoreCase(vencData)) {
                        return "resources/img/bolaAmarela.png";
                    }
                }
            }
        }
        return "resources/img/bolaVerde.png";
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

    public StreamedContent getFile01() {
        return file01;
    }

   
    public StreamedContent getFile02() {
        return file02;
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
        clienteMB.setCliente(new Cliente());
        listaBanco = new ArrayList<Banco>();
        contasPagar = new Contaspagar();
        contasPagar.setDataEnvio(new Date());
        return "cadConPagar";
    }

    public String editar() {
        for (int i = 0; i < listaContaPagar.size(); i++) {
            if (listaContaPagar.get(i).isSelecionado()) {
                if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("N")) {
                    contasPagar = listaContaPagar.get(i);
                    clienteMB.setCliente(contasPagar.getCliente());
                    carregarListaPlanoContas();
                    listaBanco = new ArrayList<Banco>();
                    listaContaPagar.get(i).setSelecionado(false);
                    valorConta = Formatacao.foramtarFloatString(contasPagar.getValor());
                    idBanco = String.valueOf(contasPagar.getBanco().getIdbanco());
                    idPlanoConta = String.valueOf(contasPagar.getPlanocontas().getIdplanoContas());
                    return "cadConPagar";
                }
            }
        }
        return null;
    }

    public String excluir() {
        ContasPagarController contasPagarController = new ContasPagarController();
        for (int i = 0; i < listaContaPagar.size(); i++) {
            if (listaContaPagar.get(i).isSelecionado()) {
                if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("N")) {
                    contasPagarController.excluir(listaContaPagar.get(i).getIdcontasPagar());
                }
            }
        }
        gerarListaContasPagar();
        return "consConPagar";
    }

    public String cancelar() {
        clienteMB.setCliente(new Cliente());
        return "consConPagar";
    }

    public String selecionarUnidade() {
        clienteMB.setPagina("consConPagar");
        return "selecionarUnidade";
    }

    public String selecionarUnidadeCadastro() {
        clienteMB.setPagina("cadConPagar");
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

    public void gerarDataInicia() {
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
            if (clienteMB.getCliente().getIdcliente() != null) {
                sql = sql + " and v.cliente.idcliente=" + clienteMB.getCliente().getIdcliente();
            } else {
                sql = sql + "  and v.cliente.visualizacao='Operacional' ";
            }
        } else {
            sql = sql + "  and v.cliente.visualizacao='Operacional' ";
        }
        sql = sql + " order by v.dataVencimento";
        gerarListaContasPagar();
    }

    public String pesquisar() {
        sql = "Select v from Contaspagar v where ";
        if (liberadas) {
            sql = sql + " v.contaPaga='S' and ";
        } else {
            sql = sql + " v.contaPaga='N' and ";
        }
        if (autorizadas) {
            sql = sql + " v.autorizarPagamento='S' and ";
        }
        if (clienteMB.getCliente() != null) {
            if (clienteMB.getCliente().getIdcliente() != null) {
                sql = sql + " v.cliente.idcliente=" + clienteMB.getCliente().getIdcliente() + " and ";
            } else {
                sql = sql + " v.cliente.visualizacao='Operacional' and ";
            }
        } else {
            sql = sql + " v.cliente.visualizacao='Operacional' and ";
        }
        if (liberadas) {
            sql = sql + "v.dataLiberacao>='" + Formatacao.ConvercaoDataSql(dataInicial)
                    + "' and v.dataLiberacao<='" + Formatacao.ConvercaoDataSql(dataFinal)
                    + "' order by v.dataLiberacao";
        } else {
            sql = sql + "v.dataVencimento>='" + Formatacao.ConvercaoDataSql(dataInicial)
                    + "' and v.dataVencimento<='" + Formatacao.ConvercaoDataSql(dataFinal)
                    + "' order by v.dataVencimento";
        }
        gerarListaContasPagar();
        return "consConPagar";
    }

    public void calcularTotal() {
        float totalvencida = 0.0f;
        float totalvencendo = 0.0f;
        float totalvencer = 0.0f;
        float total = 0.0f;
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

    public String operacaoUsuario() {
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario;
        boolean achouUser = false;
        for (int i = 0; i < listaContaPagar.size(); i++) {
            if (listaContaPagar.get(i).isSelecionado()) {
                listaContaPagar.get(i).setSelecionado(false);
                contasPagar = listaContaPagar.get(i);
                if ((listaContaPagar.get(i).getUsuarioAgendou() > 0) && (listaContaPagar.get(i).getUsuarioAgendou() != null)) {
                    usuario = usuarioController.consultar(listaContaPagar.get(i).getUsuarioAgendou());
                    if (usuario != null) {
                        usuarioAgendou = usuario.getNome();
                        achouUser = true;
                    }
                }
                if ((listaContaPagar.get(i).getUsuarioAutorizou() > 0) && (listaContaPagar.get(i).getUsuarioAutorizou() != null)) {
                    usuario = usuarioController.consultar(listaContaPagar.get(i).getUsuarioAutorizou());
                    if (usuario != null) {
                        usuarioAutorizou = usuario.getNome();
                        achouUser = true;
                    }
                }
                if ((listaContaPagar.get(i).getUsuarioBaixou() > 0) && (listaContaPagar.get(i).getUsuarioBaixou() != null)) {
                    usuario = usuarioController.consultar(listaContaPagar.get(i).getUsuarioBaixou());
                    if (usuario != null) {
                        usuarioBaixou = usuario.getNome();
                        achouUser = true;
                    }
                }
                if ((listaContaPagar.get(i).getUsuarioCadastrou() > 0) && (listaContaPagar.get(i).getUsuarioCadastrou() != null)) {
                    usuario = usuarioController.consultar(listaContaPagar.get(i).getUsuarioCadastrou());
                    if (usuario != null) {
                        usuarioCadastrou = usuario.getNome();
                        achouUser = true;
                    }
                }
                if (achouUser) {
                    return "operacoesUsuario";
                } else {
                    return "consConPagar";
                }
            }
        }
        return null;
    }

    public String imagem(Contaspagar conta) {
        if (conta.getAutorizarPagamento() == null) {
            return "resources/img/cancel.png";
        } else if (conta.getAutorizarPagamento().equalsIgnoreCase("s")) {
            return "resources/img/confirmar.png";
        } else {
            return "resources/img/cancel.png";
        }
    }

    public void carregarListaBanco() {
        if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
            BancoController bancoController = new BancoController();
            listaBanco = bancoController.listar(clienteMB.getCliente().getIdcliente());
            if (listaBanco == null) {
                listaBanco = new ArrayList<Banco>();
            }
        }
    }

    public void carregarListaPlanoContas() {
        PlanoContasController planoContasController = new PlanoContasController();
        listaPlanoContas = planoContasController.listar(clienteMB.getCliente().getTipoplanocontas().getIdtipoplanocontas());
        if (listaPlanoContas == null) {
            listaPlanoContas = new ArrayList<Planocontas>();
        }
    }

    public String salvar() {
        if (contasPagar.getIdcontasPagar() == null) {
            contasPagar.setAutorizarPagamento("N");
        }
        String msg = validarSalvar();
        if (msg.length() <= 5) {
            if (!idPlanoConta.equalsIgnoreCase("0")) {
                PlanoContasController planoContasController = new PlanoContasController();
                Planocontas plano = planoContasController.consultar(Integer.parseInt(idPlanoConta));
                contasPagar.setPlanocontas(plano);
            }
            if (!idBanco.equalsIgnoreCase("0")) {
                BancoController bancoController = new BancoController();
                Banco banco = bancoController.consultar(Integer.parseInt(idBanco));
                contasPagar.setBanco(banco);
            }
            contasPagar.setCliente(clienteMB.getCliente());
            contasPagar.setContaPaga("N");
            contasPagar.setMovimentoBanco(0);
            contasPagar.setUsuarioAgendou(0);
            contasPagar.setUsuarioAutorizou(0);
            contasPagar.setUsuarioBaixou(0);
            contasPagar.setUsuarioCadastrou(usuarioLogadoBean.getUsuario().getIdusuario());
            String data = Formatacao.ConvercaoDataPadrao(new Date()) + "_" + Formatacao.foramtarHoraString();
            contasPagar.setDataHoraCadastrou(data);
            if (valorConta.length() > 0) {
                contasPagar.setValor(Formatacao.ConvercaoMonetariaFloat(valorConta));
            } else {
                contasPagar.setValor(0.0f);
            }
            contasPagar.setVendaComissao(0);

            ContasPagarController contasPagarController = new ContasPagarController();
            contasPagar = contasPagarController.salvar(contasPagar);
            salvarNomeArquivo();
            contasPagar = new Contaspagar();

            clienteMB.setCliente(new Cliente());
            gerarListaContasPagar();

            return "consConPagar";
        } else {
            FacesMessage mensagem = new FacesMessage("Sucesso! ", msg);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }

    public void upload01(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " upload.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setArquivo01(event.getFile());
        setNomeArquivo01(event.getFile().getFileName());
    }

    public void upload02(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Sucesso! ", event.getFile().getFileName() + " upload.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setArquivo02(event.getFile());
        setNomeArquivo02(event.getFile().getFileName());
    }

    public void salvarArquivoAnexado(String nome, UploadedFile arquivo) {
        try {
            InputStream in = arquivo.getInputstream();
            OutputStream out = new FileOutputStream(new File(nome));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salvarNomeArquivo() {
        Nomearquivo nomeArquivo = new Nomearquivo();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String nome01 = servletContext.getRealPath("") + File.separator + "arquivo" + File.separator;
        nomeArquivo.setCaminho(nome01);
        if (arquivo01 != null) {
            nome01 = nome01 + String.valueOf(contasPagar.getIdcontasPagar()) + "_" + String.valueOf(contasPagar.getCliente().getIdcliente()) + "_" + arquivo01.getFileName();
            salvarArquivoAnexado(nome01, arquivo01);
            nomeArquivo.setNomearquivo01(arquivo01.getFileName());
        }

        String nome02 = servletContext.getRealPath("") + File.separator + "arquivo" + File.separator;
        //String nome02 = "C:" + File.separator + "arquivo" + File.separator;
        if (arquivo02 != null) {
            nome02 = nome02 + String.valueOf(contasPagar.getIdcontasPagar()) + "_" + String.valueOf(contasPagar.getCliente().getIdcliente()) + "_" + arquivo02.getFileName();
            salvarArquivoAnexado(nome02, arquivo02);
            nomeArquivo.setNomearquivo02(arquivo02.getFileName());
        }
        try {
            NomeArquivoFacade nomeArquivoFacade = new NomeArquivoFacade();
            nomeArquivo.setContaspagar(contasPagar);
            nomeArquivoFacade.salvar(nomeArquivo);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String liberarContasPagar() {
        totalLiberadas = "0,00";
        Float valorSoma = 0.0f;
        dataLiberacao = new Date();
        listaLiberadas = new ArrayList<Contaspagar>();
        for (int i = 0; i < listaContaPagar.size(); i++) {
            if (listaContaPagar.get(i).isSelecionado()) {
                if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("S")) {
                    listaLiberadas.add(listaContaPagar.get(i));
                    valorSoma = valorSoma + listaContaPagar.get(i).getValor();
                }
            }
        }
        totalLiberadas = Formatacao.foramtarFloatString(valorSoma);
        gerarListaContasPagar();
        return "liberacaoConPagar";
    }

    public String salvarContasLiberadas() {
        ContasPagarController contasPagarController = new ContasPagarController();
        for (int i = 0; i < listaLiberadas.size(); i++) {
            salvarContaLiberadasMovimentoBanco(listaLiberadas.get(i));
        }
        gerarListaContasPagar();
        listaLiberadas = null;
        dataLiberacao = null;
        return "consConPagar";
    }

    public void salvarContaLiberadasMovimentoBanco(Contaspagar conta) {
        conta.setDataLiberacao(dataLiberacao);
        conta.setContaPaga("S");
        conta.setUsuarioBaixou(usuarioLogadoBean.getUsuario().getIdusuario());
        String data = Formatacao.ConvercaoDataPadrao(new Date()) + "_" + Formatacao.foramtarHoraString();
        conta.setDataHoraLiberou(data);
        Movimentobanco movimentoBanco = new Movimentobanco();
        movimentoBanco.setBanco(conta.getBanco());
        movimentoBanco.setCliente(conta.getCliente());
        movimentoBanco.setDataVencimento(conta.getDataVencimento());
        movimentoBanco.setDataRegistro(new Date());
        movimentoBanco.setPlanocontas(conta.getPlanocontas());
        movimentoBanco.setUsuario(usuarioLogadoBean.getUsuario());
        movimentoBanco.setValorEntrada(0.0f);
        movimentoBanco.setValorSaida(conta.getValor());
        movimentoBanco.setDataRegistro(new Date());
        movimentoBanco.setDataCompensacao(conta.getDataCompensacao());
        movimentoBanco.setTipoDocumento(conta.getTipoDocumento());
        movimentoBanco.setDescricao(conta.getDescricao());
        movimentoBanco.setCompentencia(conta.getCompetencia());
        MovimentoBancoController movimentoBancoController = new MovimentoBancoController();
        movimentoBanco = movimentoBancoController.salvar(movimentoBanco);
        conta.setMovimentoBanco(movimentoBanco.getIdmovimentoBanco());
        ContasPagarController contasPagarController = new ContasPagarController();
        contasPagarController.salvar(conta);
    }

    public String autorizarPagamentoContasPagar() {
        ContasPagarController contasPagarController = new ContasPagarController();
        for (int i = 0; i < listaContaPagar.size(); i++) {
            if (listaContaPagar.get(i).isSelecionado()) {
                if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("N")) {
                    String data = Formatacao.ConvercaoDataPadrao(new Date()) + "_" + Formatacao.foramtarHoraString();
                    if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("S")) {
                        listaContaPagar.get(i).setAutorizarPagamento("N");
                        listaContaPagar.get(i).setUsuarioAutorizou(0);
                        listaContaPagar.get(i).setDataHoraAutorizou("");
                    } else {
                        listaContaPagar.get(i).setAutorizarPagamento("S");
                        listaContaPagar.get(i).setUsuarioAutorizou(usuarioLogadoBean.getUsuario().getIdusuario());
                        listaContaPagar.get(i).setDataHoraAutorizou(data);
                    }

                    contasPagarController.salvar(listaContaPagar.get(i));
                }
            }
        }
        gerarListaContasPagar();
        return "consConPagar";
    }

    
    public String gerarListaArquivo() {
        NomeArquivoFacade nomeArquivoFacade = new NomeArquivoFacade();
        for (int i = 0; i < listaContaPagar.size(); i++) {
            if (listaContaPagar.get(i).isSelecionado()) {
                try {
                    nomeArquivo = nomeArquivoFacade.listar(listaContaPagar.get(i).getIdcontasPagar());
                    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                    String localArquivo = servletContext.getRealPath("") + File.separator +  "arquivo" + File.separator;
                    if (nomeArquivo != null) {
                        if (nomeArquivo.getNomearquivo01() != null) {
                            String nomepadrao = String.valueOf(listaContaPagar.get(i).getIdcontasPagar()) + "_" + 
                                    String.valueOf(listaContaPagar.get(i).getCliente().getIdcliente()) + "_" + nomeArquivo.getNomearquivo01();
                            nomepadrao = "/arquivo/" + nomepadrao;
                            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(nomepadrao);
                             file02 = new DefaultStreamedContent(stream, acharExtensao(nomeArquivo.getNomearquivo01()), nomeArquivo.getNomearquivo01());
                        }
                        if (nomeArquivo.getNomearquivo02() != null) {
                            System.out.println(nomeArquivo.getNomearquivo02());
                            String nomepadrao = String.valueOf(listaContaPagar.get(i).getIdcontasPagar()) + "_" + 
                                    String.valueOf(listaContaPagar.get(i).getCliente().getIdcliente()) + "_" + nomeArquivo.getNomearquivo02();
                            nomepadrao = "/arquivo/" + nomepadrao;
                            InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(nomepadrao);
                             file02 = new DefaultStreamedContent(stream, acharExtensao(nomeArquivo.getNomearquivo02()), nomeArquivo.getNomearquivo02());
                        }
                        return "mostrarArquivos";
                    }
                 } catch (SQLException ex) {
                     Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }  
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Selecione um Arquivo", "")); 
        return"";
    }
    
    public String acharExtensao(String nome){
        int i = nome.length();
        i = i - 3;
        String extensao = nome.substring(i);
        if (extensao.equalsIgnoreCase("jpg")){
            extensao = "img/jpg";
        }else if (extensao.equalsIgnoreCase("png")){
            extensao = "img/png";
        }else extensao = "application/pdf";
        
        return extensao;
    }
    
    public String limparCosulta(){
        gerarDataInicia();
        gerarSqlInicial();
        return "consConPagar";   
    }
    
    public String validarSalvar(){
        String msg="";
        if (clienteMB.getCliente()==null){
            msg = msg + "Campo cliente não selecionado\r\n";
        }
        if (contasPagar.getTipoDocumento().equalsIgnoreCase("sn")){
            msg = msg + "Campo tipo documento selecionado\r\n";
        }
        if (contasPagar.getDataVencimento()==null){
            msg = msg + "Campo data vencimento não preenchida\r\n";
        }
        if (idPlanoConta.equalsIgnoreCase("sn")){
            msg = msg + "Campo plano de contas não selecionado\r\n";
        }
        if (idBanco.equalsIgnoreCase("sn")){
            msg = msg + "Campo conta não selecionado\r\n";
        }
        if (contasPagar.getFormaPagamento().equalsIgnoreCase("sn")){
            msg = msg + "Campo forma de pagamento não selecionado\r\n";
        }
        return msg;
    }
    
    
    
    
}