/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bo;

import br.com.ifs.dao.DaoFactory;
import br.com.ifs.model.ConferenciaIntegrantes;
import br.com.ifs.dao.ConferenciaIntegrantesDAO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class ConferenciaIntegrantesBO {
    private static ConferenciaIntegrantesBO conferenciaIntegrantesBO;

    private ConferenciaIntegrantesBO() {

    }
    public synchronized static ConferenciaIntegrantesBO getInstance() {
        if (conferenciaIntegrantesBO == null) {
            conferenciaIntegrantesBO = new ConferenciaIntegrantesBO();
        }
        return conferenciaIntegrantesBO;
    }

   public void inserir(ConferenciaIntegrantes conferenciaIntegrantes) throws Exception {
        try {
            ConferenciaIntegrantesDAO conferenciaIntegrantesDAO = new ConferenciaIntegrantesDAO();
            conferenciaIntegrantesDAO.setDaoFactory(DaoFactory.getInstance());
            conferenciaIntegrantesDAO.create(conferenciaIntegrantes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha no cadastro!");
        }
    }

    public void excluir(ConferenciaIntegrantes conferenciaIntegrantes) throws Exception {
        try {
            ConferenciaIntegrantesDAO conferenciaIntegrantesDAO = new ConferenciaIntegrantesDAO();
            conferenciaIntegrantesDAO.setDaoFactory(DaoFactory.getInstance());
            conferenciaIntegrantesDAO.delete(conferenciaIntegrantes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao excluir!");
        }
    }

    public List<ConferenciaIntegrantes> getAll() throws Exception {
        try {
            ConferenciaIntegrantesDAO conferenciaIntegrantesDAO = new ConferenciaIntegrantesDAO();
            conferenciaIntegrantesDAO.setDaoFactory(DaoFactory.getInstance());
            return conferenciaIntegrantesDAO.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    public ConferenciaIntegrantes getById(int id) throws Exception {
        try {
            ConferenciaIntegrantesDAO conferenciaIntegrantesDAO = new ConferenciaIntegrantesDAO();
            conferenciaIntegrantesDAO.setDaoFactory(DaoFactory.getInstance());

            return conferenciaIntegrantesDAO.getByPK(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }      
    
    public List<ConferenciaIntegrantes> getPorConferencia(int codigo) throws Exception {
        try {
            ConferenciaIntegrantesDAO conferenciaIntegrantesDAO = new ConferenciaIntegrantesDAO();
            conferenciaIntegrantesDAO.setDaoFactory(DaoFactory.getInstance());
            return conferenciaIntegrantesDAO.getPorConferencia(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }    
    
    public ConferenciaIntegrantes getByIntegrante(int conferencia, int integrante) throws Exception {
        try {
            ConferenciaIntegrantesDAO conferenciaIntegrantesDAO = new ConferenciaIntegrantesDAO();
            conferenciaIntegrantesDAO.setDaoFactory(DaoFactory.getInstance());

            return conferenciaIntegrantesDAO.getByIntegrante(conferencia,integrante);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }        
    
}
