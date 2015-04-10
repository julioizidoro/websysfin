/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.facade;


import br.com.financemate.dao.ContasPagarDao;
import br.com.financemate.model.Arquivocontaspagar;
import br.com.financemate.model.Contaspagar;
import br.com.financemate.modelView.Viewcontaspagar;
import br.com.financemate.modelView.Viewcontaspagarfluxo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wolverine
 */
public class ContasPagarFacade {
    
    ContasPagarDao contasPagarDao;
    
    public Contaspagar salvar(Contaspagar conta) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.salvar(conta);
    }
    
    public List<Contaspagar> listar(String sql) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.listar(sql);
    }
    
    public List<Contaspagar> listarContas(String sql) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.listarContas(sql);
    }
    
    public Contaspagar consultar(int idConta) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.consultar(idConta);
    }
    
    public void excluir(int idConta) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        contasPagarDao.excluir(idConta);
    }
    
    public Contaspagar consultarVenda(String sql) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.consultarVenda(sql);
    }
    
    public void salvarArquivo(Arquivocontaspagar arquivo) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        contasPagarDao.salvarArquivo(arquivo);
    }
    
    public Arquivocontaspagar consultarArquivo(int idContasPagar) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.consultarArquivo(idContasPagar);
    }
    
    public void excluirArquivo(int idArquivo) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        contasPagarDao.excluirArquivo(idArquivo);
    }
    
    public List<Viewcontaspagarfluxo> listaFluxo(String sql) throws SQLException{
        contasPagarDao = new ContasPagarDao();
        return contasPagarDao.listaFluxo(sql);
    }
}
