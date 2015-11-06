/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.facade;


import br.com.financemate.dao.ContasReceberDao;
import br.com.financemate.model.Contasreceber;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Wolverine
 */
public class ContasReceberFacade {
    
    ContasReceberDao contasReceberDao;
    
    public Contasreceber salvar(Contasreceber conta) throws SQLException{
        contasReceberDao = new ContasReceberDao();
        return contasReceberDao.salvar(conta);
    }
    
    public List<Contasreceber> listar(String sql) throws SQLException{
        contasReceberDao = new ContasReceberDao();
        return contasReceberDao.listar(sql);  
    }
    
    public Contasreceber consultar(int idConta) throws SQLException{
        contasReceberDao = new ContasReceberDao();
        return contasReceberDao.consultar(idConta);
    }
    
    public void excluir(int idConta) throws SQLException{
        contasReceberDao = new ContasReceberDao();
        contasReceberDao.excluir(idConta);
    }
    
    public Contasreceber consultarVendaFornecedor(int idVenda) throws SQLException{
        contasReceberDao = new ContasReceberDao();
        return contasReceberDao.consultarVendaFornecedor(idVenda);
    }
    
}
