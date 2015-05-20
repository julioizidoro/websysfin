/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

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
@Table(name = "acesso")
public class Acesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idacesso")
    private Integer idacesso;
    @Column(name = "cadastro")
    private Integer cadastro;
    @Column(name = "cliente")
    private Integer cliente;
    @Column(name = "acliente")
    private Integer acliente;
    @Column(name = "ecliente")
    private Integer ecliente;
    @Column(name = "icliente")
    private Integer icliente;
    @Column(name = "banco")
    private Integer banco;
    @Column(name = "ibanco")
    private Integer ibanco;
    @Column(name = "abanco")
    private Integer abanco;
    @Column(name = "ebanco")
    private Integer ebanco;
    @Column(name = "planocontas")
    private Integer planocontas;
    @Column(name = "iplanocontas")
    private Integer iplanocontas;
    @Column(name = "aplanocontas")
    private Integer aplanocontas;
    @Column(name = "eplanocontas")
    private Integer eplanocontas;
    @Column(name = "usuario")
    private Integer usuario;
    @Column(name = "iusuario")
    private Integer iusuario;
    @Column(name = "ausuario")
    private Integer ausuario;
    @Column(name = "eusuario")
    private Integer eusuario;
    @Column(name = "produto")
    private Integer produto;
    @Column(name = "iproduto")
    private Integer iproduto;
    @Column(name = "aproduto")
    private Integer aproduto;
    @Column(name = "eproduto")
    private Integer eproduto;
    @Column(name = "contaspagar")
    private Integer contaspagar;
    @Column(name = "icontaspagar")
    private Integer icontaspagar;
    @Column(name = "acontaspargar")
    private Integer acontaspargar;
    @Column(name = "econtaspargar")
    private Integer econtaspargar;
    @Column(name = "lcontaspargar")
    private Integer lcontaspargar;
    @Column(name = "arqcontaspargar")
    private Integer arqcontaspargar;
    @Column(name = "ucontaspargar")
    private Integer ucontaspargar;
    @Column(name = "autcontaspargar")
    private Integer autcontaspargar;
     @Column(name = "contasreceber")
    private Integer contasreceber;
    @Column(name = "icontasreceber")
    private Integer icontasreceber;
    @Column(name = "acontasreceber")
    private Integer acontasreceber;
    @Column(name = "econtasreceber")
    private Integer econtasreceber;
    @Column(name = "rcontasreceber")
    private Integer rcontasreceber;
    @Column(name = "outroslancamentos")
    private Integer outroslancamentos;
    @Column(name = "ioutroslancamentos")
    private Integer ioutroslancamentos;
    @Column(name = "aoutroslancamentos")
    private Integer aoutroslancamentos;
    @Column(name = "eoutroslancamentos")
    private Integer eoutroslancamentos;
    @Column(name = "rvendas")
    private Integer rvendas;
    @Column(name = "rpagamentos")
    private Integer rpagamentos;
    @Column(name = "rconciliacao")
    private Integer rconciliacao;
    @Column(name = "rreceber")
    private Integer rreceber;
    @Column(name = "rpagar")
    private Integer rpagar;
    @Column(name = "rfluxo")
    private Integer rfluxo;
    @Column(name = "vendas")
    private Integer vendas;
    @Column(name = "ivendas")
    private Integer ivendas;
    @Column(name = "evendas")
    private Integer evendas;
    @Column(name = "avendas")
    private Integer avendas;
    @Column(name = "pvendas")
    private Integer pvendas;
    @Column(name = "cvendas")
    private Integer cvendas;
    @Column(name = "fvendas")
    private Integer fvendas;
    @Column(name = "tipoplanocontas")
    private Integer tipoplanocontas;
    @Column(name = "itipoplanocontas")
    private Integer itipoplanocontas;
    @Column(name = "atipoplanocontas")
    private Integer atipoplanocontas;
    @Column(name = "etipoplanocontas")
    private Integer etipoplanocontas;
    

    public Acesso() {
    }

    public Acesso(Integer idacesso) {
        this.idacesso = idacesso;
    }

    public Integer getIdacesso() {
        return idacesso;
    }

    public void setIdacesso(Integer idacesso) {
        this.idacesso = idacesso;
    }

    public Integer getCadastro() {
        return cadastro;
    }

    public void setCadastro(Integer cadastro) {
        this.cadastro = cadastro;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getAcliente() {
        return acliente;
    }

    public Integer getContasreceber() {
        return contasreceber;
    }

    public void setContasreceber(Integer contasreceber) {
        this.contasreceber = contasreceber;
    }

    public Integer getOutroslancamentos() {
        return outroslancamentos;
    }

    public void setOutroslancamentos(Integer outroslancamentos) {
        this.outroslancamentos = outroslancamentos;
    }

    public void setAcliente(Integer acliente) {
        this.acliente = acliente;
    }

    public Integer getEcliente() {
        return ecliente;
    }

    public void setEcliente(Integer ecliente) {
        this.ecliente = ecliente;
    }

    public Integer getIcliente() {
        return icliente;
    }

    public void setIcliente(Integer icliente) {
        this.icliente = icliente;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Integer getIbanco() {
        return ibanco;
    }

    public void setIbanco(Integer ibanco) {
        this.ibanco = ibanco;
    }

    public Integer getAbanco() {
        return abanco;
    }

    public void setAbanco(Integer abanco) {
        this.abanco = abanco;
    }

    public Integer getEbanco() {
        return ebanco;
    }

    public void setEbanco(Integer ebanco) {
        this.ebanco = ebanco;
    }

    public Integer getPlanocontas() {
        return planocontas;
    }

    public void setPlanocontas(Integer planocontas) {
        this.planocontas = planocontas;
    }

    public Integer getIplanocontas() {
        return iplanocontas;
    }

    public void setIplanocontas(Integer iplanocontas) {
        this.iplanocontas = iplanocontas;
    }

    public Integer getAplanocontas() {
        return aplanocontas;
    }

    public void setAplanocontas(Integer aplanocontas) {
        this.aplanocontas = aplanocontas;
    }

    public Integer getEplanocontas() {
        return eplanocontas;
    }

    public void setEplanocontas(Integer eplanocontas) {
        this.eplanocontas = eplanocontas;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getIusuario() {
        return iusuario;
    }

    public void setIusuario(Integer iusuario) {
        this.iusuario = iusuario;
    }

    public Integer getAusuario() {
        return ausuario;
    }

    public void setAusuario(Integer ausuario) {
        this.ausuario = ausuario;
    }

    public Integer getEusuario() {
        return eusuario;
    }

    public void setEusuario(Integer eusuario) {
        this.eusuario = eusuario;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getIproduto() {
        return iproduto;
    }

    public void setIproduto(Integer iproduto) {
        this.iproduto = iproduto;
    }

    public Integer getAproduto() {
        return aproduto;
    }

    public void setAproduto(Integer aproduto) {
        this.aproduto = aproduto;
    }

    public Integer getEproduto() {
        return eproduto;
    }

    public void setEproduto(Integer eproduto) {
        this.eproduto = eproduto;
    }

    public Integer getContaspagar() {
        return contaspagar;
    }

    public void setContaspagar(Integer contaspagar) {
        this.contaspagar = contaspagar;
    }

    public Integer getIcontaspagar() {
        return icontaspagar;
    }

    public void setIcontaspagar(Integer icontaspagar) {
        this.icontaspagar = icontaspagar;
    }

    public Integer getAcontaspargar() {
        return acontaspargar;
    }

    public void setAcontaspargar(Integer acontaspargar) {
        this.acontaspargar = acontaspargar;
    }

    public Integer getEcontaspargar() {
        return econtaspargar;
    }

    public void setEcontaspargar(Integer econtaspargar) {
        this.econtaspargar = econtaspargar;
    }

    public Integer getLcontaspargar() {
        return lcontaspargar;
    }

    public void setLcontaspargar(Integer lcontaspargar) {
        this.lcontaspargar = lcontaspargar;
    }

    public Integer getArqcontaspargar() {
        return arqcontaspargar;
    }

    public void setArqcontaspargar(Integer arqcontaspargar) {
        this.arqcontaspargar = arqcontaspargar;
    }

    public Integer getUcontaspargar() {
        return ucontaspargar;
    }

    public void setUcontaspargar(Integer ucontaspargar) {
        this.ucontaspargar = ucontaspargar;
    }

    public Integer getAutcontaspargar() {
        return autcontaspargar;
    }

    public void setAutcontaspargar(Integer autcontaspargar) {
        this.autcontaspargar = autcontaspargar;
    }

    public Integer getIcontasreceber() {
        return icontasreceber;
    }

    public void setIcontasreceber(Integer icontasreceber) {
        this.icontasreceber = icontasreceber;
    }

    public Integer getAcontasreceber() {
        return acontasreceber;
    }

    public void setAcontasreceber(Integer acontasreceber) {
        this.acontasreceber = acontasreceber;
    }

    public Integer getEcontasreceber() {
        return econtasreceber;
    }

    public void setEcontasreceber(Integer econtasreceber) {
        this.econtasreceber = econtasreceber;
    }

    public Integer getRcontasreceber() {
        return rcontasreceber;
    }

    public void setRcontasreceber(Integer rcontasreceber) {
        this.rcontasreceber = rcontasreceber;
    }

    public Integer getIoutroslancamentos() {
        return ioutroslancamentos;
    }

    public void setIoutroslancamentos(Integer ioutroslancamentos) {
        this.ioutroslancamentos = ioutroslancamentos;
    }

    public Integer getAoutroslancamentos() {
        return aoutroslancamentos;
    }

    public void setAoutroslancamentos(Integer aoutroslancamentos) {
        this.aoutroslancamentos = aoutroslancamentos;
    }

    public Integer getEoutroslancamentos() {
        return eoutroslancamentos;
    }

    public void setEoutroslancamentos(Integer eoutroslancamentos) {
        this.eoutroslancamentos = eoutroslancamentos;
    }

    public Integer getRvendas() {
        return rvendas;
    }

    public void setRvendas(Integer rvendas) {
        this.rvendas = rvendas;
    }

    public Integer getRpagamentos() {
        return rpagamentos;
    }

    public void setRpagamentos(Integer rpagamentos) {
        this.rpagamentos = rpagamentos;
    }

    public Integer getRconciliacao() {
        return rconciliacao;
    }

    public void setRconciliacao(Integer rconciliacao) {
        this.rconciliacao = rconciliacao;
    }

    public Integer getRreceber() {
        return rreceber;
    }

    public void setRreceber(Integer rreceber) {
        this.rreceber = rreceber;
    }

    public Integer getRpagar() {
        return rpagar;
    }

    public void setRpagar(Integer rpagar) {
        this.rpagar = rpagar;
    }

    public Integer getRfluxo() {
        return rfluxo;
    }

    public void setRfluxo(Integer rfluxo) {
        this.rfluxo = rfluxo;
    }

    public Integer getVendas() {
        return vendas;
    }

    public void setVendas(Integer vendas) {
        this.vendas = vendas;
    }

    public Integer getIvendas() {
        return ivendas;
    }

    public void setIvendas(Integer ivendas) {
        this.ivendas = ivendas;
    }

    public Integer getEvendas() {
        return evendas;
    }

    public void setEvendas(Integer evendas) {
        this.evendas = evendas;
    }

    public Integer getAvendas() {
        return avendas;
    }

    public void setAvendas(Integer avendas) {
        this.avendas = avendas;
    }

    public Integer getPvendas() {
        return pvendas;
    }

    public void setPvendas(Integer pvendas) {
        this.pvendas = pvendas;
    }

    public Integer getCvendas() {
        return cvendas;
    }

    public void setCvendas(Integer cvendas) {
        this.cvendas = cvendas;
    }

    public Integer getFvendas() {
        return fvendas;
    }

    public void setFvendas(Integer fvendas) {
        this.fvendas = fvendas;
    }

    public Integer getTipoplanocontas() {
        return tipoplanocontas;
    }

    public void setTipoplanocontas(Integer tipoplanocontas) {
        this.tipoplanocontas = tipoplanocontas;
    }

    public Integer getItipoplanocontas() {
        return itipoplanocontas;
    }

    public void setItipoplanocontas(Integer itipoplanocontas) {
        this.itipoplanocontas = itipoplanocontas;
    }

    public Integer getAtipoplanocontas() {
        return atipoplanocontas;
    }

    public void setAtipoplanocontas(Integer atipoplanocontas) {
        this.atipoplanocontas = atipoplanocontas;
    }

    public Integer getEtipoplanocontas() {
        return etipoplanocontas;
    }

    public void setEtipoplanocontas(Integer etipoplanocontas) {
        this.etipoplanocontas = etipoplanocontas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacesso != null ? idacesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.idacesso == null && other.idacesso != null) || (this.idacesso != null && !this.idacesso.equals(other.idacesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Acesso[ idacesso=" + idacesso + " ]";
    }
    
}
