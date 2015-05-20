 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financemate.Connection;


import java.sql.Connection;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;




/**
 *
 * @author Wolverine
 */

@ApplicationScoped
public class ConectionFactoryTM {

    private static EntityManager manager;
    private static Connection conn;


    public static EntityManager getConnection() {
        EntityManagerFactory emf = null;
        manager = null;
        emf = Persistence.createEntityManagerFactory("systmPU");
        manager = emf.createEntityManager();
        if (!manager.isOpen()) {
            JOptionPane.showMessageDialog(null, "Conexão fechada");
        }
        return manager;
    }
}
