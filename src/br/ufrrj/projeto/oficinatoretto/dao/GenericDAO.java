package br.ufrrj.projeto.oficinatoretto.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import br.ufrrj.projeto.oficinatoretto.model.IEntity;

public abstract class GenericDAO<T extends IEntity> {
	
	@PersistenceContext(unitName="oficinaTorettoDB")
	protected final EntityManager entityManager;
	protected final Class<T> persistentClass;
	
	public GenericDAO() {
        this.entityManager = EntityManagerUtil.getEntityManager();
        this.persistentClass = (Class<T>) ((ParameterizedType) 
			getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	public EntityManager getEntityManager() {
        return entityManager;
    }
	
	protected void save(T entity) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            getEntityManager().persist(entity);
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            tx.rollback();
        } finally {
            close();
        }
    }

    protected void update(T entity) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            getEntityManager().merge(entity);
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            tx.rollback();
        } finally {
            close();
        }

    }

    protected void delete(T entity) {
        EntityTransaction tx = getEntityManager().getTransaction();

        try {
            tx.begin();
            getEntityManager().remove(getEntityManager().merge(entity));
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            tx.rollback();
        } finally {
            close();
        }
    }

    public List<T> findAll() throws Exception {
        Session session = (Session) getEntityManager().getDelegate();
        return session.createCriteria(persistentClass).list();
    }

    public abstract T findById(Integer id);

    protected void close() {
        if (getEntityManager().isOpen()) {
            getEntityManager().close();
        }
    }

}
