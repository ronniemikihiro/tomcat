package br.com.tomcat.entity;

import br.com.tomcat.enums.EnumAtivo;
import br.com.tomcat.enums.EnumPerfil;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by ronnie-msl on 13/09/17.
 * Tabela BD: tb_usuario
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario implements br.com.tomcat.entity.Entity<Long> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, length = 30)
    private String login;

    @Column(name = "senha", nullable = false, length = 50)
    private String senha;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "perfil", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private EnumPerfil perfil;

    @Column(name = "senha_ativa", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private EnumAtivo senhaAtiva;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public EnumAtivo getSenhaAtiva() {
        return senhaAtiva;
    }

    public void setSenhaAtiva(EnumAtivo senhaAtiva) {
        this.senhaAtiva = senhaAtiva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
