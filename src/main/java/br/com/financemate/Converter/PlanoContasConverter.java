/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Converter;


import br.com.financemate.facade.PlanoContasFacade;
import br.com.financemate.model.Planocontas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        PlanoContasFacade planoContasFacade = new PlanoContasFacade();
        int idPlano = Integer.parseInt(value);
        Planocontas plano;
        try {
            plano = planoContasFacade.consultar(idPlano);
            return plano;
        } catch (SQLException ex) {
            Logger.getLogger(PlanoContasConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }
    
}
