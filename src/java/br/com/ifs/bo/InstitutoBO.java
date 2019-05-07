/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bo;

import br.com.ifs.dao.DaoFactory;
import br.com.ifs.model.Instituto;
import br.com.ifs.dao.InstitutoDAO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class InstitutoBO {

    private static InstitutoBO institutoBO;

    public InstitutoBO() {

    }

    public synchronized static InstitutoBO getInstance() {
        if (institutoBO == null) {
            institutoBO = new InstitutoBO();
        }
        return institutoBO;
    }

    public void inserir(Instituto instituto) throws Exception {
        try {
            InstitutoDAO institutoDao = new InstitutoDAO();
            institutoDao.setDaoFactory(DaoFactory.getInstance());
            institutoDao.create(instituto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha no cadastro!");
        }
    }

    public void alterar(Instituto instituto) throws Exception {
        try {
            InstitutoDAO institutoDao = new InstitutoDAO();
            institutoDao.setDaoFactory(DaoFactory.getInstance());
            institutoDao.update(instituto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao alterar!");
        }
    }

    public void excluir(Instituto instituto) throws Exception {
        try {
            InstitutoDAO institutoDao = new InstitutoDAO();
            institutoDao.setDaoFactory(DaoFactory.getInstance());
            institutoDao.delete(instituto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao excluir!");
        }
    }

    public List<Instituto> getAll() throws Exception {
        try {
            InstitutoDAO institutoDao = new InstitutoDAO();
            institutoDao.setDaoFactory(DaoFactory.getInstance());
            return institutoDao.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    public Instituto getById(int id) throws Exception {
        try {
            InstitutoDAO institutoDao = new InstitutoDAO();
            institutoDao.setDaoFactory(DaoFactory.getInstance());

            return institutoDao.getByPK(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }
}
