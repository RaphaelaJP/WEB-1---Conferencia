/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.dao;

import br.com.ifs.model.Campos;
import br.com.ifs.model.UserType;
import br.com.ifs.model.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class UsuarioDAO extends BaseDAO {

    CamposDAO camposDAO = new CamposDAO();
    UserTypeDAO userTypeDAO = new UserTypeDAO();

    @Override
    public void setDaoFactory(DaoFactory daoFactory) {
        super.setDaoFactory(daoFactory); //To change body of generated methods, choose Tools | Templates.
        camposDAO.setDaoFactory(daoFactory);
        userTypeDAO.setDaoFactory(daoFactory);
    }

    private List<Usuario> rsToList(ResultSet rs) throws Exception {
        List<Usuario> list = new ArrayList<Usuario>();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setUsr_id(rs.getInt("usr_id"));
            usuario.setUsr_nome(rs.getString("usr_name"));
            usuario.setUsr_email(rs.getString("usr_email"));
            usuario.setUsr_password(rs.getString("usr_passowrd"));
            usuario.setCampo(getCampo(rs.getInt("cam_id")));
            usuario.setUserType(getUserType(rs.getInt("usr_typ_id")));
            usuario.setUsr_status(rs.getBoolean("usr_status"));

            list.add(usuario);
        }
        return list;
    }

    public int getSequence() throws Exception {
        try {
            String sql = "SELECT NEXTVAL('seq_usuario');";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
            return rs.getInt(1);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void create(Usuario usuario) throws Exception {
        try {
            usuario.setUsr_id(getSequence());
            String sql = "INSERT INTO public.usuario(usr_id, usr_name, usr_email, usr_passowrd, cam_id, usr_typ_id, usr_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = create().prepareStatement(sql);
            ps.setObject(1, usuario.getUsr_id());
            ps.setObject(2, usuario.getUsr_nome());
            ps.setObject(3, usuario.getUsr_email());
            ps.setObject(4, usuario.getUsr_password());
            ps.setObject(5, usuario.getCampo().getCam_id());
            ps.setObject(6, usuario.getUserType().getUsr_typ_id());
            ps.setObject(7, usuario.isUsr_status());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void update(Usuario usuario) throws Exception {
        try {
            String sql = "UPDATE public.usuario "
                    + "SET usr_name=?, usr_email=?, usr_passowrd=?, cam_id=?, usr_typ_id=?, usr_status=? "
                    + "WHERE usr_id=?";
            ps = create().prepareStatement(sql);
            ps = create().prepareStatement(sql);
            ps.setObject(1, usuario.getUsr_nome());
            ps.setObject(2, usuario.getUsr_email());
            ps.setObject(3, usuario.getUsr_password());
            ps.setObject(4, usuario.getCampo().getCam_id());
            ps.setObject(5, usuario.getUserType().getUsr_typ_id());
            ps.setObject(6, usuario.isUsr_status());
            ps.setObject(7, usuario.getUsr_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void delete(Usuario usuario) throws Exception {
        try {
            String sql = "DELETE FROM public.usuario WHERE usr_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, usuario.getUsr_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<Usuario> readAll() throws Exception {
        try {
            String sql = "SELECT * FROM public.usuario ORDER BY usr_name";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public Usuario getByPK(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.usuario WHERE usr_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            List<Usuario> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    public Usuario login(String email, String senha) throws Exception {
        try {
            String sql = "SELECT * FROM public.usuario WHERE usr_email=? AND usr_passowrd=? AND usr_status = true";
            ps = create().prepareStatement(sql);
            ps.setObject(1, email);
            ps.setObject(2, senha);
            rs = ps.executeQuery();
            List<Usuario> list = rsToList(rs);

            //resultSetObject(list.get(0));
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    public Usuario getDiretorCampo(int campo) throws Exception {
        try {
            String sql = "SELECT * FROM public.usuario WHERE cam_id = ? AND usr_typ_id = 3";
            ps = create().prepareStatement(sql);
            ps.setObject(1, campo);
            rs = ps.executeQuery();
            List<Usuario> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public Usuario getReitorInstituto(int instituto) throws Exception {
        try {
            String sql = "SELECT A.* FROM usuario A "
                    + "INNER JOIN campos B ON A.cam_id = B.cam_id "
                    + "WHERE B.ins_id = ? AND A.usr_typ_id = 2";
            ps = create().prepareStatement(sql);
            ps.setObject(1, instituto);
            rs = ps.executeQuery();
            List<Usuario> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<Usuario> getGerenteAndDiretor(int campo) throws Exception {
        try {
            String sql = "SELECT A.* FROM usuario A "
                    + "WHERE A.cam_id = ? AND A.usr_typ_id IN (3,4)";
            ps = create().prepareStatement(sql);
            ps.setObject(1, campo);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<Usuario> getDiretorAndReitor(int instituo) throws Exception {
        try {
            String sql = "SELECT A.* FROM usuario A "
                    + "INNER JOIN campos B ON A.cam_id = B.cam_id "
                    + "WHERE B.ins_id = ? AND A.usr_typ_id IN (3,2)";
            ps = create().prepareStatement(sql);
            ps.setObject(1, instituo);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public List<Usuario> getReitores() throws Exception {
        try {
            String sql = "SELECT A.* FROM usuario A "                    
                    + "WHERE A.usr_typ_id = 2";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }    

    private Campos getCampo(int cam_id) throws Exception {
        if (cam_id > 0) {
            return camposDAO.getByPK(cam_id);
        }
        return null;
    }

    private UserType getUserType(int typNrId) throws Exception {
        return userTypeDAO.getByPK(typNrId);
    }
}
