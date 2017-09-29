package br.com.tomcat.dao;

import br.com.tomcat.entity.Usuario;
import br.com.tomcat.enums.EnumPerfil;
import br.com.tomcat.enums.EnumSortOrder;
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
            usuario.setNome(rs.getString("nome"));
            usuario.setPerfil(EnumPerfil.getEnumPerfil(rs.getString("perfil")));
            return usuario;
        };
    }

    public int getRowCountListAll(final Map<String, Object> filters) throws Exception {
        try {
            final String where = filters.isEmpty() ? "" : getWhereLazyDataModel(filters);
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_usuario " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<Usuario> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters) throws Exception {
        try {
            final String sql = "select id, login, senha, nome, perfil from tb_usuario ";
            final String where = filters.isEmpty() ? "" : getWhereLazyDataModel(filters);
            final String order = " order by " + (StringUtil.isNull(sortField) ? "nome" : sortField) + (EnumSortOrder.getEnumOrder(sortOrder.name()).getDescricao());
            final String limit = " limit " + first + "," + pageSize;
            return jdbcTemplate.query(sql + where + order + limit, getRowMapper());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void insert(final Usuario usuario) throws Exception {
        try{
            final String sql = "insert into tb_usuario(login, senha, nome, perfil) values (?, ?, ?, ?)";
            jdbcTemplate.update(sql, usuario.getLogin(), usuario.getSenha(), usuario.getNome(), usuario.getPerfil().name());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void update(final Usuario usuario) throws Exception {
        try{
            final String sql = "update tb_usuario set login=?, senha=?, nome=?, perfil=? where id=?";
            jdbcTemplate.update(sql, usuario.getLogin(), usuario.getSenha(), usuario.getNome(), usuario.getPerfil().name(), usuario.getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void delete(final Usuario usuario) throws Exception {
        try{
            jdbcTemplate.update("delete from tb_usuario where id=?", usuario.getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public Usuario login(final Usuario usuario) throws Exception {
        try {
            return jdbcTemplate.queryForObject("select id, login, senha, nome, perfil from tb_usuario where login=? and senha=?", new Object[]{usuario.getLogin(), usuario.getSenha()}, getRowMapper());
        }catch(EmptyResultDataAccessException e) {
            throw new UserPasswordInvalidException();
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

}
