/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Converter;


import br.com.financemate.model.Planocontas;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kamila
 */
@FacesConverter(value="PlanoContasConverter")
public class PlanoContasConverter implements Converter{

     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Planocontas> listaPlanoConta = (List<Planocontas>) component.getAttributes().get("listaPlanoConta");
        for (Planocontas planoConta : listaPlanoConta) {
                if (planoConta.getDescricao().equalsIgnoreCase(value)) {
                    return planoConta;
                }
            }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Planocontas planoConta = (Planocontas) value;
            return planoConta.getDescricao();
        }
    }
    
}
