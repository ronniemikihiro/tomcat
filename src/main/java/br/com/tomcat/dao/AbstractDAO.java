package br.com.tomcat.dao;

import br.com.tomcat.entity.Entity;
import br.com.tomcat.util.StringUtil;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ronnie-msl on 15/09/17.
 */
@Repository
public abstract class AbstractDAO<E extends Entity, DTO extends Entity> {

    private final Logger log = Logger.getLogger(getEntityClass());

    @PersistenceContext
    private EntityManager em;

    public Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public int getRowCountListAll(final String filterGlobal) throws Exception {
        throw new UnsupportedOperationException("Row count list all lazy is not implemented.");
    }

    public List<DTO> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final String filterGlobal) throws Exception {
        throw new UnsupportedOperationException("List all lazy is not implemented.");
    }

    public int getRowCountListAll(final Map<String, Object> filters) throws Exception {
        throw new UnsupportedOperationException("Row count list all lazy is not implemented.");
    }

    public List<DTO> listAllLazyDataModel(final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters) throws Exception {
        throw new UnsupportedOperationException("List all lazy is not implemented.");
    }

    public E loadEntity(final DTO dto) throws Exception {
        try {
            final Class<E> clazz = getEntityClass();
            final StringBuilder hql = new StringBuilder("select distinct e from " + clazz.getSimpleName() + " e");
            for(final Field field : clazz.getDeclaredFields()) {
                final boolean isCollection = field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class);
                final boolean isSingleMap = field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(ManyToOne.class);
                hql.append(isCollection ? " left join fetch e." + field.getName() : isSingleMap ? " join fetch e." + field.getName() : "");
            }
            hql.append(" where e.id = :id");
            return (E) em.createQuery(hql.toString()).setParameter("id", dto.getId()).getSingleResult();
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void insert(E entity) throws Exception {
        try{
            em.persist(entity);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void update(E entity) throws Exception {
        try{
            em.merge(entity);
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
    }

    public void delete(E entity) throws Exception {
        try{
            em.remove(em.merge(entity));
        }catch(Exception e) {
            log.debug(e);
            log.error(e);
            throw new Exception(e);
        }
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

    public String getWhereGlobalFilter(final String filterGlobal, final String... columns) {
        final StringBuilder where = new StringBuilder();
        int i = 0;
        for(String column : columns) {
            where.append((i == 0 ? " where " : " or ") + column + " like '%"+filterGlobal+"%'");
            i++;
        }
        return where.toString();
    }

}
