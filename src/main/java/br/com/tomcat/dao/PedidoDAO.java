package br.com.tomcat.dao;

import br.com.tomcat.dto.PedidoDTO;
import br.com.tomcat.entity.Pedido;
import br.com.tomcat.enums.EnumSortOrder;
import br.com.tomcat.util.DateUtil;
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
 * Created by ronnie-msl on 29/09/17.
 */
@Repository
public class PedidoDAO extends AbstractDAO<Pedido, PedidoDTO> {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<PedidoDTO> getRowMapperPedidoDTO() {
        return (rs, rowNum) -> {
            final PedidoDTO p = new PedidoDTO();
            p.setId(rs.getLong("id_pedido"));
            p.setNomeCliente(rs.getString("nome_cliente"));
            p.setNomeAtendente(rs.getString("nome_atendente"));
            p.setData(rs.getString("data"));
            return p;
        };
    }

    public int getRowCountLazyDataModel(final String filterGlobal) throws Exception {
        try {
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "p.id", "c.nome");
            return jdbcTemplate.queryForObject("select count(1) as rowCount from tb_pedido p " + where, Integer.class);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public List<PedidoDTO> listLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final String filterGlobal) throws Exception {
        try {
            final String sql = " select distinct p.id as id_pedido, c.nome as nome_cliente, u.nome as nome_atendente, date_format(ipc.dataHora, '%d/%m/%Y') as data from tb_pedido p " +
                               " inner join tb_cliente c on c.id = p.id_cliente " +
                               " inner join tb_usuario u on u.id = p.id_atendente " +
                               " inner join tb_item_pedido_cliente ipc on ipc.id_pedido = p.id ";
            final String where = StringUtil.isNullEmpty(filterGlobal) ? "" : getWhereGlobalFilter(filterGlobal, "p.id", "c.nome");
            final String order = " order by " + (StringUtil.isNull(sortField) ? "nome_cliente" : sortField) + (EnumSortOrder.getEnumOrder(sortOrder.name()).getDescricao());
            final String limit = " limit " + first + "," + pageSize;
            return jdbcTemplate.query(sql + where + order + limit, getRowMapperPedidoDTO());
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }
}
