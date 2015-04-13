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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "banco")
public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanco")
    private Integer idbanco;
    @Size(max = 5)
    @Column(name = "numeroBanco")
    private String numeroBanco;
    @Size(max = 59)
    @Column(name = "nome")
    private String nome;
    @Size(max = 20)
    @Column(name = "agencia")
    private String agencia;
    @Size(max = 50)
    @Column(name = "conta")
    private String conta;
    @Size(max = 50)
    @Column(name = "cahve")
    private String cahve;
    @Size(max = 45)
    @Column(name = "senha")
    private String senha;
    @Size(max = 100)
    @Column(name = "gerente")
    private String gerente;
    @Size(max = 100)
    @Column(name = "emailGerente")
    private String emailGerente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<Contasreceber> contasreceberList;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<Contaspagar> contaspagarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<Movimentobanco> movimentobancoList;

    public Banco() {
    }

    public Banco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(String numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCahve() {
        return cahve;
    }

    public void setCahve(String cahve) {
        this.cahve = cahve;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getEmailGerente() {
        return emailGerente;
    }

    public void setEmailGerente(String emailGerente) {
        this.emailGerente = emailGerente;
    }

    public List<Contasreceber> getContasreceberList() {
        return contasreceberList;
    }

    public void setContasreceberList(List<Contasreceber> contasreceberList) {
        this.contasreceberList = contasreceberList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Contaspagar> getContaspagarList() {
        return contaspagarList;
    }

    public void setContaspagarList(List<Contaspagar> contaspagarList) {
        this.contaspagarList = contaspagarList;
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
        hash += (idbanco != null ? idbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idbanco == null && other.idbanco != null) || (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Banco[ idbanco=" + idbanco + " ]";
    }
    
}
