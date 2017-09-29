package br.com.tomcat.service;

import br.com.tomcat.dao.AbstractDAO;
import br.com.tomcat.dao.UsuarioDAO;
import br.com.tomcat.entity.Usuario;
import br.com.tomcat.exception.UserPasswordInvalidException;
import br.com.tomcat.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Service
public class UsuarioBO extends AbstractBO<Usuario, Usuario> {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public AbstractDAO<Usuario, Usuario> getDAO() {
        return usuarioDAO;
    }

    public Usuario login(final Usuario usuario) throws Exception {
        return usuarioDAO.login(usuario);
    }
}
