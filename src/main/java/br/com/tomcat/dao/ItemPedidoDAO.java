package br.com.tomcat.dao;

import br.com.tomcat.entity.ItemPedido;
import br.com.tomcat.enums.EnumSortOrder;
import br.com.tomcat.util.StringUtil;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    @Override
    public int getRowCountListAll(final String filterGlobal) throws Exception {
        try {
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "id", "nome");
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_item_pedido " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    @Override
    public List<ItemPedido> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final String filterGlobal) throws Exception {
        try {
            final String sql = "select id, nome, preco from tb_item_pedido ";
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

    public List<ItemPedido> listAll() throws Exception {
        try{
            final String sql = "select id, nome, preco from tb_item_pedido order by nome";
            return jdbcTemplate.query(sql, getRowMapper());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

}
