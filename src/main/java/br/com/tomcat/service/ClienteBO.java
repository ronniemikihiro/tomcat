package br.com.tomcat.service;

import br.com.tomcat.dao.AbstractDAO;
import br.com.tomcat.dao.ClienteDAO;
import br.com.tomcat.dao.ItemPedidoDAO;
import br.com.tomcat.dto.ClienteDTO;
import br.com.tomcat.entity.Cliente;
import br.com.tomcat.entity.Entity;
import br.com.tomcat.entity.ItemPedido;
import br.com.tomcat.util.JSFUtil;
import br.com.tomcat.util.ObjectUtil;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Service
public class ClienteBO extends AbstractBO<Cliente, ClienteDTO> {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public AbstractDAO<Cliente, ClienteDTO> getDAO() {
        return clienteDAO;
    }

    public List<Cliente> listAll() throws Exception {
        return clienteDAO.listAll();
    }
}
