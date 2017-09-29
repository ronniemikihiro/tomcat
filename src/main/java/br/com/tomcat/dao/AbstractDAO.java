package br.com.tomcat.dao;

import br.com.tomcat.entity.Entity;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 15/09/17.
 */
@Repository
public abstract class AbstractDAO<E extends Entity, DTO extends Entity> {

    public int getRowCountListAll(final Map<String, Object> filters) throws Exception {
        throw new UnsupportedOperationException("Row count list all lazy is not implemented.");
    }

    public List<DTO> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters) throws Exception {
        throw new UnsupportedOperationException("List all lazy is not implemented.");
    }

    public E loadEntity(final DTO dto) throws Exception {
        throw new UnsupportedOperationException("Load entity is not implemented.");
    }

    public void insert(E entity) throws Exception  {
        throw new UnsupportedOperationException("Insert is not implemented.");
    }

    public void update(E entity) throws Exception  {
        throw new UnsupportedOperationException("Update is not implemented.");
    }

    public void delete(E entity) throws Exception  {
        throw new UnsupportedOperationException("Delete is not implemented.");
    }

    protected String getWhereLazyDataModel(final Map<String, Object> filters) {
        final StringBuilder where = new StringBuilder();
        int i = 0;
        for(Map.Entry<String, Object> v : filters.entrySet()) {
            where.append((i == 0 ? " where " : " and ") + v.getKey().replace("\'","").replace("#", "'").replace("filter", v.getValue().toString().trim()));
            i++;
        }
        return where.toString();
    }

}
