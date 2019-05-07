/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.dao;


import br.com.ifs.model.Conferencia;
import br.com.ifs.model.Usuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.SessionUtil;

/**
 *
 * @author Vinicius
 */
public class ConferenciaDAO extends BaseDAO {

    private List<Conferencia> rsToList(ResultSet rs) throws Exception {
        List<Conferencia> list = new ArrayList<Conferencia>();
        while (rs.next()) {
            Conferencia conferencia = new Conferencia();
            conferencia.setCon_id(rs.getInt("con_id"));
            conferencia.setCon_nome(rs.getString("con_nome"));
            conferencia.setCon_id_sala(rs.getString("con_id_sala"));
            conferencia.setCon_data(rs.getDate("con_data"));
            conferencia.getUsuario().setUsr_id(rs.getInt("usr_id"));
            list.add(conferencia);
        }
        return list;
    }

    public int getSequence() throws Exception {
        try {
            String sql = "SELECT NEXTVAL('seq_conferencia');";
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

    public void create(Conferencia conferencia) throws Exception {
        try {
            conferencia.setCon_id(getSequence());
            String sql = "INSERT INTO public.conferencia("
                    + "con_id, con_nome, con_id_sala, con_data,usr_id) "
                    + "VALUES (?, ?, ?, ?, ?);";
            ps = create().prepareStatement(sql);
            ps.setObject(1, conferencia.getCon_id());
            ps.setObject(2, conferencia.getCon_nome());
            ps.setObject(3, conferencia.getCon_id_sala());
            Date date = new Date(conferencia.getCon_data().getTime());
            ps.setObject(4, date);

            Usuario usr = (Usuario)SessionUtil.getParam("USUARIOLogado");
            ps.setObject(5, usr.getUsr_id());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void update(Conferencia conferencia) throws Exception {
        try {
            String sql = "UPDATE public.conferencia SET con_nome=?, con_data=? WHERE con_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, conferencia.getCon_nome());
            
            Date date = new Date(conferencia.getCon_data().getTime());
            ps.setObject(2, date);
                       
            ps.setObject(3, conferencia.getCon_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void delete(Conferencia conferencia) throws Exception {
        try {
            String sql = "DELETE FROM public.conferencia WHERE con_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, conferencia.getCon_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<Conferencia> readAll() throws Exception {
        try {
            String sql = "SELECT * FROM public.conferencia ORDER BY con_data DESC";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public Conferencia getByPK(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.conferencia WHERE con_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            List<Conferencia> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    public List<Conferencia> getByUsuario(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.conferencia WHERE usr_id = ? AND con_data >= current_date ORDER BY con_data DESC";
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

}
