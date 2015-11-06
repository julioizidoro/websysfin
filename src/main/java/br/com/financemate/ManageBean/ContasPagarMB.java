/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean;


import br.com.financemate.Util.Formatacao;
import br.com.financemate.facade.BancoFacade;
import br.com.financemate.facade.ContasPagarFacade;
import br.com.financemate.facade.MovimentoBancoFacade;
import br.com.financemate.facade.NomeArquivoFacade;
import br.com.financemate.facade.PlanoContasFacade;
import br.com.financemate.model.Banco;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Contaspagar;
import br.com.financemate.model.Movimentobanco;
import br.com.financemate.model.Nomearquivo;
import br.com.financemate.model.Planocontas;
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
        String vencData = Formatacao.ConvercaoDataPadrao(conta.getDataVencimento());
        if (conta.getDataVencimento().after(data)) {
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
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIcontaspagar()){
            clienteMB.setCliente(new Cliente());
            listaBanco = new ArrayList<Banco>();
            contasPagar = new Contaspagar();
            contasPagar.setDataEnvio(new Date());
            return "cadConPagar";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
    }

    public String editar() {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getAcontaspargar()){
            clienteMB.setCliente(new Cliente());
            listaBanco = new ArrayList<Banco>();
            contasPagar = new Contaspagar();
            contasPagar.setDataEnvio(new Date());
            return "cadConPagar";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }

    public String excluir() {
        try {
            if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getEcontaspargar()) {
                ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
                for (int i = 0; i < listaContaPagar.size(); i++) {
                    if (listaContaPagar.get(i).isSelecionado()) {
                        if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("N")) {

                            contasPagarFacade.excluir(listaContaPagar.get(i).getIdcontasPagar());

                        }
                    }
                }
                gerarListaContasPagar();
                return "consConPagar";
            } else {
                FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
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
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        try {
            if (listaContaPagar == null) {
                listaContaPagar = new ArrayList<Contaspagar>();
            }
            for (int i = 0; i < listaContaPagar.size(); i++) {
                listaContaPagar.get(i).setStatus(verStatus(listaContaPagar.get(i)));
                listaContaPagar = contasPagarFacade.listar(sql);
            }
            calcularTotal();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage mensagem = new FacesMessage("Erro! " + ex);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
           
        }
       return;
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
        //refazer

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
            BancoFacade bancoFacade = new BancoFacade();
            try {
                if ((clienteMB.getCliente() != null) && (clienteMB.getCliente().getIdcliente() != null)) {
                listaBanco = bancoFacade.listar(clienteMB.getCliente().getIdcliente());
                }
                if (listaBanco == null) {
                listaBanco = new ArrayList<Banco>();
                } 
            }catch (SQLException ex) {
                Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
                 FacesMessage mensagem = new FacesMessage("Erro! " + ex);
                 FacesContext.getCurrentInstance().addMessage(null, mensagem);
                
            }
           
        }

    public void carregarListaPlanoContas() {

        try {
            PlanoContasFacade planoContasFacade = new PlanoContasFacade();
            listaPlanoContas = planoContasFacade.listar(clienteMB.getCliente().getTipoplanocontas().getIdtipoplanocontas());

            if (listaPlanoContas == null) {
                listaPlanoContas = new ArrayList<Planocontas>();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage mensagem = new FacesMessage("Erro! " + ex);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }

    }

    public String salvar() {

        try {
            if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIcontaspagar()) {
                if (contasPagar.getIdcontasPagar() == null) {
                    contasPagar.setAutorizarPagamento("N");
                }
                String msg = validarSalvar();
                if (msg.length() <= 5) {
                    if (!idPlanoConta.equalsIgnoreCase("0")) {
                        PlanoContasFacade planoContasFacade = new PlanoContasFacade();
                        Planocontas plano = planoContasFacade.consultar(Integer.parseInt(idPlanoConta));
                        contasPagar.setPlanocontas(plano);
                    }
                    if (!idBanco.equalsIgnoreCase("0")) {
                        BancoFacade bancoFacade = new BancoFacade();
                        Banco banco = bancoFacade.consultar(Integer.parseInt(idBanco));
                        contasPagar.setBanco(banco);
                    }
                    contasPagar.setCliente(clienteMB.getCliente());
                    contasPagar.setContaPaga("N");
                    contasPagar.setMovimentoBanco(0);
                    if (valorConta.length() > 0) {
                        contasPagar.setValor(Formatacao.ConvercaoMonetariaFloat(valorConta));
                    } else {
                        contasPagar.setValor(0.0f);
                    }
                    contasPagar.setVendaComissao(0);

                    ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
                    contasPagar = contasPagarFacade.salvar(contasPagar);

                    salvarNomeArquivo();
                    contasPagar = new Contaspagar();

                    clienteMB.setCliente(new Cliente());
                    gerarListaContasPagar();

                    return "consConPagar";
                } else {
                    FacesMessage mensagem = new FacesMessage("Erro! ", msg);
                    FacesContext.getCurrentInstance().addMessage(null, mensagem);
                    return "";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        
    }
        return  null;
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
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getContaspagar()){
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
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }

    public String salvarContasLiberadas() {
        if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getIcontaspagar()){
            ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
            for (int i = 0; i < listaLiberadas.size(); i++) {
                salvarContaLiberadasMovimentoBanco(listaLiberadas.get(i));
            }
            gerarListaContasPagar();
            listaLiberadas = null;
            dataLiberacao = null;
            return "consConPagar";
        }else {
            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
        
    }

    public void salvarContaLiberadasMovimentoBanco(Contaspagar conta) {
        try {
            conta.setDataLiberacao(dataLiberacao);
        conta.setContaPaga("S");
        String data = Formatacao.ConvercaoDataPadrao(new Date()) + "_" + Formatacao.foramtarHoraString();
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
        MovimentoBancoFacade movimentoBancoFacade = new MovimentoBancoFacade();
        conta.setMovimentoBanco(movimentoBanco.getIdmovimentoBanco());
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        contasPagarFacade.salvar(conta);
            movimentoBanco = movimentoBancoFacade.salvar(movimentoBanco);
        } catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage mensagem = new FacesMessage("Erro! " + ex);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            
        }
        
    }

    public String autorizarPagamentoContasPagar() {
        try {
            if (usuarioLogadoBean.getUsuario().getTipoacesso().getAcesso().getContaspagar()) {
                ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
                for (int i = 0; i < listaContaPagar.size(); i++) {
                    if (listaContaPagar.get(i).isSelecionado()) {
                        if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("N")) {
                            String data = Formatacao.ConvercaoDataPadrao(new Date()) + "_" + Formatacao.foramtarHoraString();
                            if (listaContaPagar.get(i).getAutorizarPagamento().equalsIgnoreCase("S")) {
                                listaContaPagar.get(i).setAutorizarPagamento("N");

                            } else {
                                listaContaPagar.get(i).setAutorizarPagamento("S");
                                contasPagarFacade.salvar(listaContaPagar.get(i));

                            }
                            gerarListaContasPagar();
                            return "consConPagar";
                        } else {
                            FacesMessage mensagem = new FacesMessage("Erro! ", "Acesso Negado");
                            FacesContext.getCurrentInstance().addMessage(null, mensagem);
                            return "";
                        }
                        
                    }
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(ContasPagarMB.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage mensagem = new FacesMessage("Erro! " + ex);
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        }
               
        return null;
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