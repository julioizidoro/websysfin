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
import javax.persistence.Lob;
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
@Table(name = "vendas")
public class Vendas implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Obstm")
    private String obstm;
    @Size(max = 1)
    @Column(name = "vendasMatriz")
    private String vendasMatriz;
    @Column(name = "vendaimportada")
    private Integer vendaimportada;
    @Size(max = 200)
    @Column(name = "obsCancelar")
    private String obsCancelar;
    @Column(name = "datacancelamento")
    @Temporal(TemporalType.DATE)
    private Date datacancelamento;
    @Column(name = "usuariocancelamento")
    private Integer usuariocancelamento;
    @JoinColumn(name = "fornecedor_idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @JoinColumn(name = "fornecedorcidade_idfornecedorcidade", referencedColumnName = "idfornecedorcidade")
    @ManyToOne(optional = false)
    private Fornecedorcidade fornecedorcidade;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos")
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Invoices> invoicesList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvendas")
    private Integer idvendas;
    @Size(max = 100)
    @Column(name = "nomeCliente")
    private String nomeCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorBruto")
    private Float valorBruto;
    @Column(name = "valorDesconto")
    private Float valorDesconto;
    @Column(name = "valorJuros")
    private Float valorJuros;
    @Column(name = "valorLiquido")
    private Float valorLiquido;
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    @Column(name = "comissaoLiquidaTotal")
    private Float comissaoLiquidaTotal;
    @Column(name = "despesasFinanceiras")
    private Float despesasFinanceiras;
    @Column(name = "comissaoFuncionarios")
    private Float comissaoFuncionarios;
    @Column(name = "comissaoTerceiros")
    private Float comissaoTerceiros;
    @Column(name = "dataPagamentoFornecedor")
    @Temporal(TemporalType.DATE)
    private Date dataPagamentoFornecedor;
    @Column(name = "valorPagoFornecedor")
    private Float valorPagoFornecedor;
    @Column(name = "valorRecebido")
    private Float valorRecebido;
    @Column(name = "liquidoVendas")
    private Float liquidoVendas;
    @Size(max = 100)
    @Column(name = "nomeFornecedor")
    private String nomeFornecedor;
    @Column(name = "liquidoReceber")
    private Float liquidoReceber;
    @Size(max = 15)
    @Column(name = "situacao")
    private String situacao;
    @Size(max = 50)
    @Column(name = "consultor")
    private String consultor;
    @Size(max = 200)
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Emissaonota> emissaonotaList;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "planoContas_idplanoContas", referencedColumnName = "idplanoContas")
    @ManyToOne(optional = false)
    private Planocontas planocontas;
    @JoinColumn(name = "produto_idproduto", referencedColumnName = "idproduto")
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendas")
    private List<Formapagamento> formapagamentoList;
    @Transient
    private Boolean selecionado;

    public Vendas() {
    }

    public Vendas(Integer idvendas) {
        this.idvendas = idvendas;
    }

    public Integer getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(Integer idvendas) {
        this.idvendas = idvendas;
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

    public Float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Float getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(Float valorJuros) {
        this.valorJuros = valorJuros;
    }

    public Float getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Float valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
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

    public Date getDataPagamentoFornecedor() {
        return dataPagamentoFornecedor;
    }

    public void setDataPagamentoFornecedor(Date dataPagamentoFornecedor) {
        this.dataPagamentoFornecedor = dataPagamentoFornecedor;
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

    public Float getLiquidoVendas() {
        return liquidoVendas;
    }

    public void setLiquidoVendas(Float liquidoVendas) {
        this.liquidoVendas = liquidoVendas;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Float getLiquidoReceber() {
        return liquidoReceber;
    }

    public void setLiquidoReceber(Float liquidoReceber) {
        this.liquidoReceber = liquidoReceber;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getConsultor() {
        return consultor;
    }

    public void setConsultor(String consultor) {
        this.consultor = consultor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Emissaonota> getEmissaonotaList() {
        return emissaonotaList;
    }

    public void setEmissaonotaList(List<Emissaonota> emissaonotaList) {
        this.emissaonotaList = emissaonotaList;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Formapagamento> getFormapagamentoList() {
        return formapagamentoList;
    }

    public void setFormapagamentoList(List<Formapagamento> formapagamentoList) {
        this.formapagamentoList = formapagamentoList;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvendas != null ? idvendas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendas)) {
            return false;
        }
        Vendas other = (Vendas) object;
        if ((this.idvendas == null && other.idvendas != null) || (this.idvendas != null && !this.idvendas.equals(other.idvendas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Vendas[ idvendas=" + idvendas + " ]";
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getObstm() {
        return obstm;
    }

    public void setObstm(String obstm) {
        this.obstm = obstm;
    }

    public String getVendasMatriz() {
        return vendasMatriz;
    }

    public void setVendasMatriz(String vendasMatriz) {
        this.vendasMatriz = vendasMatriz;
    }

    public Integer getVendaimportada() {
        return vendaimportada;
    }

    public void setVendaimportada(Integer vendaimportada) {
        this.vendaimportada = vendaimportada;
    }

    public String getObsCancelar() {
        return obsCancelar;
    }

    public void setObsCancelar(String obsCancelar) {
        this.obsCancelar = obsCancelar;
    }

    public Date getDatacancelamento() {
        return datacancelamento;
    }

    public void setDatacancelamento(Date datacancelamento) {
        this.datacancelamento = datacancelamento;
    }

    public Integer getUsuariocancelamento() {
        return usuariocancelamento;
    }

    public void setUsuariocancelamento(Integer usuariocancelamento) {
        this.usuariocancelamento = usuariocancelamento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedorcidade getFornecedorcidade() {
        return fornecedorcidade;
    }

    public void setFornecedorcidade(Fornecedorcidade fornecedorcidade) {
        this.fornecedorcidade = fornecedorcidade;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public List<Invoices> getInvoicesList() {
        return invoicesList;
    }

    public void setInvoicesList(List<Invoices> invoicesList) {
        this.invoicesList = invoicesList;
    }
    
}
