/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.dao;

import br.com.ifs.model.ConferenciaIntegrantes;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class ConferenciaIntegrantesDAO extends BaseDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void setDaoFactory(DaoFactory daoFactory) {
        super.setDaoFactory(daoFactory); //To change body of generated methods, choose Tools | Templates.
        usuarioDAO.setDaoFactory(daoFactory);
    }

    private List<ConferenciaIntegrantes> rsToList(ResultSet rs) throws Exception {
        List<ConferenciaIntegrantes> list = new ArrayList<ConferenciaIntegrantes>();
        while (rs.next()) {
            ConferenciaIntegrantes conferenciaIntegrantes = new ConferenciaIntegrantes();
            conferenciaIntegrantes.setCon_int_id(rs.getInt("con_int_id"));
            conferenciaIntegrantes.getConferencia().setCon_id(rs.getInt("con_id"));
            conferenciaIntegrantes.setUsuario(usuarioDAO.getByPK(rs.getInt("usr_id")));
            list.add(conferenciaIntegrantes);
        }
        return list;
    }

    public int getSequence() throws Exception {
        try {
            String sql = "SELECT NEXTVAL('seq_conferenciaIntegrantes');";
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

    public void create(ConferenciaIntegrantes conferenciaIntegrantes) throws Exception {
        try {
            conferenciaIntegrantes.setCon_int_id(getSequence());
            String sql = "INSERT INTO public.conferencia_integrantes("
                    + "con_int_id, con_id, usr_id) "
                    + "VALUES (?, ?, ?)";
            ps = create().prepareStatement(sql);
            ps.setObject(1, conferenciaIntegrantes.getCon_int_id());
            ps.setObject(2, conferenciaIntegrantes.getConferencia().getCon_id());
            ps.setObject(3, conferenciaIntegrantes.getUsuario().getUsr_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void delete(ConferenciaIntegrantes conferenciaIntegrantes) throws Exception {
        try {
            String sql = "DELETE FROM public.conferencia_integrantes WHERE con_int_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, conferenciaIntegrantes.getCon_int_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<ConferenciaIntegrantes> readAll() throws Exception {
        try {
            String sql = "SELECT * FROM public.conferencia_integrantes ORDER BY con_int_id DESC";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public ConferenciaIntegrantes getByPK(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.conferencia_integrantes WHERE con_int_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            List<ConferenciaIntegrantes> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    public List<ConferenciaIntegrantes> getPorConferencia(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.conferencia_integrantes WHERE con_id = ? ORDER BY con_int_id DESC";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public ConferenciaIntegrantes getByIntegrante(int conferencia, int integrante) throws Exception {
        try {
            String sql = "SELECT con_int_id, con_id, usr_id "
                    + "	FROM public.conferencia_integrantes WHERE con_id = ? AND usr_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, conferencia);
            ps.setObject(2, integrante);
            rs = ps.executeQuery();
            List<ConferenciaIntegrantes> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

}
