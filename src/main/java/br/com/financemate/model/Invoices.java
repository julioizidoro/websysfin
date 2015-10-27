/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "invoices")
public class Invoices implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinvoices")
    private Integer idinvoices;
    @Column(name = "datarecebimentocomprovante")
    @Temporal(TemporalType.DATE)
    private Date datarecebimentocomprovante;
    @Column(name = "dataPrevistaPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPrevistaPagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorPrevistoInvoice")
    private Float valorPrevistoInvoice;
    @Column(name = "dataPagamentoInvoice")
    @Temporal(TemporalType.DATE)
    private Date dataPagamentoInvoice;
    @Column(name = "valorPagamentoInvoice")
    private Float valorPagamentoInvoice;
    @Column(name = "valorPago")
    private Float valorPago;
    @Column(name = "cambioPagamento")
    private Float cambioPagamento;
    @Column(name = "ganhoperda")
    private Float ganhoperda;
    @Column(name = "ganhoCambio")
    private Float ganhoCambio;
    @Column(name = "valorcredito")
    private Float valorcredito;
    @Column(name = "taxa")
    private Float taxa;
    @Size(max = 100)
    @Column(name = "obscredito")
    private String obscredito;
    @Size(max = 6)
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "controle")
    private Integer controle;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "vendas_idvendas", referencedColumnName = "idvendas")
    @ManyToOne(optional = false)
    private Vendas vendas;

    public Invoices() {
    }

    public Invoices(Integer idinvoices) {
        this.idinvoices = idinvoices;
    }

    public Integer getIdinvoices() {
        return idinvoices;
    }

    public void setIdinvoices(Integer idinvoices) {
        this.idinvoices = idinvoices;
    }

    public Date getDatarecebimentocomprovante() {
        return datarecebimentocomprovante;
    }

    public void setDatarecebimentocomprovante(Date datarecebimentocomprovante) {
        this.datarecebimentocomprovante = datarecebimentocomprovante;
    }

    public Date getDataPrevistaPagamento() {
        return dataPrevistaPagamento;
    }

    public void setDataPrevistaPagamento(Date dataPrevistaPagamento) {
        this.dataPrevistaPagamento = dataPrevistaPagamento;
    }

    public Float getValorPrevistoInvoice() {
        return valorPrevistoInvoice;
    }

    public void setValorPrevistoInvoice(Float valorPrevistoInvoice) {
        this.valorPrevistoInvoice = valorPrevistoInvoice;
    }

    public Date getDataPagamentoInvoice() {
        return dataPagamentoInvoice;
    }

    public void setDataPagamentoInvoice(Date dataPagamentoInvoice) {
        this.dataPagamentoInvoice = dataPagamentoInvoice;
    }

    public Float getValorPagamentoInvoice() {
        return valorPagamentoInvoice;
    }

    public void setValorPagamentoInvoice(Float valorPagamentoInvoice) {
        this.valorPagamentoInvoice = valorPagamentoInvoice;
    }

    public Float getValorPago() {
        return valorPago;
    }

    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }

    public Float getCambioPagamento() {
        return cambioPagamento;
    }

    public void setCambioPagamento(Float cambioPagamento) {
        this.cambioPagamento = cambioPagamento;
    }

    public Float getGanhoperda() {
        return ganhoperda;
    }

    public void setGanhoperda(Float ganhoperda) {
        this.ganhoperda = ganhoperda;
    }

    public Float getGanhoCambio() {
        return ganhoCambio;
    }

    public void setGanhoCambio(Float ganhoCambio) {
        this.ganhoCambio = ganhoCambio;
    }

    public Float getValorcredito() {
        return valorcredito;
    }

    public void setValorcredito(Float valorcredito) {
        this.valorcredito = valorcredito;
    }

    public Float getTaxa() {
        return taxa;
    }

    public void setTaxa(Float taxa) {
        this.taxa = taxa;
    }

    public String getObscredito() {
        return obscredito;
    }

    public void setObscredito(String obscredito) {
        this.obscredito = obscredito;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getControle() {
        return controle;
    }

    public void setControle(Integer controle) {
        this.controle = controle;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinvoices != null ? idinvoices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoices)) {
            return false;
        }
        Invoices other = (Invoices) object;
        if ((this.idinvoices == null && other.idinvoices != null) || (this.idinvoices != null && !this.idinvoices.equals(other.idinvoices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Invoices[ idinvoices=" + idinvoices + " ]";
    }
    
}
