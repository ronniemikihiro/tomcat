package br.com.tomcat.dto;

import br.com.tomcat.entity.Entity;
import br.com.tomcat.util.StringUtil;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by ronnie-msl on 29/09/17.
 */
public class PedidoDTO implements Entity<Long> {

    private Long id;
    private String nomeCliente;
    private String nomeAtendente;
    private String data;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
