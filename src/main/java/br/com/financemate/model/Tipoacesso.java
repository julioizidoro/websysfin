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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "tipoacesso")
public class Tipoacesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoacesso")
    private Integer idtipoacesso;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoacesso")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "acesso_idacesso", referencedColumnName = "idacesso")
    @ManyToOne(optional = false)
    private Acesso acesso;

    public Tipoacesso() {
    }

    public Tipoacesso(Integer idtipoacesso) {
        this.idtipoacesso = idtipoacesso;
    }

    public Integer getIdtipoacesso() {
        return idtipoacesso;
    }

    public void setIdtipoacesso(Integer idtipoacesso) {
        this.idtipoacesso = idtipoacesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoacesso != null ? idtipoacesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoacesso)) {
            return false;
        }
        Tipoacesso other = (Tipoacesso) object;
        if ((this.idtipoacesso == null && other.idtipoacesso != null) || (this.idtipoacesso != null && !this.idtipoacesso.equals(other.idtipoacesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Tipoacesso[ idtipoacesso=" + idtipoacesso + " ]";
    }
    
}
