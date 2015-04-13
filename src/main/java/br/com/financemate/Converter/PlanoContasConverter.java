/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Converter;

import br.com.financemate.Controller.PlanoContasController;
import br.com.financemate.model.Planocontas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kamila
 */
@FacesConverter(forClass=Planocontas.class)
public class PlanoContasConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PlanoContasController planoContasController = new PlanoContasController();
        int idPlano = Integer.parseInt(value);
        Planocontas plano = planoContasController.consultar(idPlano);
        return plano;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }
    
}
