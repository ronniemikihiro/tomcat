package br.com.tomcat.dto;

import br.com.tomcat.entity.Entity;
import br.com.tomcat.util.StringUtil;

/**
 * Created by ronnie-msl on 13/09/17.
 */
public class ClienteDTO implements Entity<Long> {

    private Long id;
    private String nome;
    private long idDadosPessoa;
    private String celular1;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdDadosPessoa() {
        return idDadosPessoa;
    }

    public void setIdDadosPessoa(long idDadosPessoa) {
        this.idDadosPessoa = idDadosPessoa;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular1Format() {
        return StringUtil.formatTel(celular1);
    }

}
