package br.com.tomcat.dao;

import br.com.tomcat.dto.ClienteDTO;
import br.com.tomcat.entity.Cliente;
import br.com.tomcat.enums.EnumSortOrder;
import br.com.tomcat.util.StringUtil;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Repository
public class ClienteDAO extends AbstractDAO<Cliente, ClienteDTO> {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private void setDataSource(final DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
    }

    private RowMapper<ClienteDTO> getRowMapperClienteDTO() {
        return (rs, rowNum) -> {
            final ClienteDTO cliente = new ClienteDTO();
            cliente.setId(rs.getLong("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setIdDadosPessoa(rs.getLong("id_dados_pessoa"));
            cliente.setCelular1(rs.getString("celular1"));
            return cliente;
        };
    }

    public int getRowCountListAll(final String filterGlobal) throws Exception {
        try {
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "c.id", "c.nome");
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_cliente c inner join tb_dados_pessoa dp on dp.id = c.id_dados_pessoa " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<ClienteDTO> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final String filterGlobal) throws Exception {
        try {
            final String sql = " select c.id as id_cliente, c.nome as nome, dp.id as id_dados_pessoa, dp.celular1 as celular1 from tb_cliente c " +
                               " inner join tb_dados_pessoa dp on dp.id = c.id_dados_pessoa ";
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "c.id", "c.nome");
            final String order = " order by " + (StringUtil.isNull(sortField) ? "nome" : sortField) + (EnumSortOrder.getEnumOrder(sortOrder.name()).getDescricao());
            final String limit = " limit " + first + "," + pageSize;
            return jdbcTemplate.query(sql + where + order + limit, getRowMapperClienteDTO());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<Cliente> listAll() throws Exception {
        try{
            final String sql = " select c.id as id_cliente, c.nome as nome from tb_cliente c " +
                               " inner join tb_dados_pessoa dp on dp.id = c.id_dados_pessoa " +
                               " order by c.nome";
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                final Cliente c = new Cliente();
                c.setId(rs.getLong("id_cliente"));
                c.setNome(rs.getString("nome"));
                return c;
            });
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

}
