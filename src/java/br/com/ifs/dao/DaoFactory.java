package br.com.ifs.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DaoFactory {

    private String driver="org.postgresql.Driver", url="jdbc:postgresql://127.0.0.1:5432/web1", user="postgres", pass="postgres";
    private static DaoFactory myInstance;
    private DaoFactory() {
        
    }
    
    public synchronized static DaoFactory getInstance() {
        if (myInstance == null) {
            myInstance = new DaoFactory();
        }
        return myInstance;
    }

    public synchronized static DaoFactory getInstance(String driver, String url, String user, String pass) {
        if (myInstance == null) {
            myInstance = new DaoFactory();
            myInstance.setDriver(driver);
            myInstance.setUrl(url);
            myInstance.setUser(user);
            myInstance.setPass(pass);
        }
        return myInstance;
    }
    
    public Connection getConnection() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, pass);
    }
    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
