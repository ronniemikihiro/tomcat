package br.com.tomcat.vo;

import br.com.tomcat.entity.Cliente;
import br.com.tomcat.entity.ItemPedido;
import br.com.tomcat.entity.ItemPedidoCliente;

import java.util.List;

/**
 * Created by ronnie-msl on 19/09/17.
 */
public class PedidoVO {

    private List<Cliente> listClientes;
    private List<ItemPedido> listItensPedido;
    private ItemPedidoCliente itemPedidoCliente;

    public List<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public List<ItemPedido> getListItensPedido() {
        return listItensPedido;
    }

    public void setListItensPedido(List<ItemPedido> listItensPedido) {
        this.listItensPedido = listItensPedido;
    }

    public ItemPedidoCliente getItemPedidoCliente() {
        return itemPedidoCliente;
    }

    public void setItemPedidoCliente(ItemPedidoCliente itemPedidoCliente) {
        this.itemPedidoCliente = itemPedidoCliente;
    }
}
