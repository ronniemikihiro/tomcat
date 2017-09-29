package br.com.tomcat.enums;

import java.util.Arrays;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public enum EnumSortOrder {

    ASCENDING(" ASC "),
    DESCENDING(" DESC ");

    private String descricao;

    private EnumSortOrder(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumSortOrder getEnumOrder(final String name) {
        return Arrays.stream(EnumSortOrder.values()).filter(p -> p.name().equals(name)).findAny().orElse(null);
    }
}
