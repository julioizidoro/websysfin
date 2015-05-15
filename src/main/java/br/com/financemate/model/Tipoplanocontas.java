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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "tipoplanocontas")
public class Tipoplanocontas implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoplanocontas")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoplanocontas")
    private List<Planocontas> planocontasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoplanocontas")
    private Integer idtipoplanocontas;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;

    public Tipoplanocontas() {
    }

    public Tipoplanocontas(Integer idtipoplanocontas) {
        this.idtipoplanocontas = idtipoplanocontas;
    }

    public Integer getIdtipoplanocontas() {
        return idtipoplanocontas;
    }

    public void setIdtipoplanocontas(Integer idtipoplanocontas) {
        this.idtipoplanocontas = idtipoplanocontas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoplanocontas != null ? idtipoplanocontas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoplanocontas)) {
            return false;
        }
        Tipoplanocontas other = (Tipoplanocontas) object;
        if ((this.idtipoplanocontas == null && other.idtipoplanocontas != null) || (this.idtipoplanocontas != null && !this.idtipoplanocontas.equals(other.idtipoplanocontas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public List<Planocontas> getPlanocontasList() {
        return planocontasList;
    }

    public void setPlanocontasList(List<Planocontas> planocontasList) {
        this.planocontasList = planocontasList;
    }
    
}
