package br.com.tomcat.entity;

import br.com.tomcat.util.StringUtil;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_dados_pessoa
 */
public class DadosPessoa implements Entity<Long> {

    public static final String TABLE_NAME = "tb_dados_pessoa";

    private Long id;
    private String telefone;
    private String celular1;
    private String celular2;
    private String logradouro;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = StringUtil.onlyNumbers(telefone);
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = StringUtil.onlyNumbers(celular1);
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = StringUtil.onlyNumbers(celular2);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = StringUtil.onlyNumbers(cep);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
