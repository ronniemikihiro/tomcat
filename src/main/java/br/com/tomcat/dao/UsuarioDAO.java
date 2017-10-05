package br.com.tomcat.dao;

import br.com.tomcat.entity.Usuario;
import br.com.tomcat.enums.EnumAtivo;
import br.com.tomcat.enums.EnumPerfil;
import br.com.tomcat.enums.EnumSortOrder;
import br.com.tomcat.exception.UserEmailInvalidException;
import br.com.tomcat.exception.UserPasswordInvalidException;
import br.com.tomcat.util.StringUtil;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Repository
public class UsuarioDAO extends AbstractDAO<Usuario, Usuario>  {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Usuario> getRowMapper() {
        return (rs, rowNum) -> {
            final Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setEmail(rs.getString("email"));
            usuario.setNome(rs.getString("nome"));
            usuario.setPerfil(EnumPerfil.getEnumPerfil(rs.getString("perfil")));
            usuario.setSenhaAtiva(EnumAtivo.getEnumAtivo(rs.getString("senha_ativa")));
            return usuario;
        };
    }

    public int getRowCountListAll(final String filterGlobal) throws Exception {
        try {
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "id", "nome");
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_usuario " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<Usuario> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final String filterGlobal) throws Exception {
        try {
            final String sql = "select id, login, senha, email, nome, perfil, senha_ativa from tb_usuario ";
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "id", "nome");
            final String order = " order by " + (StringUtil.isNull(sortField) ? "nome" : sortField) + (EnumSortOrder.getEnumOrder(sortOrder.name()).getDescricao());
            final String limit = " limit " + first + "," + pageSize;
            return jdbcTemplate.query(sql + where + order + limit, getRowMapper());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public Usuario login(final Usuario usuario) throws Exception {
        try {
            return jdbcTemplate.queryForObject("select id, login, senha, email, nome, perfil, senha_ativa from tb_usuario where login=? and senha=?",
                    new Object[]{usuario.getLogin(), usuario.getSenha()}, getRowMapper());
        }catch(EmptyResultDataAccessException e) {
            throw new UserPasswordInvalidException();
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public Usuario getToEmail(final String email) throws Exception {
        try {
            return jdbcTemplate.queryForObject("select id, login, senha, email, nome, perfil, senha_ativa from tb_usuario where email=?",
                    new Object[]{email}, getRowMapper());
        }catch(EmptyResultDataAccessException e) {
            throw new UserEmailInvalidException();
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

}
