package br.com.tomcat.vo;

import br.com.tomcat.enums.EnumPerfil;

/**
 * Created by ronnie-msl on 19/09/17.
 */
public class UsuarioVO {

    private EnumPerfil[] enumPerfis;

    public EnumPerfil[] getEnumPerfis() {
        return enumPerfis;
    }

    public void setEnumPerfis(EnumPerfil[] enumPerfis) {
        this.enumPerfis = enumPerfis;
    }

}
