package br.com.tomcat.controller;

import br.com.tomcat.entity.Entity;
import br.com.tomcat.enums.EnumModo;
import br.com.tomcat.service.AbstractBO;
import br.com.tomcat.util.JSFUtil;
import br.com.tomcat.util.LazyDataModel;
import br.com.tomcat.util.ObjectUtil;
import org.primefaces.model.SortOrder;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public abstract class AbstractMB<E extends Entity, DTO extends Entity> {

    private E entity;
    private DTO dto;
    private LazyDataModel<DTO> lazyDataModel;
    private Character modo = EnumModo.LIST.getDescricao();

    public abstract AbstractBO<E, DTO> getBO();

    public abstract String getPage();

    private Class<E> getEntityClass() throws Exception {
        return (Class<E>) Class.forName(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName());
    }

    public E getEntity() {
        if(ObjectUtil.isNull(entity)) {
            newEntity();
        }
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public void newEntity() {
        try{
            entity = getEntityClass().newInstance();
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    public void loadEntity() {
        try {
            setEntity(getBO().loadEntity(getDto()));
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    public void loadLazyDataModel() {
        setLazyDataModel(new LazyDataModel<DTO>() {
            @Override
            public List<DTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                try {
                    setRowCount(first == 0 ? getBO().getRowCountListAll(filters) : getRowCount());
                    return getBO().listAllLazyDataModel(first, pageSize, sortField, sortOrder, filters);
                }catch(Exception e) {
                    JSFUtil.addError(e);
                }
                return new ArrayList<>();
            }
        });
    }

    public void save() {
        try {
            if(ObjectUtil.isNull(getEntity().getId())) {
                getBO().insert(getEntity());
                JSFUtil.addInfo("Cadastro realizado com sucesso!");
            }else {
                getBO().update(getEntity());
                JSFUtil.addInfo("Alteração realizado com sucesso!");
            }
            JSFUtil.goPage(getPage());
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    public void delete() {
        try {
            getBO().delete(getEntity());
            JSFUtil.addInfo("Exclusão realizado com sucesso!");
            JSFUtil.goPage(getPage());
        }catch(Exception e) {
            JSFUtil.addError(e);
        }
    }

    public void changeModoList() {
        modo = EnumModo.LIST.getDescricao();
    }

    public DTO getDto() {
        return dto;
    }

    public void setDto(DTO dto) {
        this.dto = dto;
    }

    public LazyDataModel<DTO> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<DTO> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public Character getModo() {
        return modo;
    }

    public void setModo(Character modo) {
        this.modo = modo;
    }

    public boolean isModoList() {
        return EnumModo.LIST.getDescricao().equals(modo);
    }

    public boolean isModoInsert() {
        return EnumModo.INSERT.getDescricao().equals(modo);
    }

    public boolean isModoUpdate() {
        return EnumModo.UPDATE.getDescricao().equals(modo);
    }

    public boolean isModoView() {
        return EnumModo.VIEW.getDescricao().equals(modo);
    }

}
