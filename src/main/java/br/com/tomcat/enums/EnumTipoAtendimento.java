package br.com.tomcat.enums;

import java.util.Arrays;

/**
 * Created by ronnie-msl on 29/09/17.
 */
public enum EnumTipoAtendimento {

    P("Pedido"),
    R("Em preparo"),
    E("Entregue");

    private String descricao;

    private EnumTipoAtendimento(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumTipoAtendimento getEnumTipoAtendimento(final String name) {
        return Arrays.stream(EnumTipoAtendimento.values()).filter(p -> p.name().equals(name)).findAny().orElse(null);
    }
}
