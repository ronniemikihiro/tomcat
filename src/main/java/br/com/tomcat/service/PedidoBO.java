package br.com.tomcat.service;

import br.com.tomcat.dao.AbstractDAO;
import br.com.tomcat.dao.ClienteDAO;
import br.com.tomcat.dao.PedidoDAO;
import br.com.tomcat.dto.ClienteDTO;
import br.com.tomcat.dto.PedidoDTO;
import br.com.tomcat.entity.Cliente;
import br.com.tomcat.entity.ItemPedidoCliente;
import br.com.tomcat.entity.Pedido;
import br.com.tomcat.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ronnie-msl on 29/09/17.
 */
@Service
public class PedidoBO extends AbstractBO<Pedido, PedidoDTO> {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Override
    public AbstractDAO<Pedido, PedidoDTO> getDAO() {
        return pedidoDAO;
    }

    public void save(final Pedido pedido) throws Exception {
//        final long idPedido = ObjectUtil.isNull(pedido.getId()) ? pedidoDAO.insert(pedido) : pedido.getId();
//        for(ItemPedidoCliente ipc : pedido.getListItemPedidoCliente()) {
//
//        }
    }
}
