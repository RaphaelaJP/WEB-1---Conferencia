package br.com.ifs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDAO {
    private DaoFactory daoFactory;
    private Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    public Connection create() throws Exception {
        con = daoFactory.getConnection();
        return con;
    }
    
    public void close() {
        try {
            rs.close();
        } catch (Exception e) {            
        }
        try {
            ps.close();
        } catch (Exception e) {
            
        }
        try {
            con.close();
        } catch (Exception e) {
            
        }        
    }
    /**
     * @return the daoFactory
     */
    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    /**
     * @param daoFactory the daoFactory to set
     */
    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
        
}
