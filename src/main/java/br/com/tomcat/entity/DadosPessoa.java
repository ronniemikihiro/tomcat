package br.com.tomcat.entity;

import br.com.tomcat.util.StringUtil;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_dados_pessoa
 */
@Entity
@Table(name = "tb_dados_pessoa")
public class DadosPessoa implements br.com.tomcat.entity.Entity<Long> {

    public static final String TABLE_NAME = "tb_dados_pessoa";

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefone",length = 20)
    private String telefone;

    @Column(name = "celular1", nullable = false, length = 20)
    private String celular1;

    @Column(name = "celular2", length = 20)
    private String celular2;

    @Column(name = "logradouro", length = 45)
    private String logradouro;

    @Column(name = "complemento", length = 45)
    private String complemento;

    @Column(name = "cep", length = 20)
    private String cep;

    @Column(name = "bairro", length = 45)
    private String bairro;

    @Column(name = "cidade", length = 45)
    private String cidade;

    @Column(name = "estado", length = 45)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DadosPessoa that = (DadosPessoa) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
