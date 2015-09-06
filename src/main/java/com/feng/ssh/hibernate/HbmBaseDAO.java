package com.feng.ssh.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/** 为了与HBase分开,现在先这么干了 */
public interface HbmBaseDAO {

	Session getSession();

	void save(Object o);

	void update(Object o);

	/** 可重入的,更新或者插入 */
	void saveOrUpdate(Object o);

	void merge(Object o);

	void delete(Object o);

	List<?> find(String hql, Object... params);

	List<?> find(String hql, List<?> params);

	/* 自己写的hql————in方法 */
	List<?> findin(String hql, List<?> params);

	/** 分页 */
	List<?> find(String hql, int page, int rows, List<?> params);

	/** 分页 */
	List<?> find(String hql, int page, int rows, Object... params);

	Object get(Class<?> c, Serializable id);

	Object get(String hql, Object... params);

	Object get(String hql, List<?> params);

	Object load(Class<?> c, Serializable id);

	Long count(String hql, Object... params);

	Long count(String hql);

	Long count(String hql, List<?> params);

	int executeNonQuery(String hql);

	Query createQuery(String hql);

}
