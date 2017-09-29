package br.com.tomcat.enums;

import java.util.Arrays;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public enum EnumModo {

    LIST('L'),
    INSERT('I'),
    UPDATE('U'),
    VIEW('V');

    private Character descricao;

    private EnumModo(final Character descricao) {
        this.descricao = descricao;
    }

    public Character getDescricao() {
        return descricao;
    }

    public static EnumModo getEnumPerfil(final Character descricao) {
        return Arrays.stream(EnumModo.values()).filter(p -> p.getDescricao().equals(descricao)).findAny().orElse(null);
    }
}
