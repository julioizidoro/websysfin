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

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "viewcontaspagarfluxo")
@NamedQueries({
    @NamedQuery(name = "Viewcontaspagarfluxo.findAll", query = "SELECT v FROM Viewcontaspagarfluxo v")})
public class Viewcontaspagarfluxo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcontaspagar")
    @Id
    private int idcontaspagar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente_idcliente")
    private int clienteIdcliente;
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorDia")
    private Double valorDia;

    public Viewcontaspagarfluxo() {
    }

    public int getIdcontaspagar() {
        return idcontaspagar;
    }

    public void setIdcontaspagar(int idcontaspagar) {
        this.idcontaspagar = idcontaspagar;
    }

    public int getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(int clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValorDia() {
        return valorDia;
    }

    public void setValorDia(Double valorDia) {
        this.valorDia = valorDia;
    }
    
}
