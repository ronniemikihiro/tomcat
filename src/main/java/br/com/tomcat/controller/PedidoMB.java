package br.com.tomcat.controller;

import br.com.tomcat.dto.PedidoDTO;
import br.com.tomcat.entity.*;
import br.com.tomcat.enums.EnumTipoAtendimento;
import br.com.tomcat.service.AbstractBO;
import br.com.tomcat.service.ClienteBO;
import br.com.tomcat.service.ItemPedidoBO;
import br.com.tomcat.service.PedidoBO;
import br.com.tomcat.util.JSFUtil;
import br.com.tomcat.util.ListUtil;
import br.com.tomcat.vo.PedidoVO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by ronnie-msl on 29/09/17.
 */
@ManagedBean
@ViewScoped
public class PedidoMB extends AbstractMB<Pedido, PedidoDTO> {

    private static final String PAGINA = "pedido.xhtml";

    @ManagedProperty(value = "#{pedidoBO}")
    private PedidoBO pedidoBO;

    @ManagedProperty(value = "#{clienteBO}")
    private ClienteBO clienteBO;

    @ManagedProperty(value = "#{itemPedidoBO}")
    private ItemPedidoBO itemPedidoBO;

    private PedidoVO vo;

    public PedidoVO getVo() {
        return vo;
    }

    @Override
    public AbstractBO<Pedido, PedidoDTO> getBO() {
        return pedidoBO;
    }

    @Override
    public String getPage() {
        return PAGINA;
    }

    @PostConstruct
    private void init() {
        vo = new PedidoVO();
        loadLazyDataModel();
        loadClientes();
        loadItensPedido();
    }

    @Override
    public void newEntity() {
        super.newEntity();
        getEntity().setAtendente((Usuario) JSFUtil.getPropSession("usuario"));
        getEntity().setListItemPedidoCliente(new ArrayList<>());
        newItemPedidoCliente();
    }

    @Override
    public void loadEntity() {
        super.loadEntity();
        newItemPedidoCliente();
    }

    private void loadClientes() {
        try{
            vo.setListClientes(clienteBO.listAll());
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    private void loadItensPedido() {
        try {
            vo.setListItensPedido(itemPedidoBO.listAll());
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    private void newItemPedidoCliente() {
        vo.setItemPedidoCliente(new ItemPedidoCliente());
        vo.getItemPedidoCliente().setItemPedido(new ItemPedido());
        vo.getItemPedidoCliente().setEnumTipoAtendimento(EnumTipoAtendimento.P);
    }

    public void addItemPedidoCliente() {
        vo.getItemPedidoCliente().setDataHora(LocalDateTime.now());
        vo.getItemPedidoCliente().setPedido(getEntity());
        getEntity().getListItemPedidoCliente().add(vo.getItemPedidoCliente());
        newItemPedidoCliente();
    }

    public Double getTotal() {
        return ListUtil.isNullEmpty(getEntity().getListItemPedidoCliente()) ? 0 : getEntity().getListItemPedidoCliente().stream().mapToDouble(ItemPedidoCliente::getTotalPreco).sum();
    }

    public void setPedidoBO(PedidoBO pedidoBO) {
        this.pedidoBO = pedidoBO;
    }

    public void setClienteBO(ClienteBO clienteBO) {
        this.clienteBO = clienteBO;
    }

    public void setItemPedidoBO(ItemPedidoBO itemPedidoBO) {
        this.itemPedidoBO = itemPedidoBO;
    }
}
