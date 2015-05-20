/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Integracao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "parcelamentopagamento")
public class SParcelamentopagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparcemlamentoPagamento")
    private Integer idparcemlamentoPagamento;
    @Column(name = "formaPagamento")
    private String formaPagamento;
    @Column(name = "tipoParcelmaneto")
    private String tipoParcelmaneto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorParcelamento")
    private Float valorParcelamento;
    @Column(name = "diaVencimento")
    private Integer diaVencimento;
    @Column(name = "numeroParcelas")
    private Integer numeroParcelas;
    @Column(name = "valorParcela")
    private Float valorParcela;
    @Column(name = "formapagamento_idformapagamento")
    private int idformapagamento;

    public SParcelamentopagamento() {
    }

    public SParcelamentopagamento(Integer idparcemlamentoPagamento) {
        this.idparcemlamentoPagamento = idparcemlamentoPagamento;
    }

    public Integer getIdparcemlamentoPagamento() {
        return idparcemlamentoPagamento;
    }

    public void setIdparcemlamentoPagamento(Integer idparcemlamentoPagamento) {
        this.idparcemlamentoPagamento = idparcemlamentoPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getTipoParcelmaneto() {
        return tipoParcelmaneto;
    }

    public void setTipoParcelmaneto(String tipoParcelmaneto) {
        this.tipoParcelmaneto = tipoParcelmaneto;
    }

    public Float getValorParcelamento() {
        return valorParcelamento;
    }

    public void setValorParcelamento(Float valorParcelamento) {
        this.valorParcelamento = valorParcelamento;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Float getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Float valorParcela) {
        this.valorParcela = valorParcela;
    }

    public int getIdformapagamento() {
        return idformapagamento;
    }

    public void setIdformapagamento(int idformapagamento) {
        this.idformapagamento = idformapagamento;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparcemlamentoPagamento != null ? idparcemlamentoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SParcelamentopagamento)) {
            return false;
        }
        SParcelamentopagamento other = (SParcelamentopagamento) object;
        if ((this.idparcemlamentoPagamento == null && other.idparcemlamentoPagamento != null) || (this.idparcemlamentoPagamento != null && !this.idparcemlamentoPagamento.equals(other.idparcemlamentoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SParcemlamentopagamento[ idparcemlamentoPagamento=" + idparcemlamentoPagamento + " ]";
    }
    
}
