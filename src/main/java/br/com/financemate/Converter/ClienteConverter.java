/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.Converter;

import br.com.financemate.model.Cliente;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Greici
 */
@FacesConverter(value="ClienteConverter")
public class ClienteConverter implements Converter{
     
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Cliente> listaCliente = (List<Cliente>) component.getAttributes().get("listaCliente");
        for (Cliente cliente : listaCliente) {
                if (cliente.getNomeFantasia().equalsIgnoreCase(value)) {
                    return cliente;
                }
            }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Cliente cliente = (Cliente) value;
            return cliente.getNomeFantasia();
        }
    }
    
}
