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
@Table(name = "planocontas")
@NamedQueries({
    @NamedQuery(name = "Planocontas.findAll", query = "SELECT p FROM Planocontas p")})
public class Planocontas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplanoContas")
    private Integer idplanoContas;
    @Size(max = 20)
    @Column(name = "numeroConta")
    private String numeroConta;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planocontas")
    private List<Contasreceber> contasreceberList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planocontas")
    private List<Contaspagar> contaspagarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planocontas")
    private List<Vendas> vendasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planocontas")
    private List<Movimentobanco> movimentobancoList;

    public Planocontas() {
    }

    public Planocontas(Integer idplanoContas) {
        this.idplanoContas = idplanoContas;
    }

    public Integer getIdplanoContas() {
        return idplanoContas;
    }

    public void setIdplanoContas(Integer idplanoContas) {
        this.idplanoContas = idplanoContas;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Contasreceber> getContasreceberList() {
        return contasreceberList;
    }

    public void setContasreceberList(List<Contasreceber> contasreceberList) {
        this.contasreceberList = contasreceberList;
    }

    public List<Contaspagar> getContaspagarList() {
        return contaspagarList;
    }

    public void setContaspagarList(List<Contaspagar> contaspagarList) {
        this.contaspagarList = contaspagarList;
    }

    public List<Vendas> getVendasList() {
        return vendasList;
    }

    public void setVendasList(List<Vendas> vendasList) {
        this.vendasList = vendasList;
    }

    public List<Movimentobanco> getMovimentobancoList() {
        return movimentobancoList;
    }

    public void setMovimentobancoList(List<Movimentobanco> movimentobancoList) {
        this.movimentobancoList = movimentobancoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanoContas != null ? idplanoContas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planocontas)) {
            return false;
        }
        Planocontas other = (Planocontas) object;
        if ((this.idplanoContas == null && other.idplanoContas != null) || (this.idplanoContas != null && !this.idplanoContas.equals(other.idplanoContas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Planocontas[ idplanoContas=" + idplanoContas + " ]";
    }
    
}
