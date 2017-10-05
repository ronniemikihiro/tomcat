package br.com.tomcat.enums;

import java.util.Arrays;

/**
 * Created by ronnie-msl on 04/10/17.
 */
public enum EnumAtivo {

    S("Sim"),
    N("Não");

    private String descricao;

    private EnumAtivo(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumAtivo getEnumAtivo(final String name) {
        return Arrays.stream(EnumAtivo.values()).filter(p -> p.name().equals(name)).findAny().orElse(null);
    }
}
