/**
 * 
 */
package com.feng.ssh.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.feng.ssh.hibernate.HbmBaseDAO;

/**
 * Hibernate 的基础dao
 * 
 * @author feng
 *
 */
public class HbmBaseDAOImpl implements HbmBaseDAO {

	private SessionFactory sessionFactory;

	/** 封装一下便于调用 */
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Object o) {
		this.getCurrentSession().save(o);
	}

	@Override
	public void update(Object o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(Object o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public void merge(Object o) {
		this.getCurrentSession().merge(o);
	}

	@Override
	public List<?> find(String hql, Object... params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	@Override
	public List<?> find(String hql, List<?> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.list();
	}

	/* 自己写 的hql————in方法 */
	public List<?> findin(String hql, List<?> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameterList("alist", params);
		return query.list();
	}

	@Override
	public List<?> find(String hql, int page, int rows, List<?> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.list();
	}

	@Override
	public List<?> find(String hql, int page, int rows, Object... params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.list();
	}

	@Override
	public Object get(Class<?> c, Serializable id) {
		return this.getCurrentSession().get(c, id);
	}

	@Override
	public Object get(String hql, Object... params) {
		List<?> list = this.find(hql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Object get(String hql, List<?> params) {
		List<?> list = this.find(hql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Object load(Class<?> c, Serializable id) {
		return this.getCurrentSession().load(c, id);
	}

	@Override
	public Long count(String hql, Object... params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql, List<?> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public int executeNonQuery(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public Query createQuery(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query;
	}

	@Override
	public void delete(Object o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public Long count(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}
}
