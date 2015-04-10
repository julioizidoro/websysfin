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
@Table(name = "viewrelatoriovendas")
@NamedQueries({
    @NamedQuery(name = "Viewrelatoriovendas.findAll", query = "SELECT v FROM Viewrelatoriovendas v")})
public class Viewrelatoriovendas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Size(max = 100)
    @Column(name = "nomeCliente")
    private String nomeCliente;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorBruto")
    private Float valorBruto;
    @Column(name = "valordesconto")
    private Float valordesconto;
    @Column(name = "comissaoLiquidaTotal")
    private Float comissaoLiquidaTotal;
    @Column(name = "despesasFinanceiras")
    private Float despesasFinanceiras;
    @Column(name = "comissaoFuncionarios")
    private Float comissaoFuncionarios;
    @Column(name = "comissaoTerceiros")
    private Float comissaoTerceiros;
    @Column(name = "liquidoVendas")
    private Float liquidoVendas;
    @Size(max = 50)
    @Column(name = "consultor")
    private String consultor;
    @Size(max = 100)
    @Column(name = "nomeFornecedor")
    private String nomeFornecedor;
    @Size(max = 200)
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idvendas")
    @Id
    private int idvendas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCliente")
    private int idCliente;

    public Viewrelatoriovendas() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Float valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Float getValordesconto() {
        return valordesconto;
    }

    public void setValordesconto(Float valordesconto) {
        this.valordesconto = valordesconto;
    }

    public Float getComissaoLiquidaTotal() {
        return comissaoLiquidaTotal;
    }

    public void setComissaoLiquidaTotal(Float comissaoLiquidaTotal) {
        this.comissaoLiquidaTotal = comissaoLiquidaTotal;
    }

    public Float getDespesasFinanceiras() {
        return despesasFinanceiras;
    }

    public void setDespesasFinanceiras(Float despesasFinanceiras) {
        this.despesasFinanceiras = despesasFinanceiras;
    }

    public Float getComissaoFuncionarios() {
        return comissaoFuncionarios;
    }

    public void setComissaoFuncionarios(Float comissaoFuncionarios) {
        this.comissaoFuncionarios = comissaoFuncionarios;
    }

    public Float getComissaoTerceiros() {
        return comissaoTerceiros;
    }

    public void setComissaoTerceiros(Float comissaoTerceiros) {
        this.comissaoTerceiros = comissaoTerceiros;
    }

    public Float getLiquidoVendas() {
        return liquidoVendas;
    }

    public void setLiquidoVendas(Float liquidoVendas) {
        this.liquidoVendas = liquidoVendas;
    }

    public String getConsultor() {
        return consultor;
    }

    public void setConsultor(String consultor) {
        this.consultor = consultor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(int idvendas) {
        this.idvendas = idvendas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
