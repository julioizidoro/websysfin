/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Converter;

import br.com.financemate.Controller.BancoController;
import br.com.financemate.model.Banco;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kamila
 */
@FacesConverter(forClass=Banco.class)
public class BancoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BancoController bancoController = new BancoController();
        int idBanco = Integer.parseInt(value);
        Banco banco = bancoController.consultar(idBanco);
        return banco;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }
    
}
