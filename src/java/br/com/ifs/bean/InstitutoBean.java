/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bean;

import br.com.ifs.bo.InstitutoBO;
import br.com.ifs.model.Instituto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


/**
 *
 * @author Vinicius
 */
@Named(value = "institutoMB")
@RequestScoped

public class InstitutoBean {

    private boolean pnCadastrar = true, pnConsulta = false;
    private Instituto instituto = new Instituto();
    private List<Instituto> lista;

    @PostConstruct
    public void pageLoad() {
        try {
            //lista = InstitutoBO.getInstance().getAll();
        } catch (Exception ex) {
            Logger.getLogger(InstitutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Instituto> getAllInstituto() throws Exception {
        return InstitutoBO.getInstance().getAll();         
    }

    public String cadastrarAction() {
        try {
            if (instituto.getIns_id() == 0) {
                InstitutoBO.getInstance().inserir(instituto);
            } else {
                InstitutoBO.getInstance().alterar(instituto);
            }
            lista = getAllInstituto();
            instituto = new Instituto();
            pnCadastrar = false;
            pnConsulta = true;
        } catch (Exception ex) {
            Logger.getLogger(InstitutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String novoInstituto() {
        pnCadastrar = true;
        pnConsulta = false;
        return null;
    }

    public String todosAction() {
        pnCadastrar = false;
        pnConsulta = true;
        return null;
    }

    public String deletar(Instituto i) throws Exception {
        InstitutoBO.getInstance().excluir(i);
        lista = getAllInstituto();
        return null;
    }

    public String alterar(Instituto i) throws Exception {
        instituto = i;
        return null;
    }

    /**
     * @return the instituto
     */
    public Instituto getInstituto() {
        return instituto;
    }

    /**
     * @param instituto the instituto to set
     */
    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    /**
     * @return the lista
     */
    public List<Instituto> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Instituto> lista) {
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
