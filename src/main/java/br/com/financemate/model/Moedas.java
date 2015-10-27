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
@Table(name = "moedas")
@NamedQueries({
    @NamedQuery(name = "Moedas.findAll", query = "SELECT m FROM Moedas m")})
public class Moedas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmoedas")
    private Integer idmoedas;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 10)
    @Column(name = "sigla")
    private String sigla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moedas")
    private List<Pais> paisList;

    public Moedas() {
    }

    public Moedas(Integer idmoedas) {
        this.idmoedas = idmoedas;
    }

    public Integer getIdmoedas() {
        return idmoedas;
    }

    public void setIdmoedas(Integer idmoedas) {
        this.idmoedas = idmoedas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmoedas != null ? idmoedas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moedas)) {
            return false;
        }
        Moedas other = (Moedas) object;
        if ((this.idmoedas == null && other.idmoedas != null) || (this.idmoedas != null && !this.idmoedas.equals(other.idmoedas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Moedas[ idmoedas=" + idmoedas + " ]";
    }
    
}
