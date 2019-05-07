/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vinicius
 */
public class Conferencia {
 
    private int con_id;
    private String con_nome;
    
    Calendar calendar = Calendar.getInstance();   
    private String con_id_sala = ("CONF"+String.valueOf(calendar.getTimeInMillis()));;
    private Date con_data;
    private Usuario usuario = new Usuario();

    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getCon_nome() {
        return con_nome;
    }

    public void setCon_nome(String con_nome) {
        this.con_nome = con_nome;
    }

    public String getCon_id_sala() {
        return con_id_sala;
    }

    public void setCon_id_sala(String con_id_sala) {
        this.con_id_sala = con_id_sala;
    }

    public Date getCon_data() {
        return con_data;
    }

    public void setCon_data(Date con_data) {
        this.con_data = con_data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
    
}
