package br.com.tomcat.entity;

import br.com.tomcat.enums.EnumPerfil;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_usuario
 */
public class Usuario implements Entity<Long> {

    private Long id;
    private String login;
    private String senha;
    private String nome;
    private EnumPerfil perfil;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(EnumPerfil perfil) {
        this.perfil = perfil;
    }

}
