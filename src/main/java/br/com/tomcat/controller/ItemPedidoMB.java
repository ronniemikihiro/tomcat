package br.com.tomcat.controller;

import br.com.tomcat.entity.ItemPedido;
import br.com.tomcat.service.AbstractBO;
import br.com.tomcat.service.ItemPedidoBO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@ManagedBean
@ViewScoped
public class ItemPedidoMB extends AbstractMB<ItemPedido, ItemPedido> {

    private static final String PAGINA = "itemPedido.xhtml";

    @ManagedProperty(value = "#{itemPedidoBO}")
    private ItemPedidoBO itemPedidoBO;

    @Override
    public AbstractBO<ItemPedido, ItemPedido> getBO() {
        return itemPedidoBO;
    }

    @Override
    public String getPage() {
        return PAGINA;
    }

    @PostConstruct
    private void init() {
        loadLazyDataModel();
    }

    public void setItemPedidoBO(ItemPedidoBO itemPedidoBO) {
        this.itemPedidoBO = itemPedidoBO;
    }
}
