/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.dao;

import br.com.ifs.model.Instituto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class InstitutoDAO extends BaseDAO {

    private List<Instituto> rsToList(ResultSet rs) throws Exception {
        List<Instituto> list = new ArrayList<Instituto>();
        while (rs.next()) {
            Instituto instituicao = new Instituto();
            instituicao.setIns_id(rs.getInt("ins_id"));
            instituicao.setIns_nome(rs.getString("ins_nome"));
            list.add(instituicao);
        }
        return list;
    }

    
    public int getSequence() throws Exception{
        try{
            String sql = "SELECT NEXTVAL('seq_instituto');";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
            return rs.getInt(1);
        }catch (Exception e){
            throw e;
        }finally {
            close();
        }
    }    
    
    public void create(Instituto instituto) throws Exception {
        try {
            instituto.setIns_id(getSequence());
            String sql = "INSERT INTO public.instituto(ins_id, ins_nome) VALUES (?, ?)";
            ps = create().prepareStatement(sql);
            ps.setObject(1, instituto.getIns_id());
            ps.setObject(2, instituto.getIns_nome());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void update(Instituto instituto) throws Exception {
        try {
            String sql = "UPDATE public.instituto SET ins_nome=? WHERE ins_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, instituto.getIns_nome());
            ps.setObject(2, instituto.getIns_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void delete(Instituto instituto) throws Exception {
        try {
            String sql = "DELETE FROM public.instituto WHERE ins_id = ?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, instituto.getIns_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    
    public List<Instituto> readAll() throws Exception {
        try {
            String sql = "SELECT ins_id, ins_nome FROM public.instituto";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }    

    public Instituto getByPK(int codigo) throws Exception {
        try {
            String sql = "SELECT ins_id, ins_nome FROM public.instituto WHERE ins_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            List<Instituto> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
}
