/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bo;

import br.com.ifs.dao.DaoFactory;
import br.com.ifs.model.Usuario;
import br.com.ifs.dao.UsuarioDAO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class UsuarioBO {

    private static UsuarioBO usuarioBO;

    public UsuarioBO() {

    }

    public synchronized static UsuarioBO getInstance() {
        if (usuarioBO == null) {
            usuarioBO = new UsuarioBO();
        }
        return usuarioBO;
    }

    public void inserir(Usuario usuario) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            usuarioDAO.create(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha no cadastro!");
        }
    }

    public void alterar(Usuario usuario) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            usuarioDAO.update(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao alterar!");
        }
    }

    public void excluir(Usuario usuario) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            usuarioDAO.delete(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao excluir!");
        }
    }

    public List<Usuario> getAll() throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    /*
    public List<Usuario> getGerenteCampo(int codigo) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.readGerenteCampo(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    public List<Usuario> getDiretorCampo(int codigo) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.readDiretorCampo(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }
     */
    public Usuario getById(int id) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());

            return usuarioDAO.getByPK(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }

    public Usuario login(Usuario usuario) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.setDaoFactory(DaoFactory.getInstance());
        return usuarioDAO.login(usuario.getUsr_email(), usuario.getUsr_password());
    }

    public List<Usuario> getGerenteAndDiretor(int campo) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.getGerenteAndDiretor(campo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }

    public List<Usuario> getDiretorAndReitor(int instituto) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.getDiretorAndReitor(instituto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }
    
    public List<Usuario> getReitores() throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.getReitores();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha na consulta!");
        }
    }    

    public Usuario getDiretorCampo(int id) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.getDiretorCampo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }

    public Usuario getReitorInstituto(int id) throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setDaoFactory(DaoFactory.getInstance());
            return usuarioDAO.getReitorInstituto(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao consultar!");
        }
    }
}
