package br.com.tomcat.controller;

import br.com.tomcat.dto.ClienteDTO;
import br.com.tomcat.entity.Cliente;
import br.com.tomcat.entity.DadosPessoa;
import br.com.tomcat.service.AbstractBO;
import br.com.tomcat.service.ClienteBO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@ManagedBean
@ViewScoped
public class ClienteMB extends AbstractMB<Cliente, ClienteDTO> {

    private static final String PAGINA = "cliente.xhtml";

    @ManagedProperty(value = "#{clienteBO}")
    private ClienteBO clienteBO;

    @Override
    public AbstractBO<Cliente, ClienteDTO> getBO() {
        return clienteBO;
    }

    @Override
    public String getPage() {
        return PAGINA;
    }

    @PostConstruct
    private void init() {
        loadLazyDataModel();
    }

    @Override
    public void newEntity() {
        super.newEntity();
        getEntity().setDadosPessoa(new DadosPessoa());
    }

    public void setClienteBO(ClienteBO clienteBO) {
        this.clienteBO = clienteBO;
    }
}
