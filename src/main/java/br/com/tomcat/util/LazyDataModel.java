package br.com.tomcat.util;

import br.com.tomcat.entity.Entity;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 15/09/17.
 */
public class LazyDataModel<E extends Entity> extends org.primefaces.model.LazyDataModel<E> {

    @Override
    public List<E> load(final int first,
                        final int pageSize,
                        final String sortField,
                        final SortOrder sortOrder,
                        final Map<String, Object> filters) {
        throw new UnsupportedOperationException("Lazy loading is not implemented.");
    }

    @Override
    public E getRowData(final String rowKey) {
        try{
            return ((List<E>) getWrappedData()).stream().filter(v -> v.getId().toString().equals(rowKey)).findAny().orElse(null);
        }catch(Exception e) {
            throw new RuntimeException("Propriedade [rowKey] deve ser definida!");
        }
    }

    @Override
    public Object getRowKey(final E entity) {
        return entity.getId();
    }

}
