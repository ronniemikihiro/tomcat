package br.com.tomcat.controller;

import br.com.tomcat.entity.Usuario;
import br.com.tomcat.enums.EnumPerfil;
import br.com.tomcat.service.AbstractBO;
import br.com.tomcat.service.UsuarioBO;
import br.com.tomcat.util.EncryptionUtil;
import br.com.tomcat.vo.UsuarioVO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@ManagedBean
@ViewScoped
public class UsuarioMB extends AbstractMB<Usuario, Usuario> {

    private static final String PAGINA = "usuario.xhtml";

    @ManagedProperty(value = "#{usuarioBO}")
    private UsuarioBO usuarioBO;

    private UsuarioVO vo;

    public UsuarioVO getVo() {
        return vo;
    }

    @Override
    public AbstractBO<Usuario, Usuario> getBO() {
        return usuarioBO;
    }

    @Override
    public String getPage() {
        return PAGINA;
    }

    @PostConstruct
    private void init() {
        vo = new UsuarioVO();
        loadLazyDataModel();
        loadPerfis();
    }

    public void loadPerfis() {
        vo.setEnumPerfis(EnumPerfil.values());
    }

    @Override
    public void save() {
        getEntity().setSenha(EncryptionUtil.encrypt(getEntity().getSenha()));
        super.save();
    }

    public void setUsuarioBO(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }
}
