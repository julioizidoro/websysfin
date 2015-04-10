/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.modelView;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "viewrelatoriocontasreceber")
@NamedQueries({
    @NamedQuery(name = "Viewrelatoriocontasreceber.findAll", query = "SELECT v FROM Viewrelatoriocontasreceber v")})
public class Viewrelatoriocontasreceber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "numeroDocumento")
    private String numeroDocumento;
    @Size(max = 100)
    @Column(name = "nomeCliente")
    private String nomeCliente;
    @Column(name = "numeroParcela")
    private Integer numeroParcela;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorParcela")
    private Float valorParcela;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(name = "valorJuros")
    private Float valorJuros;
    @Column(name = "valorDesagio")
    private Float valorDesagio;
    @Column(name = "valorRecebido")
    private Float valorRecebido;
    @Size(max = 50)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Size(max = 100)
    @Column(name = "Unidade")
    private String unidade;
    @Size(max = 59)
    @Column(name = "nomeBanco")
    private String nomeBanco;
    @Column(name = "numeroVenda")
    private Integer numeroVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoCliente")
    private int codigoCliente;
    @Id
    private Long ID;

    public Viewrelatoriocontasreceber() {
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public Float getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Float valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Float getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(Float valorJuros) {
        this.valorJuros = valorJuros;
    }

    public Float getValorDesagio() {
        return valorDesagio;
    }

    public void setValorDesagio(Float valorDesagio) {
        this.valorDesagio = valorDesagio;
    }

    public Float getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Float valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Integer getNumeroVenda() {
        return numeroVenda;
    }

    public void setNumeroVenda(Integer numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    
}
