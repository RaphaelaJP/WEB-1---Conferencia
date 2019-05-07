/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bean;

import br.com.ifs.bo.ConferenciaBO;
import br.com.ifs.model.Conferencia;
import br.com.ifs.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import util.SessionUtil;

/**
 *
 * @author Vinicius
 */
@Named(value = "conferenciaMB")
@RequestScoped

public class ConferenciaBean {

    private boolean pnCadastrar = true, pnConsulta = false;
    private Conferencia conferencia = new Conferencia();
    private List<Conferencia> lista;

    private final Usuario usr = (Usuario)SessionUtil.getParam("USUARIOLogado");
    
    @PostConstruct
    public void pageLoad() {
        try {
            //lista = ConferenciaBO.getInstance().getAll();                                                     
        } catch (Exception ex) {
            Logger.getLogger(ConferenciaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Conferencia> getListaConferenciaPorUsuario() throws Exception{
        return ConferenciaBO.getInstance().getByUsuario(usr.getUsr_id());
    }
    
    public String cadastrarAction() {
        try {
            if (conferencia.getCon_id() == 0) {
                ConferenciaBO.getInstance().inserir(conferencia);                
            }else{
                ConferenciaBO.getInstance().alterar(conferencia);
            }
            lista = getListaConferenciaPorUsuario();
            conferencia = new Conferencia();
            pnCadastrar = false;
            pnConsulta = true;
        } catch (Exception ex) {
            Logger.getLogger(ConferenciaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String novoConferencia() {
        pnCadastrar = true;
        pnConsulta = false;
        return null;
    }

    public String todosAction() {
        pnCadastrar = false;
        pnConsulta = true;
        return null;
    }

    public String deletar(Conferencia i) throws Exception {
        ConferenciaBO.getInstance().excluir(i);
        conferencia = new Conferencia();
        lista = getListaConferenciaPorUsuario();
        return null;
    }

    public String alterar(Conferencia i) throws Exception {
        conferencia = i;
        return null;
    }

       
    /**
     * @return the conferencia
     */
    public Conferencia getConferencia() {
        return conferencia;
    }

    /**
     * @param conferencia the conferencia to set
     */
    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    /**
     * @return the lista
     */
    public List<Conferencia> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Conferencia> lista) {
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
