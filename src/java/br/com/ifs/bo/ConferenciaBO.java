/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bo;

import br.com.ifs.dao.DaoFactory;
import br.com.ifs.model.Conferencia;
import br.com.ifs.dao.ConferenciaDAO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class ConferenciaBO {
    private static ConferenciaBO conferenciaBO;

    public ConferenciaBO() {

    }
    public synchronized static ConferenciaBO getInstance() {
        if (conferenciaBO == null) {
            conferenciaBO = new ConferenciaBO();
        }
        return conferenciaBO;
    }

   public void inserir(Conferencia conferencia) throws Exception {
        try {
            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            conferenciaDAO.setDaoFactory(DaoFactory.getInstance());
            conferenciaDAO.create(conferencia);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha no cadastro!");
        }
    }

    public void alterar(Conferencia conferencia) throws Exception {
        try {
            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            conferenciaDAO.setDaoFactory(DaoFactory.getInstance());
            conferenciaDAO.update(conferencia);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao alterar!");
        }
    }

    public void excluir(Conferencia conferencia) throws Exception {
        try {
            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            conferenciaDAO.setDaoFactory(DaoFactory.getInstance());
            conferenciaDAO.delete(conferencia);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao excluir!");
        }
    }

    public List<Conferencia> getAll() throws Exception {
        try {
            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            conferenciaDAO.setDaoFactory(DaoFactory.getInstance());
            return conferenciaDAO.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    public Conferencia getById(int id) throws Exception {
        try {
            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            conferenciaDAO.setDaoFactory(DaoFactory.getInstance());

            return conferenciaDAO.getByPK(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }      
    
    public List<Conferencia> getByUsuario(int codigo) throws Exception {
        try {
            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            conferenciaDAO.setDaoFactory(DaoFactory.getInstance());
            return conferenciaDAO.getByUsuario(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }    
    
}
