/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.facade;

import br.com.financemate.dao.BancoDao;
import br.com.financemate.model.Banco;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Wolverine
 */
public class BancoFacade {
    
    BancoDao bancoDao;
    
    public Banco salvar(Banco banco) throws SQLException{
        bancoDao = new BancoDao();
        return bancoDao.salvar(banco);
    }
    
    public List<Banco> listar(int idCliente) throws SQLException{
        bancoDao = new BancoDao();
        return bancoDao.listar(idCliente);
    }
    
    public Banco consultar(int idBanco) throws SQLException{
        bancoDao = new BancoDao();
        return bancoDao.consultar(idBanco);
    }
    
    public Banco consultar(int idCliente, String nome) throws SQLException{
        bancoDao = new BancoDao();
        return bancoDao.consultar(idCliente, nome);
    }
    
}
