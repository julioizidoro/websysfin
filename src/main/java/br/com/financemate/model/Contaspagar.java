/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "contaspagar")
@NamedQueries({
    @NamedQuery(name = "Contaspagar.findAll", query = "SELECT c FROM Contaspagar c")})
public class Contaspagar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontasPagar")
    private Integer idcontasPagar;
    @Column(name = "dataLiberacao")
    @Temporal(TemporalType.DATE)
    private Date dataLiberacao;
    @Column(name = "dataEnvio")
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "dataAgendamento")
    @Temporal(TemporalType.DATE)
    private Date dataAgendamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Size(max = 7)
    @Column(name = "competencia")
    private String competencia;
    @Column(name = "dataCompensacao")
    @Temporal(TemporalType.DATE)
    private Date dataCompensacao;
    @Column(name = "movimentoBanco")
    private Integer movimentoBanco;
    @Column(name = "usuarioCadastrou")
    private Integer usuarioCadastrou;
    @Column(name = "usuarioAgendou")
    private Integer usuarioAgendou;
    @Column(name = "usuarioBaixou")
    private Integer usuarioBaixou;
    @Column(name = "usuarioAutorizou")
    private Integer usuarioAutorizou;
    @Size(max = 1)
    @Column(name = "contaPaga")
    private String contaPaga;
    @Size(max = 50)
    @Column(name = "formaPagamento")
    private String formaPagamento;
    @Column(name = "vendaComissao")
    private Integer vendaComissao;
    @Size(max = 100)
    @Column(name = "fornecedor")
    private String fornecedor;
    @Size(max = 30)
    @Column(name = "numeroDocumento")
    private String numeroDocumento;
    @Size(max = 1)
    @Column(name = "marcar")
    private String marcar;
    @Size(max = 50)
    @Column(name = "tipoDocumento")
    private String tipoDocumento;
    @Size(max = 1)
    @Column(name = "autorizarPagamento")
    private String autorizarPagamento;
    @Size(max = 30)
    @Column(name = "dataHoraCadastrou")
    private String dataHoraCadastrou;
    @Size(max = 30)
    @Column(name = "dataHoraAgendou")
    private String dataHoraAgendou;
    @Size(max = 30)
    @Column(name = "dataHoraLiberou")
    private String dataHoraLiberou;
    @Size(max = 30)
    @Column(name = "dataHoraAutorizou")
    private String dataHoraAutorizou;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "planoContas_idplanoContas", referencedColumnName = "idplanoContas")
    @ManyToOne(optional = false)
    private Planocontas planocontas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaspagar")
    private List<Arquivocontaspagar> arquivocontaspagarList;
    @Transient
    private boolean selecionado=false;
   
    public Contaspagar() {
    }

    public Contaspagar(Integer idcontasPagar) {
        this.idcontasPagar = idcontasPagar;
    }

    public Integer getIdcontasPagar() {
        return idcontasPagar;
    }

    public void setIdcontasPagar(Integer idcontasPagar) {
        this.idcontasPagar = idcontasPagar;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public Date getDataCompensacao() {
        return dataCompensacao;
    }

    public void setDataCompensacao(Date dataCompensacao) {
        this.dataCompensacao = dataCompensacao;
    }

    public Integer getMovimentoBanco() {
        return movimentoBanco;
    }

    public void setMovimentoBanco(Integer movimentoBanco) {
        this.movimentoBanco = movimentoBanco;
    }

    public Integer getUsuarioCadastrou() {
        return usuarioCadastrou;
    }

    public void setUsuarioCadastrou(Integer usuarioCadastrou) {
        this.usuarioCadastrou = usuarioCadastrou;
    }

    public Integer getUsuarioAgendou() {
        return usuarioAgendou;
    }

    public void setUsuarioAgendou(Integer usuarioAgendou) {
        this.usuarioAgendou = usuarioAgendou;
    }

    public Integer getUsuarioBaixou() {
        return usuarioBaixou;
    }

    public void setUsuarioBaixou(Integer usuarioBaixou) {
        this.usuarioBaixou = usuarioBaixou;
    }

    public Integer getUsuarioAutorizou() {
        return usuarioAutorizou;
    }

    public void setUsuarioAutorizou(Integer usuarioAutorizou) {
        this.usuarioAutorizou = usuarioAutorizou;
    }

    public String getContaPaga() {
        return contaPaga;
    }

    public void setContaPaga(String contaPaga) {
        this.contaPaga = contaPaga;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getVendaComissao() {
        return vendaComissao;
    }

    public void setVendaComissao(Integer vendaComissao) {
        this.vendaComissao = vendaComissao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getMarcar() {
        return marcar;
    }

    public void setMarcar(String marcar) {
        this.marcar = marcar;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getAutorizarPagamento() {
        return autorizarPagamento;
    }

    public void setAutorizarPagamento(String autorizarPagamento) {
        this.autorizarPagamento = autorizarPagamento;
    }

    public String getDataHoraCadastrou() {
        return dataHoraCadastrou;
    }

    public void setDataHoraCadastrou(String dataHoraCadastrou) {
        this.dataHoraCadastrou = dataHoraCadastrou;
    }

    public String getDataHoraAgendou() {
        return dataHoraAgendou;
    }

    public void setDataHoraAgendou(String dataHoraAgendou) {
        this.dataHoraAgendou = dataHoraAgendou;
    }

    public String getDataHoraLiberou() {
        return dataHoraLiberou;
    }

    public void setDataHoraLiberou(String dataHoraLiberou) {
        this.dataHoraLiberou = dataHoraLiberou;
    }

    public String getDataHoraAutorizou() {
        return dataHoraAutorizou;
    }

    public void setDataHoraAutorizou(String dataHoraAutorizou) {
        this.dataHoraAutorizou = dataHoraAutorizou;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Planocontas getPlanocontas() {
        return planocontas;
    }

    public void setPlanocontas(Planocontas planocontas) {
        this.planocontas = planocontas;
    }

    public List<Arquivocontaspagar> getArquivocontaspagarList() {
        return arquivocontaspagarList;
    }

    public void setArquivocontaspagarList(List<Arquivocontaspagar> arquivocontaspagarList) {
        this.arquivocontaspagarList = arquivocontaspagarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontasPagar != null ? idcontasPagar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contaspagar)) {
            return false;
        }
        Contaspagar other = (Contaspagar) object;
        if ((this.idcontasPagar == null && other.idcontasPagar != null) || (this.idcontasPagar != null && !this.idcontasPagar.equals(other.idcontasPagar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Contaspagar[ idcontasPagar=" + idcontasPagar + " ]";
    }
    
}
