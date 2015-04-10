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
@Table(name = "viewrelatoriopagamento")
@NamedQueries({
    @NamedQuery(name = "Viewrelatoriopagamento.findAll", query = "SELECT v FROM Viewrelatoriopagamento v")})
public class Viewrelatoriopagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NumeroConta")
    private int numeroConta;
    @Size(max = 100)
    @Column(name = "planoContas")
    private String planoContas;
    @Column(name = "dataCompensacao")
    @Temporal(TemporalType.DATE)
    private Date dataCompensacao;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorEntrada")
    private Float valorEntrada;
    @Column(name = "valorSaida")
    private Float valorSaida;
    @Size(max = 10)
    @Column(name = "compentencia")
    private String compentencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmovimentobanco")
    private int idmovimentobanco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente")
    private int idcliente;
    @Id
    private Long ID;

    public Viewrelatoriopagamento() {
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(String planoContas) {
        this.planoContas = planoContas;
    }

    public Date getDataCompensacao() {
        return dataCompensacao;
    }

    public void setDataCompensacao(Date dataCompensacao) {
        this.dataCompensacao = dataCompensacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(Float valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public Float getValorSaida() {
        return valorSaida;
    }

    public void setValorSaida(Float valorSaida) {
        this.valorSaida = valorSaida;
    }

    public String getCompentencia() {
        return compentencia;
    }

    public void setCompentencia(String compentencia) {
        this.compentencia = compentencia;
    }

    public int getIdmovimentobanco() {
        return idmovimentobanco;
    }

    public void setIdmovimentobanco(int idmovimentobanco) {
        this.idmovimentobanco = idmovimentobanco;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    
}
