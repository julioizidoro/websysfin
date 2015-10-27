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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Size(max = 9)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 7)
    @Column(name = "situacao")
    private String situacao;
    @Size(max = 50)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 200)
    @Column(name = "localsalvar")
    private String localsalvar;
    @Size(max = 200)
    @Column(name = "locarlvisualizar")
    private String locarlvisualizar;
    @Size(max = 50)
    @Column(name = "departamento")
    private String departamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Vendas> vendasList;
    @JoinColumn(name = "grupoAcesso_idgrupoAcesso", referencedColumnName = "idgrupoAcesso")
    @ManyToOne(optional = false)
    private Grupoacesso grupoacesso;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
    @Size(max = 45)
    @Column(name = "senha")
    private String senha;
    @Column(name = "cliente")
    private Integer cliente;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 200)
    @Column(name = "localSalvar")
    private String localSalvar;
    @JoinColumn(name = "tipoacesso_idtipoacesso", referencedColumnName = "idtipoacesso")
    @ManyToOne(optional = false)
    private Tipoacesso tipoacesso;
    @Transient
    private boolean selecionado;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalSalvar() {
        return localSalvar;
    }

    public void setLocalSalvar(String localSalvar) {
        this.localSalvar = localSalvar;
    }

    public Tipoacesso getTipoacesso() {
        return tipoacesso;
    }

    public void setTipoacesso(Tipoacesso tipoacesso) {
        this.tipoacesso = tipoacesso;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Usuario[ idusuario=" + idusuario + " ]";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLocalsalvar() {
        return localsalvar;
    }

    public void setLocalsalvar(String localsalvar) {
        this.localsalvar = localsalvar;
    }

    public String getLocarlvisualizar() {
        return locarlvisualizar;
    }

    public void setLocarlvisualizar(String locarlvisualizar) {
        this.locarlvisualizar = locarlvisualizar;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Vendas> getVendasList() {
        return vendasList;
    }

    public void setVendasList(List<Vendas> vendasList) {
        this.vendasList = vendasList;
    }

    public Grupoacesso getGrupoacesso() {
        return grupoacesso;
    }

    public void setGrupoacesso(Grupoacesso grupoacesso) {
        this.grupoacesso = grupoacesso;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }
    
}
