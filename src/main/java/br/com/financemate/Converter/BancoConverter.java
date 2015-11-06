/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Converter;


import br.com.financemate.facade.BancoFacade;
import br.com.financemate.model.Banco;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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
        try {
            BancoFacade bancoFacade = new BancoFacade();
            int idBanco = Integer.parseInt(value);
            Banco banco = bancoFacade.consultar(idBanco);
            return banco;
        } catch (SQLException ex) {
            Logger.getLogger(BancoConverter.class.getName()).log(Level.SEVERE, null, ex);
            context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro!" + ex));
            
        }
        return "";
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }
    
}
