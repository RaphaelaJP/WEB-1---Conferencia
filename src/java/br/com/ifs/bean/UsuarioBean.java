/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifs.bean;

import br.com.ifs.bo.CamposBO;
import br.com.ifs.bo.InstitutoBO;
import br.com.ifs.model.Usuario;
import br.com.ifs.bo.UsuarioBO;
import br.com.ifs.model.Campos;
import br.com.ifs.model.Instituto;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import util.SessionUtil;

/**
 *
 * @author Vinicius
 */
@Named(value = "usuarioMB")
@SessionScoped

public class UsuarioBean implements Serializable {

    private boolean pnCadastrar = true, pnConsulta = false;

    private List<Usuario> lista;
    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void pageLoad() {
        try {
            lista = getAllUsuarios();
            //selectListCampos();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> getAllUsuarios() throws Exception {
        return UsuarioBO.getInstance().getAll();
    }

    public String cadastrarAction() {
        try {

            if (usuario.getUsr_id() == 0) {
                Usuario usuarioTempo;
                boolean cadastrar = false;
                if (usuario.getUserType().getUsr_typ_id() == 3) {
                    usuarioTempo = UsuarioBO.getInstance().getDiretorCampo(usuario.getCampo().getCam_id());
                    cadastrar = usuarioTempo == null ? true : false;
                    if (!cadastrar) {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Já existe um diretor nesse campos.", "erro diretor"));
                    }
                } else if (usuario.getUserType().getUsr_typ_id() == 2) {
                    usuarioTempo = UsuarioBO.getInstance().getReitorInstituto(usuario.getCampo().getInstituicao().getIns_id());
                    cadastrar = usuarioTempo == null ? true : false;
                    if (!cadastrar) {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Já existe um reitor nessa instituição.", "erro instituicao"));
                    }
                }
                if (cadastrar) {
                    UsuarioBO.getInstance().inserir(usuario);
                }
            } else {
                UsuarioBO.getInstance().alterar(usuario);
            }
            lista = getAllUsuarios();
            usuario = new Usuario();
            pnCadastrar = false;
            pnConsulta = true;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String novoUsuario() {
        pnCadastrar = true;
        pnConsulta = false;
        usuario = new Usuario();
        return null;
    }

    public String todosAction() {
        pnCadastrar = false;
        pnConsulta = true;
        return null;
    }

    public String deletar(Usuario i) throws Exception {
        UsuarioBO.getInstance().excluir(i);
        lista = getAllUsuarios();
        return null;
    }

    public String alterar(Usuario i) throws Exception {
        usuario = i;
        return null;
    }

    public List<SelectItem> selectListInstituto() throws Exception {
        List<Instituto> listaInstituto = new ArrayList<Instituto>();
        listaInstituto = new InstitutoBO().getAll();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        Object o = null;
        listaCombo.add(new SelectItem(o, "Selecione Instituto"));
        for (int i = 0; i < listaInstituto.size(); i++) {
            listaCombo.add(new SelectItem(listaInstituto.get(i).getIns_id(), listaInstituto.get(i).getIns_nome()));
        }
        return listaCombo;
    }

    public List<SelectItem> selectListCampos() throws Exception {
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        Object o = null;
        listaCombo.add(new SelectItem(o, "Selecione Campos"));
        if (usuario.getCampo().getInstituicao().getIns_id() > 0) {
            List<Campos> listaCampo = new ArrayList<Campos>();
            listaCampo = new CamposBO().getByInstituto(usuario.getCampo().getInstituicao().getIns_id());
            for (int i = 0; i < listaCampo.size(); i++) {
                listaCombo.add(new SelectItem(listaCampo.get(i).getCam_id(), listaCampo.get(i).getCam_nome()));
            }
        }
        return listaCombo;
    }

    public String login() throws Exception {
        Usuario usuarioTemp = UsuarioBO.getInstance().login(usuario);
        if (usuarioTemp != null) {
            //Object b = new Object();
            //SessionUtil.setParam("USUARIOLogado", b);
            SessionUtil.setParam("USUARIOLogado", usuarioTemp);
            if (usuarioTemp.getUserType().getUsr_typ_id() == 1) {
                return "administrador.xhtml?faces-redirect=true";
            } else if (usuarioTemp.getUserType().getUsr_typ_id() == 4 || usuarioTemp.getUserType().getUsr_typ_id() == 3 || usuarioTemp.getUserType().getUsr_typ_id() == 2) {
                return "usuariosMenu.xhtml?faces-redirect=true";
            } else {
                return "logado.xhtml?faces-redirect=true";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Usuario ou senha incorretos.", "Erro login"));
            return null;
        }
    }

    public String registraSaida() {
        //REMOVER USUARIO DA SESSION
        return "/login?faces-redirect=true";
    }

    /**
     * @return the lista
     */
    public List<Usuario> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Usuario> lista) {
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
