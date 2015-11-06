/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Greici
 */
@Embeddable
public class OperacaousuairoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idoperacaousuairo")
    private int idoperacaousuairo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contasPagar_idcontasPagar")
    private int contasPagaridcontasPagar;

    public OperacaousuairoPK() {
    }

    public OperacaousuairoPK(int idoperacaousuairo, int contasPagaridcontasPagar) {
        this.idoperacaousuairo = idoperacaousuairo;
        this.contasPagaridcontasPagar = contasPagaridcontasPagar;
    }

    public int getIdoperacaousuairo() {
        return idoperacaousuairo;
    }

    public void setIdoperacaousuairo(int idoperacaousuairo) {
        this.idoperacaousuairo = idoperacaousuairo;
    }

    public int getContasPagaridcontasPagar() {
        return contasPagaridcontasPagar;
    }

    public void setContasPagaridcontasPagar(int contasPagaridcontasPagar) {
        this.contasPagaridcontasPagar = contasPagaridcontasPagar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idoperacaousuairo;
        hash += (int) contasPagaridcontasPagar;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperacaousuairoPK)) {
            return false;
        }
        OperacaousuairoPK other = (OperacaousuairoPK) object;
        if (this.idoperacaousuairo != other.idoperacaousuairo) {
            return false;
        }
        if (this.contasPagaridcontasPagar != other.contasPagaridcontasPagar) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.OperacaousuairoPK[ idoperacaousuairo=" + idoperacaousuairo + ", contasPagaridcontasPagar=" + contasPagaridcontasPagar + " ]";
    }
    
}
