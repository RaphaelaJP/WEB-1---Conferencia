/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bean;

import br.com.ifs.bo.CamposBO;
import br.com.ifs.bo.InstitutoBO;
import br.com.ifs.model.Campos;
import br.com.ifs.model.Instituto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Vinicius
 */
@Named(value = "camposMB")
@RequestScoped

public class CamposBean {

    private boolean pnCadastrar = true, pnConsulta = false;
    private Campos campos = new Campos();
    private List<Campos> lista;
    
    
    
    @PostConstruct
    public void pageLoad() {
        try {
            //lista = CamposBO.getInstance().getAll();
        } catch (Exception ex) {
            Logger.getLogger(CamposBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Campos> getAllCampos() throws Exception {
        return CamposBO.getInstance().getAll();        
    }    
    
    
    public String cadastrarAction() {
        try {
            if (campos.getCam_id() == 0) {
                CamposBO.getInstance().inserir(campos);                
            }else{
                CamposBO.getInstance().alterar(campos);
            }
            lista = getAllCampos();
            campos = new Campos();
            pnCadastrar = false;
            pnConsulta = true;
        } catch (Exception ex) {
            Logger.getLogger(CamposBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String novoCampos() {
        pnCadastrar = true;
        pnConsulta = false;
        return null;
    }

    public String todosAction() {
        pnCadastrar = false;
        pnConsulta = true;
        return null;
    }

    public String deletar(Campos i) throws Exception {
        CamposBO.getInstance().excluir(i);
        campos = new Campos();
        lista = getAllCampos();
        return null;
    }

    public String alterar(Campos i) throws Exception {
        campos = i;
        return null;
    }

    public List<SelectItem> selectListInstituto() throws Exception {
        List<Instituto> listaInstituto = new ArrayList<Instituto>();
        listaInstituto = new InstitutoBO().getAll();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();        
        for (int i = 0; i < listaInstituto.size(); i++) {
            listaCombo.add(new SelectItem(listaInstituto.get(i).getIns_id(), listaInstituto.get(i).getIns_nome()));
        }
        return listaCombo;
    }    
        
    /**
     * @return the campos
     */
    public Campos getCampos() {
        return campos;
    }

    /**
     * @param campos the campos to set
     */
    public void setCampos(Campos campos) {
        this.campos = campos;
    }

    /**
     * @return the lista
     */
    public List<Campos> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Campos> lista) {
        this.lista = lista;
    }

    /**
     * @return the pnCadastrar
     */
    public boolean isPnCadastrar() {
        return pnCadastrar;
    }

    /**
     * @param pnCadastrar the pnCadastrar to set
     */
    public void setPnCadastrar(boolean pnCadastrar) {
        this.pnCadastrar = pnCadastrar;
    }

    /**
     * @return the pnConsulta
     */
    public boolean isPnConsulta() {
        return pnConsulta;
    }

    /**
     * @param pnConsulta the pnConsulta to set
     */
    public void setPnConsulta(boolean pnConsulta) {
        this.pnConsulta = pnConsulta;
    }
    
}
