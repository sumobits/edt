package com.sumobits.edu.tracker.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sumobits.edu.tracker.model.Persistable;

public abstract class GenericDaoImpl<T> implements GenericDao<T>
{
	private static Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	private Class<T> type;

	@Autowired
	@PersistenceContext(unitName = "com.sumobits.edu.tracker.oltp")
	protected EntityManager entityManager;

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public T save(T instance)
	{
		entityManager.persist(entityManager.contains(instance) ? instance : entityManager.merge(instance));
		entityManager.flush();
		logger.debug("Successfully saved " + instance);
		return instance;
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public boolean delete(T instance)
	{
		boolean result = false;
		entityManager.remove(entityManager.getReference(type, ((Persistable)instance).getId()));
		entityManager.flush();
		result = true;
		return result;
	}

}
