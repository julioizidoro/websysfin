/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {
    @Column(name = "dataInscricao")
    @Temporal(TemporalType.DATE)
    private Date dataInscricao;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Column(name = "dataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 50)
    @Column(name = "PaisNascimento")
    private String paisNascimento;
    @Size(max = 50)
    @Column(name = "cidadeNascimento")
    private String cidadeNascimento;
    @Size(max = 45)
    @Column(name = "rg")
    private String rg;
    @Size(max = 14)
    @Column(name = "cpf")
    private String cpf;
    @Size(max = 9)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 50)
    @Column(name = "numeroPassaporte")
    private String numeroPassaporte;
    @Column(name = "dataExpedicaoPassaporte")
    @Temporal(TemporalType.DATE)
    private Date dataExpedicaoPassaporte;
    @Column(name = "validadePassaporte")
    @Temporal(TemporalType.DATE)
    private Date validadePassaporte;
    @Size(max = 20)
    @Column(name = "tipologradouro")
    private String tipologradouro;
    @Size(max = 50)
    @Column(name = "pais")
    private String pais;
    @Size(max = 14)
    @Column(name = "foneResidencial")
    private String foneResidencial;
    @Size(max = 14)
    @Column(name = "foneCelular")
    private String foneCelular;
    @Size(max = 14)
    @Column(name = "foneComercial")
    private String foneComercial;
    @Size(max = 50)
    @Column(name = "profissao")
    private String profissao;
    @Size(max = 100)
    @Column(name = "skype")
    private String skype;
    @Size(max = 100)
    @Column(name = "facebook")
    private String facebook;
    @Size(max = 100)
    @Column(name = "nomeMae")
    private String nomeMae;
    @Size(max = 50)
    @Column(name = "profissaoMae")
    private String profissaoMae;
    @Size(max = 14)
    @Column(name = "foneMae")
    private String foneMae;
    @Size(max = 100)
    @Column(name = "nomePai")
    private String nomePai;
    @Size(max = 50)
    @Column(name = "profissaoPai")
    private String profissaoPai;
    @Size(max = 14)
    @Column(name = "fonePai")
    private String fonePai;
    @Size(max = 50)
    @Column(name = "escolaridade")
    private String escolaridade;
    @Size(max = 100)
    @Column(name = "complementopublicidade")
    private String complementopublicidade;
    @Size(max = 10)
    @Column(name = "tipoCliente")
    private String tipoCliente;
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "dataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "dataTermino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @Size(max = 20)
    @Column(name = "estadoCivil")
    private String estadoCivil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Vendas> vendasList;
    @JoinColumn(name = "publicidade_idpublicidade", referencedColumnName = "idpublicidade")
    @ManyToOne(optional = false)
    private Publicidade publicidade;
    @JoinColumn(name = "unidadeNegocio_idunidadeNegocio", referencedColumnName = "idunidadeNegocio")
    @ManyToOne(optional = false)
    private Unidadenegocio unidadenegocio;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Size(max = 100)
    @Column(name = "razaoSocial")
    private String razaoSocial;
    @Size(max = 100)
    @Column(name = "nomeFantasia")
    private String nomeFantasia;
    @Size(max = 18)
    @Column(name = "cnpj")
    private String cnpj;
    @Size(max = 20)
    @Column(name = "tipoLogradouro")
    private String tipoLogradouro;
    @Size(max = 100)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 20)
    @Column(name = "numero")
    private String numero;
    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 50)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 50)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 2)
    @Column(name = "estado")
    private String estado;
    @Size(max = 9)
    @Column(name = "cep")
    private String cep;
    @Size(max = 14)
    @Column(name = "foneFixo")
    private String foneFixo;
    @Size(max = 100)
    @Column(name = "administrador")
    private String administrador;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Column(name = "contaReceita")
    private Integer contaReceita;
    @Column(name = "contaRecebimento")
    private Integer contaRecebimento;
    @Column(name = "codigosystm")
    private Integer codigosystm;
    @Size(max = 15)
    @Column(name = "visualizacao")
    private String visualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Banco> bancoList;
    @JoinColumn(name = "tipoplanocontas_idtipoplanocontas", referencedColumnName = "idtipoplanocontas")
    @ManyToOne(optional = false)
    private Tipoplanocontas tipoplanocontas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Contasreceber> contasreceberList;
    @Transient
    private boolean selecionado;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getFoneFixo() {
        return foneFixo;
    }

    public void setFoneFixo(String foneFixo) {
        this.foneFixo = foneFixo;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContaReceita() {
        return contaReceita;
    }

    public void setContaReceita(Integer contaReceita) {
        this.contaReceita = contaReceita;
    }

    public Integer getContaRecebimento() {
        return contaRecebimento;
    }

    public void setContaRecebimento(Integer contaRecebimento) {
        this.contaRecebimento = contaRecebimento;
    }

    public Integer getCodigosystm() {
        return codigosystm;
    }

    public void setCodigosystm(Integer codigosystm) {
        this.codigosystm = codigosystm;
    }

    public String getVisualizacao() {
        return visualizacao;
    }

    public void setVisualizacao(String visualizacao) {
        this.visualizacao = visualizacao;
    }

    public List<Banco> getBancoList() {
        return bancoList;
    }

    public void setBancoList(List<Banco> bancoList) {
        this.bancoList = bancoList;
    }

    public Tipoplanocontas getTipoplanocontas() {
        return tipoplanocontas;
    }

    public void setTipoplanocontas(Tipoplanocontas tipoplanocontas) {
        this.tipoplanocontas = tipoplanocontas;
    }

    public List<Contasreceber> getContasreceberList() {
        return contasreceberList;
    }

    public void setContasreceberList(List<Contasreceber> contasreceberList) {
        this.contasreceberList = contasreceberList;
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
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Cliente[ idcliente=" + idcliente + " ]";
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPaisNascimento() {
        return paisNascimento;
    }

    public void setPaisNascimento(String paisNascimento) {
        this.paisNascimento = paisNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNumeroPassaporte() {
        return numeroPassaporte;
    }

    public void setNumeroPassaporte(String numeroPassaporte) {
        this.numeroPassaporte = numeroPassaporte;
    }

    public Date getDataExpedicaoPassaporte() {
        return dataExpedicaoPassaporte;
    }

    public void setDataExpedicaoPassaporte(Date dataExpedicaoPassaporte) {
        this.dataExpedicaoPassaporte = dataExpedicaoPassaporte;
    }

    public Date getValidadePassaporte() {
        return validadePassaporte;
    }

    public void setValidadePassaporte(Date validadePassaporte) {
        this.validadePassaporte = validadePassaporte;
    }

    public String getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(String tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFoneResidencial() {
        return foneResidencial;
    }

    public void setFoneResidencial(String foneResidencial) {
        this.foneResidencial = foneResidencial;
    }

    public String getFoneCelular() {
        return foneCelular;
    }

    public void setFoneCelular(String foneCelular) {
        this.foneCelular = foneCelular;
    }

    public String getFoneComercial() {
        return foneComercial;
    }

    public void setFoneComercial(String foneComercial) {
        this.foneComercial = foneComercial;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getProfissaoMae() {
        return profissaoMae;
    }

    public void setProfissaoMae(String profissaoMae) {
        this.profissaoMae = profissaoMae;
    }

    public String getFoneMae() {
        return foneMae;
    }

    public void setFoneMae(String foneMae) {
        this.foneMae = foneMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getProfissaoPai() {
        return profissaoPai;
    }

    public void setProfissaoPai(String profissaoPai) {
        this.profissaoPai = profissaoPai;
    }

    public String getFonePai() {
        return fonePai;
    }

    public void setFonePai(String fonePai) {
        this.fonePai = fonePai;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getComplementopublicidade() {
        return complementopublicidade;
    }

    public void setComplementopublicidade(String complementopublicidade) {
        this.complementopublicidade = complementopublicidade;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public List<Vendas> getVendasList() {
        return vendasList;
    }

    public void setVendasList(List<Vendas> vendasList) {
        this.vendasList = vendasList;
    }

    public Publicidade getPublicidade() {
        return publicidade;
    }

    public void setPublicidade(Publicidade publicidade) {
        this.publicidade = publicidade;
    }

    public Unidadenegocio getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(Unidadenegocio unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }
    
}
