/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bo;

import br.com.ifs.dao.DaoFactory;
import br.com.ifs.model.UserType;
import br.com.ifs.dao.UserTypeDAO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class UserTypeBO {

    private static UserTypeBO userTypeBO;

    private UserTypeBO() {

    }

    public synchronized static UserTypeBO getInstance() {
        if (userTypeBO == null) {
            userTypeBO = new UserTypeBO();
        }
        return userTypeBO;
    }


    public List<UserType> getAll() throws Exception {
        try {
            UserTypeDAO userTypeDAO = new UserTypeDAO();
            userTypeDAO.setDaoFactory(DaoFactory.getInstance());
            return userTypeDAO.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    public UserType getById(int id) throws Exception {
        try {
            UserTypeDAO userTypeDAO = new UserTypeDAO();
            userTypeDAO.setDaoFactory(DaoFactory.getInstance());

            return userTypeDAO.getByPK(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }
}
