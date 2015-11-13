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
    
    private List<Contaspagar> listaContasPagar;
    
    @PostConstruct
    public void init(){
        liberarContasPagar();
    }

    public List<Contaspagar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<Contaspagar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
    }
    
    
    
    public void liberarContasPagar() {
        List<Contaspagar> listaContasSelecionadas = new ArrayList<Contaspagar>();
        for (int i = 0; i < listaContasPagar.size(); i++) {
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