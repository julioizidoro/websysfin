/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.ManageBean.contasPagar;

import br.com.financemate.model.Contaspagar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Julio
 */

@Named
@ViewScoped
public class liberacaoContasPagarMB implements Serializable{
    
    private List<Contaspagar> listaLiberadas;
    private Contaspagar Contaspagar;
    
    @PostConstruct
    public void init(){
        liberarContasPagar();
    }

    public Contaspagar getContaspagar() {
        return Contaspagar;
    }

    public void setContaspagar(Contaspagar Contaspagar) {
        this.Contaspagar = Contaspagar;
    }

    
    
    public List<Contaspagar> getListaLiberadas() {
        return listaLiberadas;
    }

    public void setListaLiberadas(List<Contaspagar> listaLiberadas) {
        this.listaLiberadas = listaLiberadas;
    }
    
    
    
    public void liberarContasPagar() {
        List<Contaspagar> listaContasSelecionadas = new ArrayList<Contaspagar>();
        for (int i = 0; i < listaContasSelecionadas.size(); i++) {
            Contaspagar contaspagar = new Contaspagar();
            if (contaspagar.isSelecionado()) {
                listaContasSelecionadas.add(contaspagar);
            }

        }
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return null;
    }
}