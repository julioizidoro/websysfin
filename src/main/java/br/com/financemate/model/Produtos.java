/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "produtos")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p")})
public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprodutos")
    private Integer idprodutos;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "comissaogerente")
    private Double comissaogerente;
    @Column(name = "idgerente")
    private Integer idgerente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtos")
    private List<Vendas> vendasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtos")
    private List<Fornecedorcidade> fornecedorcidadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtos")
    private List<Invoices> invoicesList;

    public Produtos() {
    }

    public Produtos(Integer idprodutos) {
        this.idprodutos = idprodutos;
    }

    public Integer getIdprodutos() {
        return idprodutos;
    }

    public void setIdprodutos(Integer idprodutos) {
        this.idprodutos = idprodutos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getComissaogerente() {
        return comissaogerente;
    }

    public void setComissaogerente(Double comissaogerente) {
        this.comissaogerente = comissaogerente;
    }

    public Integer getIdgerente() {
        return idgerente;
    }

    public void setIdgerente(Integer idgerente) {
        this.idgerente = idgerente;
    }

    public List<Vendas> getVendasList() {
        return vendasList;
    }

    public void setVendasList(List<Vendas> vendasList) {
        this.vendasList = vendasList;
    }

    public List<Fornecedorcidade> getFornecedorcidadeList() {
        return fornecedorcidadeList;
    }

    public void setFornecedorcidadeList(List<Fornecedorcidade> fornecedorcidadeList) {
        this.fornecedorcidadeList = fornecedorcidadeList;
    }

    public List<Invoices> getInvoicesList() {
        return invoicesList;
    }

    public void setInvoicesList(List<Invoices> invoicesList) {
        this.invoicesList = invoicesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodutos != null ? idprodutos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.idprodutos == null && other.idprodutos != null) || (this.idprodutos != null && !this.idprodutos.equals(other.idprodutos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Produtos[ idprodutos=" + idprodutos + " ]";
    }
    
}
