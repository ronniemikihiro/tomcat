package br.com.tomcat.enums;

import java.util.Arrays;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public enum EnumPerfil {

    U("Usuário"),
    A("Administrador");

    private String descricao;

    private EnumPerfil(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumPerfil getEnumPerfil(final String name) {
        return Arrays.stream(EnumPerfil.values()).filter(p -> p.name().equals(name)).findAny().orElse(null);
    }
}
