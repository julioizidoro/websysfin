/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Greici
 */
@Entity
@Table(name = "operacaousuairo")
@NamedQueries({
    @NamedQuery(name = "Operacaousuairo.findAll", query = "SELECT o FROM Operacaousuairo o")})
public class Operacaousuairo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OperacaousuairoPK operacaousuairoPK;
    @Size(max = 50)
    @Column(name = "tipooperacao")
    private String tipooperacao;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Size(max = 10)
    @Column(name = "hora")
    private String hora;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "contasPagar_idcontasPagar", referencedColumnName = "idcontasPagar", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contaspagar contaspagar;

    public Operacaousuairo() {
    }

    public Operacaousuairo(OperacaousuairoPK operacaousuairoPK) {
        this.operacaousuairoPK = operacaousuairoPK;
    }

    public Operacaousuairo(int idoperacaousuairo, int contasPagaridcontasPagar) {
        this.operacaousuairoPK = new OperacaousuairoPK(idoperacaousuairo, contasPagaridcontasPagar);
    }

    public OperacaousuairoPK getOperacaousuairoPK() {
        return operacaousuairoPK;
    }

    public void setOperacaousuairoPK(OperacaousuairoPK operacaousuairoPK) {
        this.operacaousuairoPK = operacaousuairoPK;
    }

    public String getTipooperacao() {
        return tipooperacao;
    }

    public void setTipooperacao(String tipooperacao) {
        this.tipooperacao = tipooperacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Contaspagar getContaspagar() {
        return contaspagar;
    }

    public void setContaspagar(Contaspagar contaspagar) {
        this.contaspagar = contaspagar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operacaousuairoPK != null ? operacaousuairoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacaousuairo)) {
            return false;
        }
        Operacaousuairo other = (Operacaousuairo) object;
        if ((this.operacaousuairoPK == null && other.operacaousuairoPK != null) || (this.operacaousuairoPK != null && !this.operacaousuairoPK.equals(other.operacaousuairoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Operacaousuairo[ operacaousuairoPK=" + operacaousuairoPK + " ]";
    }
    
}
