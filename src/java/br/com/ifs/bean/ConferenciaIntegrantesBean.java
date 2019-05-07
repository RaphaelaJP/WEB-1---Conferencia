/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bean;

import br.com.ifs.bo.ConferenciaBO;
import br.com.ifs.bo.ConferenciaIntegrantesBO;
import br.com.ifs.bo.UsuarioBO;
import br.com.ifs.model.Conferencia;
import br.com.ifs.model.ConferenciaIntegrantes;
import br.com.ifs.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import util.SessionUtil;
import util.SendEmail;

/**
 *
 * @author Vinicius
 */
@Named(value = "conferenciaIntegrantesMB")
@RequestScoped

public class ConferenciaIntegrantesBean  {

    private boolean pnCadastrar = true, pnConsulta = false;
    private ConferenciaIntegrantes conferenciaIntegrantes = new ConferenciaIntegrantes();
    private List<ConferenciaIntegrantes> lista;

    private final Usuario usr = (Usuario) SessionUtil.getParam("USUARIOLogado");

    @PostConstruct
    public void pageLoad() {
        try {

        } catch (Exception ex) {
            Logger.getLogger(ConferenciaIntegrantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ConferenciaIntegrantes> getAllConferenciaIntegrantes() throws Exception {
        return ConferenciaIntegrantesBO.getInstance().getPorConferencia(conferenciaIntegrantes.getConferencia().getCon_id());
    }

    public String cadastrarAction() {
        try {
            int conferencia = conferenciaIntegrantes.getConferencia().getCon_id();
            int integrante = conferenciaIntegrantes.getUsuario().getUsr_id();
            if (ConferenciaIntegrantesBO.getInstance().getByIntegrante(conferencia, integrante) == null) {
                ConferenciaIntegrantesBO.getInstance().inserir(conferenciaIntegrantes);
                Conferencia c = new Conferencia();
                Usuario u = new Usuario();
                c = ConferenciaBO.getInstance().getById(conferencia);
                u = UsuarioBO.getInstance().getById(integrante);
                String titulo = "Reunião sobre: " + c.getCon_nome();
                String corpo = "Link para reunão: https://appear.in/" + c.getCon_id_sala();
                String email = u.getUsr_email();
                
                SendEmail e = new SendEmail();
                                
                if (e.sendEmail(titulo, email, corpo)) {
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Convite enviado com sucesso.", "email sucesso"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Ocorreu algum erro ao enviar e-mail.", "email erro"));
                }
                 
                lista = getAllConferenciaIntegrantes();
                pnCadastrar = false;
                pnConsulta = true;
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Esse usuario já foi convidado.", "Erro duplicidade usuario"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ConferenciaIntegrantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SelectItem> selectListConferenciasPorUsuario() throws Exception {
        List<Conferencia> listaConferencia = new ArrayList<Conferencia>();
        listaConferencia = new ConferenciaBO().getByUsuario(usr.getUsr_id());
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        Object o = null;
        listaCombo.add(new SelectItem(o, "Selecione uma conferência"));
        for (int i = 0; i < listaConferencia.size(); i++) {
            listaCombo.add(new SelectItem(listaConferencia.get(i).getCon_id(), listaConferencia.get(i).getCon_nome()));
        }
        return listaCombo;
    }

    public List<SelectItem> selectListIntegrantes() throws Exception {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        if (usr.getUserType().getUsr_typ_id() == 4) {
            listaUsuario = new UsuarioBO().getGerenteAndDiretor(usr.getCampo().getCam_id());
        } else if (usr.getUserType().getUsr_typ_id() == 3) {
            listaUsuario = new UsuarioBO().getDiretorAndReitor(usr.getCampo().getInstituicao().getIns_id());
        } else if (usr.getUserType().getUsr_typ_id() == 2) {
            listaUsuario = new UsuarioBO().getReitores();
        }
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        Object o = null;
        listaCombo.add(new SelectItem(o, "Selecione um integrante"));
        for (int i = 0; i < listaUsuario.size(); i++) {
            listaCombo.add(new SelectItem(listaUsuario.get(i).getUsr_id(), listaUsuario.get(i).getUsr_nome() + " - " + listaUsuario.get(i).getUserType().getUsr_typ_nome() + "(" + listaUsuario.get(i).getCampo().getCam_nome() + "/" + listaUsuario.get(i).getCampo().getInstituicao().getIns_nome() + ")"));
        }
        return listaCombo;
    }

    public String novoConferenciaIntegrantes() {
        pnCadastrar = true;
        pnConsulta = false;
        return null;
    }

    public String todosAction() {
        pnCadastrar = false;
        pnConsulta = true;
        return null;
    }

    public String deletar(ConferenciaIntegrantes i) throws Exception {
        ConferenciaIntegrantesBO.getInstance().excluir(i);
        lista = getAllConferenciaIntegrantes();
        return null;
    }

    /**
     * @return the conferenciaIntegrantes
     */
    public ConferenciaIntegrantes getConferenciaIntegrantes() {
        return conferenciaIntegrantes;
    }

    /**
     * @param conferenciaIntegrantes the conferenciaIntegrantes to set
     */
    public void setConferenciaIntegrantes(ConferenciaIntegrantes conferenciaIntegrantes) {
        this.conferenciaIntegrantes = conferenciaIntegrantes;
    }

    /**
     * @return the lista
     */
    public List<ConferenciaIntegrantes> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<ConferenciaIntegrantes> lista) {
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
