/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.model;

/**
 *
 * @author Vinicius
 */
public class Usuario {
    private int usr_id;
    private String usr_nome;
    private String usr_email;
    private String usr_password;
    private Campos campo = new Campos();
    private UserType userType = new UserType();
    private boolean usr_status;

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_nome() {
        return usr_nome;
    }

    public void setUsr_nome(String usr_nome) {
        this.usr_nome = usr_nome;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_password() {
        return usr_password;
    }

    public void setUsr_password(String usr_password) {
        this.usr_password = usr_password;
    }

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isUsr_status() {
        return usr_status;
    }

    public void setUsr_status(boolean usr_status) {
        this.usr_status = usr_status;
    }
    
    
}
