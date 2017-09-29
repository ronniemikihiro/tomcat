package br.com.tomcat.entity;

import java.io.Serializable;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public interface Entity<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(final ID id);
}
