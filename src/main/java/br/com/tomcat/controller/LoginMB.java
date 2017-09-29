package br.com.tomcat.controller;

import br.com.tomcat.entity.Usuario;
import br.com.tomcat.exception.NegocioException;
import br.com.tomcat.service.UsuarioBO;
import br.com.tomcat.util.EncryptionUtil;
import br.com.tomcat.util.JSFUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by ronnie-msl on 28/09/17.
 */
@ManagedBean
@SessionScoped
public class LoginMB {

    @ManagedProperty(value = "#{usuarioBO}")
    private UsuarioBO usuarioBO;

    private Usuario usuario;

    @PostConstruct
    private void init() {
        usuario = new Usuario();
    }

    public void login() {
        try {
            usuario.setSenha(EncryptionUtil.encrypt(usuario.getSenha()));
            usuario = usuarioBO.login(usuario);
            JSFUtil.goPage("index.xhtml");
        }catch(NegocioException e) {
            JSFUtil.addWarn(e);
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    public void logout() {
        usuario = new Usuario();
        JSFUtil.goPage("login.xhtml");
    }

    public void setUsuarioBO(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
