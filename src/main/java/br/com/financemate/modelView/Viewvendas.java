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
@Table(name = "viewvendas")
@NamedQueries({
    @NamedQuery(name = "Viewvendas.findAll", query = "SELECT v FROM Viewvendas v")})
public class Viewvendas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idvendas")
    @Id
    private int idvendas;
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Size(max = 100)
    @Column(name = "nomeCliente")
    private String nomeCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorBruto")
    private Float valorBruto;
    @Size(max = 100)
    @Column(name = "razaoSocial")
    private String razaoSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente_idcliente")
    private int clienteIdcliente;
    @Column(name = "valorLiquido")
    private Float valorLiquido;
    @Column(name = "valorDesconto")
    private Float valorDesconto;
    @Column(name = "valorPagoFornecedor")
    private Float valorPagoFornecedor;
    @Column(name = "valorRecebido")
    private Float valorRecebido;
    @Size(max = 15)
    @Column(name = "situacao")
    private String situacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "planoContas")
    private int planoContas;
    @Size(max = 100)
    @Column(name = "nomeFantasia")
    private String nomeFantasia;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 15)
    @Column(name = "visualizacao")
    private String visualizacao;

    public Viewvendas() {
    }

    public int getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(int idvendas) {
        this.idvendas = idvendas;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Float getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Float valorBruto) {
        this.valorBruto = valorBruto;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(int clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    public Float getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Float valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public Float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Float getValorPagoFornecedor() {
        return valorPagoFornecedor;
    }

    public void setValorPagoFornecedor(Float valorPagoFornecedor) {
        this.valorPagoFornecedor = valorPagoFornecedor;
    }

    public Float getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Float valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(int planoContas) {
        this.planoContas = planoContas;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVisualizacao() {
        return visualizacao;
    }

    public void setVisualizacao(String visualizacao) {
        this.visualizacao = visualizacao;
    }
    
}
