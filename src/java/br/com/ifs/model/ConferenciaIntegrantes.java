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
public class ConferenciaIntegrantes {

    private int con_int_id;
    private Conferencia conferencia = new Conferencia();
    private Usuario usuario = new Usuario();

    public int getCon_int_id() {
        return con_int_id;
    }

    public void setCon_int_id(int con_int_id) {
        this.con_int_id = con_int_id;
    }

    public Conferencia getConferencia() {
        return conferencia;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
