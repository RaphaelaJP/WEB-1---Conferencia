/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.dao;

import br.com.ifs.model.Campos;
import br.com.ifs.model.Instituto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class CamposDAO extends BaseDAO {

    InstitutoDAO institutoDAO = new InstitutoDAO();

    @Override
    public void setDaoFactory(DaoFactory daoFactory) {
        super.setDaoFactory(daoFactory); //To change body of generated methods, choose Tools | Templates.
        institutoDAO.setDaoFactory(daoFactory);
    }

    private List<Campos> rsToList(ResultSet rs) throws Exception {
        List<Campos> list = new ArrayList<Campos>();
        while (rs.next()) {
            Campos campos = new Campos();
            campos.setCam_id(rs.getInt("cam_id"));
            campos.setCam_nome(rs.getString("cam_nome"));
            campos.setInstituicao(getInstituto(rs.getInt("ins_id")));
            list.add(campos);
        }
        return list;
    }

    public int getSequence() throws Exception {
        try {
            String sql = "SELECT NEXTVAL('seq_campos');";
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

    public void create(Campos campos) throws Exception {
        try {
            campos.setCam_id(getSequence());
            String sql = "INSERT INTO public.campos(cam_id, ins_id, cam_nome) VALUES (?, ?, ?)";
            ps = create().prepareStatement(sql);
            ps.setObject(1, campos.getCam_id());
            ps.setObject(2, campos.getInstituicao().getIns_id());
            ps.setObject(3, campos.getCam_nome());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void update(Campos campos) throws Exception {
        try {
            String sql = "UPDATE public.campos SET ins_id=?, cam_nome=?	WHERE cam_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, campos.getInstituicao().getIns_id());
            ps.setObject(2, campos.getCam_nome());
            ps.setObject(3, campos.getCam_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void delete(Campos campos) throws Exception {
        try {
            String sql = "DELETE FROM public.campos WHERE cam_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, campos.getCam_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<Campos> readAll() throws Exception {
        try {
            String sql = "SELECT * FROM public.campos ORDER BY cam_id";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public List<Campos> getByInstituto(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.campos WHERE ins_id = ? ORDER BY cam_nome";
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

    public Campos getByPK(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM public.campos WHERE cam_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            List<Campos> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    public Instituto getInstituto(int ins_id) throws Exception{
        return institutoDAO.getByPK(ins_id);
    }
}
