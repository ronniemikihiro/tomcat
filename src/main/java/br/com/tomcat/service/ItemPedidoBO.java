package br.com.tomcat.service;

import br.com.tomcat.dao.AbstractDAO;
import br.com.tomcat.dao.ItemPedidoDAO;
import br.com.tomcat.entity.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Service
public class ItemPedidoBO extends AbstractBO<ItemPedido, ItemPedido> {

    @Autowired
    private ItemPedidoDAO itemPedidoDAO;

    @Override
    public AbstractDAO<ItemPedido, ItemPedido> getDAO() {
        return itemPedidoDAO;
    }
}
