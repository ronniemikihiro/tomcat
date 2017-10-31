package br.com.tomcat.service;

import br.com.tomcat.dao.AbstractDAO;
import br.com.tomcat.entity.Entity;
import br.com.tomcat.util.JSFUtil;
import br.com.tomcat.util.ObjectUtil;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 13/09/17.
 */
@Service
public abstract class AbstractBO<E extends Entity, DTO extends Entity> {

    public abstract AbstractDAO<E, DTO> getDAO();

    public int getRowCountLazyDataModel(final String filterGlobal) throws Exception {
        return getDAO().getRowCountLazyDataModel(filterGlobal);
    }

    public List<DTO> listLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final String filterGlobal) throws Exception {
        return getDAO().listLazyDataModel(first, pageSize, sortField, sortOrder, filterGlobal);
    }

    public int getRowCountLazyDataModel(final Map<String, Object> filters) throws Exception {
        return getDAO().getRowCountLazyDataModel(filters);
    }

    public List<DTO> listLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters) throws Exception {
        return getDAO().listLazyDataModel(first, pageSize, sortField, sortOrder, filters);
    }

    public E loadEntity(final DTO dto) throws Exception {
        return getDAO().loadEntity(dto);
    }

    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void insert(final E entity) throws Exception {
        getDAO().insert(entity);
    }

    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void update(final E entity) throws Exception {
        getDAO().update(entity);
    }

    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void delete(final E entity) throws Exception {
        getDAO().delete(entity);
    }

}
