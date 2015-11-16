/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean.contasPagar;

import br.com.financemate.Util.Formatacao;
import br.com.financemate.facade.ContasPagarFacade;
import br.com.financemate.model.Contaspagar;
import br.com.financemate.model.Movimentobanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Julio
 */
@Named
@ViewScoped
public class liberacaoContasPagarMB implements Serializable {

    private List<Contaspagar> listaContasPagar;
    private Contaspagar contasPagar;
    private String totalLiberadas;
    private List<Contaspagar> listaContasSelecionadas;
    private Date dataLiberação;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        contasPagar = (Contaspagar) session.getAttribute("contapagar");
        session.removeAttribute("contapagar");
        //liberarContasPagar();
        if (contasPagar == null) {
            contasPagar = new Contaspagar();
        }
    }

    public Date getDataLiberação() {
        return dataLiberação;
    }

    public void setDataLiberação(Date dataLiberação) {
        this.dataLiberação = dataLiberação;
    }

    public Contaspagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(Contaspagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public List<Contaspagar> getListaContasSelecionadas() {
        return listaContasSelecionadas;
    }

    public void setListaContasSelecionadas(List<Contaspagar> listaContasSelecionadas) {
        this.listaContasSelecionadas = listaContasSelecionadas;
    }

    public String getTotalLiberadas() {
        return totalLiberadas;
    }

    public void setTotalLiberadas(String totalLiberadas) {
        this.totalLiberadas = totalLiberadas;
    }

    public List<Contaspagar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<Contaspagar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
    }

    public String salvarContasLiberadas(Contaspagar conta) {
        ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
        contasPagarFacade.salvar(conta);
        RequestContext.getCurrentInstance().closeDialog(contasPagar);
        return "";
    }

    public String cancelar() {
        RequestContext.getCurrentInstance().closeDialog(null);
        return null;
    }

}
