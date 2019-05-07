/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.dao;

import br.com.ifs.model.UserType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class UserTypeDAO extends BaseDAO {

    private List<UserType> rsToList(ResultSet rs) throws Exception {
        List<UserType> list = new ArrayList<UserType>();
        while (rs.next()) {
            UserType userType = new UserType();
            userType.setUsr_typ_id(rs.getInt("usr_typ_id"));
            userType.setUsr_typ_nome(rs.getString("usr_typ_nome"));
            list.add(userType);
        }
        return list;
    }

    public UserType getByPK(int codigo) throws Exception {
        try {
            String sql = "SELECT usr_typ_id, usr_typ_nome FROM public.user_type WHERE usr_typ_id=?";
            ps = create().prepareStatement(sql);
            ps.setObject(1, codigo);
            rs = ps.executeQuery();
            List<UserType> list = rsToList(rs);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    public List<UserType> readAll() throws Exception {
        try {
            String sql = "SELECT usr_typ_id, usr_typ_nome FROM public.user_type ORDER BY usr_typ_nome";
            ps = create().prepareStatement(sql);
            rs = ps.executeQuery();
            return rsToList(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }        
}
