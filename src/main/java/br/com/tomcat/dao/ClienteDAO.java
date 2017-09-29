package br.com.tomcat.dao;

import br.com.tomcat.dto.ClienteDTO;
import br.com.tomcat.entity.Cliente;
import br.com.tomcat.entity.DadosPessoa;
import br.com.tomcat.enums.EnumSortOrder;
import br.com.tomcat.util.StringUtil;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    private RowMapper<Cliente> getRowMapperCliente() {
        return (rs, rowNum) -> {
            final Cliente c = new Cliente();
            c.setId(rs.getLong("id_cliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            final DadosPessoa dp = new DadosPessoa();
            dp.setId(rs.getLong("id_dados_pessoa"));
            dp.setTelefone(rs.getString("telefone"));
            dp.setCelular1(rs.getString("celular1"));
            dp.setCelular2(rs.getString("celular2"));
            dp.setLogradouro(rs.getString("logradouro"));
            dp.setComplemento(rs.getString("complemento"));
            dp.setCep(rs.getString("cep"));
            dp.setBairro(rs.getString("bairro"));
            dp.setCidade(rs.getString("cidade"));
            dp.setEstado(rs.getString("estado"));
            c.setDadosPessoa(dp);
            return c;
        };
    }

    public int getRowCountListAll(final Map<String, Object> filters) throws Exception {
        try {
            final String where = filters.isEmpty() ? "" : getWhereLazyDataModel(filters);
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_cliente c inner join tb_dados_pessoa dp on dp.id = c.id_dados_pessoa " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<ClienteDTO> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters) throws Exception {
        try {
            final String sql = " select c.id as id_cliente, c.nome as nome, dp.id as id_dados_pessoa, dp.celular1 as celular1 from tb_cliente c " +
                               " inner join tb_dados_pessoa dp on dp.id = c.id_dados_pessoa ";
            final String where = filters.isEmpty() ? "" : getWhereLazyDataModel(filters);
            final String order = " order by " + (StringUtil.isNull(sortField) ? "c.nome" : sortField) + (EnumSortOrder.getEnumOrder(sortOrder.name()).getDescricao());
            final String limit = " limit " + first + "," + pageSize;
            return jdbcTemplate.query(sql + where + order + limit, getRowMapperClienteDTO());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public Cliente loadEntity(final ClienteDTO clienteDTO) throws Exception {
        try{
            final String sql = " select c.id as id_cliente, nome, cpf, id_dados_pessoa, telefone, celular1, celular2, logradouro, complemento, cep, bairro, cidade, estado from tb_cliente c " +
                               " inner join tb_dados_pessoa dp on dp.id = c.id_dados_pessoa " +
                               " where c.id=?";
            return jdbcTemplate.queryForObject(sql, getRowMapperCliente(), clienteDTO.getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void insert(final Cliente cliente) throws Exception {
        try{
            cliente.getDadosPessoa().setId(simpleJdbcInsert.withTableName(DadosPessoa.TABLE_NAME).executeAndReturnKey(new BeanPropertySqlParameterSource(cliente.getDadosPessoa())).longValue());
            final String sql = "insert into tb_cliente(nome, cpf, id_dados_pessoa) values (?, ?, ?)";
            jdbcTemplate.update(sql, cliente.getNome(), cliente.getCpf(), cliente.getDadosPessoa().getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void update(final Cliente cliente) throws Exception {
        try{
            final DadosPessoa dp = cliente.getDadosPessoa();
            final String sqlDadosPessoa = "update tb_dados_pessoa set telefone=?, celular1=?, celular2=?, logradouro=?, complemento=?, cep=?, bairro=?, cidade=?, estado=? where id=?";
            jdbcTemplate.update(sqlDadosPessoa, dp.getTelefone(), dp.getCelular1(), dp.getCelular2(), dp.getLogradouro(), dp.getComplemento(), dp.getCep(), dp.getBairro(), dp.getCidade(), dp.getEstado(), dp.getId());
            final String sqlCliente = "update tb_cliente set nome=?, cpf=? where id=?";
            jdbcTemplate.update(sqlCliente, cliente.getNome(), cliente.getCpf(), cliente.getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void delete(final Cliente cliente) throws Exception {
        try{
            jdbcTemplate.update("delete from tb_cliente where id=?", cliente.getId());
            jdbcTemplate.update("delete from tb_dados_pessoa where id=?", cliente.getDadosPessoa().getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

}
