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
@Table(name = "orcamentoprodutosorcamento")
public class Orcamentoprodutosorcamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorcamentoProdutosOrcamento")
    private Integer idorcamentoProdutosOrcamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorMoedaEstrangeira")
    private Float valorMoedaEstrangeira;
    @Column(name = "valorMoedaNacional")
    private Float valorMoedaNacional;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "orcamento_idorcamento")
    private int orcamento;
    @Column(name = "produtosOrcamento_idprodutosOrcamento")
    private int produtosOrcamento;
    

    public Orcamentoprodutosorcamento() {
    }

    
    public Float getValorMoedaEstrangeira() {
        return valorMoedaEstrangeira;
    }

    public Integer getIdorcamentoProdutosOrcamento() {
        return idorcamentoProdutosOrcamento;
    }

    public void setIdorcamentoProdutosOrcamento(Integer idorcamentoProdutosOrcamento) {
        this.idorcamentoProdutosOrcamento = idorcamentoProdutosOrcamento;
    }

    public int getProdutosOrcamento() {
        return produtosOrcamento;
    }

    public void setProdutosOrcamento(int produtosOrcamento) {
        this.produtosOrcamento = produtosOrcamento;
    }

   
    public void setValorMoedaEstrangeira(Float valorMoedaEstrangeira) {
        this.valorMoedaEstrangeira = valorMoedaEstrangeira;
    }

    public Float getValorMoedaNacional() {
        return valorMoedaNacional;
    }

    public void setValorMoedaNacional(Float valorMoedaNacional) {
        this.valorMoedaNacional = valorMoedaNacional;
    }

    public int getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(int orcamento) {
        this.orcamento = orcamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorcamentoProdutosOrcamento != null ? idorcamentoProdutosOrcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orcamentoprodutosorcamento)) {
            return false;
        }
        Orcamentoprodutosorcamento other = (Orcamentoprodutosorcamento) object;
        if ((this.idorcamentoProdutosOrcamento == null && other.idorcamentoProdutosOrcamento != null) || (this.idorcamentoProdutosOrcamento != null && !this.idorcamentoProdutosOrcamento.equals(other.idorcamentoProdutosOrcamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orcamentoprodutosorcamento[ idorcamento=" + idorcamentoProdutosOrcamento + " ]";
    }

    
}
