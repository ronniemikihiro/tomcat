package br.com.tomcat.entity;

import br.com.tomcat.util.StringUtil;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_cliente
 */
@Entity
@Table(name = "tb_cliente")
public class Cliente implements br.com.tomcat.entity.Entity<Long> {

    public static final String TABLE_NAME = "tb_cliente";

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "cpf", length = 20)
    private String cpf;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dados_pessoa", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return id != null ? id.equals(cliente.id) : cliente.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
