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
public class Campos {
    private int cam_id;
    private Instituto instituicao = new  Instituto();
    private String cam_nome;

    public int getCam_id() {
        return cam_id;
    }

    public void setCam_id(int cam_id) {
        this.cam_id = cam_id;
    }

    public Instituto getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituto instituicao) {
        this.instituicao = instituicao;
    }

    public String getCam_nome() {
        return cam_nome;
    }

    public void setCam_nome(String cam_nome) {
        this.cam_nome = cam_nome;
    }
    
    
}
