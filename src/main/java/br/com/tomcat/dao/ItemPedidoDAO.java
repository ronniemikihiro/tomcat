package br.com.tomcat.dao;

import br.com.tomcat.entity.ItemPedido;
import br.com.tomcat.entity.Usuario;
import br.com.tomcat.enums.EnumPerfil;
import br.com.tomcat.enums.EnumSortOrder;
import br.com.tomcat.util.ObjectUtil;
import br.com.tomcat.util.StringUtil;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Repository
public class ItemPedidoDAO extends AbstractDAO<ItemPedido, ItemPedido> {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<ItemPedido> getRowMapper() {
        return (rs, rowNum) -> {
            final ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(rs.getLong("id"));
            itemPedido.setNome(rs.getString("nome"));
            itemPedido.setPreco(rs.getDouble("preco"));
            return itemPedido;
        };
    }

    public int getRowCountListAll(final Map<String, Object> filters) throws Exception {
        try {
            final String where = filters.isEmpty() ? "" : getWhereLazyDataModel(filters);
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_item_pedido " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<ItemPedido> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters) throws Exception {
        try {
            final String sql = "select id, nome, preco from tb_item_pedido ";
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

    public void insert(final ItemPedido itemPedido) throws Exception {
        try{
            final String sql = "insert into tb_item_pedido(nome, preco) values (?, ?)";
            jdbcTemplate.update(sql, itemPedido.getNome(), itemPedido.getPreco());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void update(final ItemPedido itemPedido) throws Exception {
        try{
            final String sql = "update tb_item_pedido set nome=?, preco=? where id=?";
            jdbcTemplate.update(sql, itemPedido.getNome(), itemPedido.getPreco(), itemPedido.getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void delete(final ItemPedido itemPedido) throws Exception {
        try{
            jdbcTemplate.update("delete from tb_item_pedido where id=?", itemPedido.getId());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

}
