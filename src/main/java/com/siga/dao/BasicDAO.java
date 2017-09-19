package com.siga.dao;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siga.model.AbstractBean;
import com.siga.model.Convidado;

import java.util.List;
import java.util.Map;

public interface BasicDAO{
	public abstract List<? extends AbstractBean> findByNamedQuery(String s, Map<String, Object> map);

	public abstract List<? extends AbstractBean> findByNamedQuery(String s);

	public abstract List<? extends AbstractBean> findByQuery(String s, Map<String, Object> map);

	public abstract List<? extends AbstractBean> findByNamedQuery(String s, Map<String, Object> map, int maxResults);

	public abstract List<? extends AbstractBean> findByNamedQuery(String s, int maxResults);

	public abstract List<? extends AbstractBean> findByQuery(String s, Map<String, Object> map, int maxResults);

	public abstract AbstractBean save(AbstractBean abstractbean);

	public abstract AbstractBean saveFlushAndClear(AbstractBean abstractbean);

	public abstract void update(AbstractBean abstractbean);

	public abstract void delete(AbstractBean abstractbean);

	public abstract void clear();

	public abstract void flushAndClear();

	public abstract void flush();
}
