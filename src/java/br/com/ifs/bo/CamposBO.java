/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bo;

import br.com.ifs.dao.DaoFactory;
import br.com.ifs.model.Campos;
import br.com.ifs.dao.CamposDAO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class CamposBO {

    private static CamposBO camposBO;

    public CamposBO() {

    }

    public synchronized static CamposBO getInstance() {
        if (camposBO == null) {
            camposBO = new CamposBO();
        }
        return camposBO;
    }

    public void inserir(Campos campos) throws Exception {
        try {
            CamposDAO camposDAO = new CamposDAO();
            camposDAO.setDaoFactory(DaoFactory.getInstance());
            camposDAO.create(campos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha no cadastro!");
        }
    }

    public void alterar(Campos campos) throws Exception {
        try {
            CamposDAO camposDAO = new CamposDAO();
            camposDAO.setDaoFactory(DaoFactory.getInstance());
            camposDAO.update(campos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao alterar!");
        }
    }

    public void excluir(Campos campos) throws Exception {
        try {
            CamposDAO camposDAO = new CamposDAO();
            camposDAO.setDaoFactory(DaoFactory.getInstance());
            camposDAO.delete(campos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao excluir!");
        }
    }

    public List<Campos> getAll() throws Exception {
        try {
            CamposDAO camposDAO = new CamposDAO();
            camposDAO.setDaoFactory(DaoFactory.getInstance());
            return camposDAO.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }
    
    public List<Campos> getByInstituto(int id) throws Exception {
        try {
            CamposDAO camposDAO = new CamposDAO();
            camposDAO.setDaoFactory(DaoFactory.getInstance());

            return camposDAO.getByInstituto(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }    

    public Campos getById(int id) throws Exception {
        try {
            CamposDAO camposDAO = new CamposDAO();
            camposDAO.setDaoFactory(DaoFactory.getInstance());

            return camposDAO.getByPK(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }
}
