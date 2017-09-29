package br.com.tomcat.entity;

import br.com.tomcat.util.StringUtil;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_cliente
 */
public class Cliente implements Entity<Long> {

    public static final String TABLE_NAME = "tb_cliente";

    private Long id;
    private String nome;
    private String cpf;
    private DadosPessoa dadosPessoa;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = StringUtil.onlyNumbers(cpf);
    }

    public DadosPessoa getDadosPessoa() {
        return dadosPessoa;
    }

    public void setDadosPessoa(DadosPessoa dadosPessoa) {
        this.dadosPessoa = dadosPessoa;
    }
}
