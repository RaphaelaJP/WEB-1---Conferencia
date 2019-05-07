/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bean;

import br.com.ifs.bo.UserTypeBO;
import br.com.ifs.model.UserType;
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
@Named(value = "userTypeMB")
@RequestScoped

public class UserTypeBean {

    private boolean pnCadastrar = true, pnConsulta = false;
    private UserType userType = new UserType();
    private List<UserType> lista;

    @PostConstruct
    public void pageLoad() {
        try {
            lista = UserTypeBO.getInstance().getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserTypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public List<SelectItem> selectList() throws Exception{
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for(int i = 0; i<lista.size(); i++){
            listaCombo.add(new SelectItem(lista.get(i).getUsr_typ_id(), lista.get(i).getUsr_typ_nome()));         
        }
        return listaCombo;
    }

    /**
     * @return the userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * @return the lista
     */
    public List<UserType> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<UserType> lista) {
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
