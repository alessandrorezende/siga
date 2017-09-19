package com.siga.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.siga.model.AbstractBean;

@Repository(value = "basicDAO")
public class BasicDAOImpl implements BasicDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(type = javax.persistence.PersistenceContextType.EXTENDED)
	protected EntityManager entityManager;

	private static Logger logger = Logger.getLogger(BasicDAOImpl.class);

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<? extends AbstractBean> findByNamedQuery(String namedQuery, Map<String, Object> namedParams) {
		try {
			logger.info("Procurando pela namedQuery " + namedQuery + " com " + namedParams.size() + " parametros");
			Query query = entityManager.createNamedQuery(namedQuery);
			if (namedParams != null) {
				Entry<String, Object> mapEntry;
				for (Iterator it = namedParams.entrySet().iterator(); it.hasNext(); query
						.setParameter((String) mapEntry.getKey(), mapEntry.getValue())) {
					mapEntry = (Entry<String, Object>) it.next();
					logger.info("Param: " + mapEntry.getKey() + ", Value: " + mapEntry.getValue());
				}

			}
			List<? extends AbstractBean> returnList = (List<? extends AbstractBean>) query.getResultList();
			logger.info("Objetos Encontrados: " + returnList.size());

			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Ocorreu um erro ao executar o findByNamedQuery com parâmetros. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao executar o  findByNamedQuery com parâmetros");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<? extends AbstractBean> findByNamedQuery(String namedQuery) {
		try {
			logger.info((new StringBuilder("Procurando pela namedQuery ")).append(namedQuery)
					.append(" sem nenhum parametro").toString());
			Query query = entityManager.createNamedQuery(namedQuery);
			List<? extends AbstractBean> returnList = (List<? extends AbstractBean>) query.getResultList();

			logger.info("Objetos Encontrados: " + returnList.size());
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Ocorreu um erro ao executar o findByNamedQuery sem parâmetros. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao executar o findByNamedQuery sem parâmetros");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<? extends AbstractBean> findByQuery(String hql, Map namedParams) {
		try {
			logger.info((new StringBuilder("Procurando pela query: ")).append(hql).toString());
			Query query = entityManager.createQuery(hql);
			if (namedParams != null) {
				Entry mapEntry;
				for (Iterator it = namedParams.entrySet().iterator(); it.hasNext(); query
						.setParameter((String) mapEntry.getKey(), mapEntry.getValue())) {
					mapEntry = (Entry) it.next();
					logger.info("Param: " + mapEntry.getKey() + ", Value: " + mapEntry.getValue());
				}
			}
			List<? extends AbstractBean> returnList = (List<? extends AbstractBean>) query.getResultList();
			logger.info("Objetos Encontrados: " + returnList.size());

			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocorreu um erro ao executar o findByQuery. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao executar o findByQuery");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<? extends AbstractBean> findByNamedQuery(String namedQuery, Map<String, Object> namedParams,
			int maxResults) {
		try {
			logger.info("Procurando pela namedQuery " + namedQuery + " com " + namedParams.size() + " parametros");
			Query query = entityManager.createNamedQuery(namedQuery);
			query.setMaxResults(maxResults);
			if (namedParams != null) {
				Entry<String, Object> mapEntry;
				for (Iterator it = namedParams.entrySet().iterator(); it.hasNext(); query
						.setParameter((String) mapEntry.getKey(), mapEntry.getValue())) {

					mapEntry = (Entry<String, Object>) it.next();
					logger.info("Param: " + mapEntry.getKey() + ", Value: " + mapEntry.getValue());
				}
			}
			List<? extends AbstractBean> returnList = (List<? extends AbstractBean>) query.getResultList();
			logger.info("Objetos Encontrados: " + returnList.size());

			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Ocorreu um erro ao executar o findByNamedQuery com parâmetros. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao executar o findByNamedQuery com parâmetros");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<? extends AbstractBean> findByNamedQuery(String namedQuery, int maxResults) {
		try {
			logger.info((new StringBuilder("Procurando pela namedQuery ")).append(namedQuery)
					.append(" sem nenhum parametro").toString());
			Query query = entityManager.createNamedQuery(namedQuery);
			query.setMaxResults(maxResults);
			List<? extends AbstractBean> returnList = (List<? extends AbstractBean>) query.getResultList();
			logger.info("Objetos Encontrados: " + returnList.size());

			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Ocorreu um erro ao executar o findByNamedQuery sem parâmetros. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao executar o findByNamedQuery  sem parâmetros");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<? extends AbstractBean> findByQuery(String hql, Map namedParams, int maxResults) {
		try {
			logger.info((new StringBuilder("Procurando pela query: ")).append(hql).toString());
			Query query = entityManager.createQuery(hql);
			query.setMaxResults(maxResults);
			if (namedParams != null) {
				Entry mapEntry;
				for (Iterator it = namedParams.entrySet().iterator(); it.hasNext(); query
						.setParameter((String) mapEntry.getKey(), mapEntry.getValue())) {
					mapEntry = (Entry) it.next();
					logger.info("Param: " + mapEntry.getKey() + ", Value: " + mapEntry.getValue());
				}
			}
			List<? extends AbstractBean> returnList = (List<? extends AbstractBean>) query.getResultList();
			logger.info("Objetos Encontrados: " + returnList.size());
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocorreu um erro ao executar o findByQuery. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao executar o findByQuery");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public AbstractBean save(AbstractBean bean) {
		try {
			logger.info("Salvando Bean " + bean.getClass().getName());
			entityManager.persist(bean);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocorreu um erro ao tentar salvar. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao tentar salvar");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public AbstractBean saveFlushAndClear(AbstractBean bean) {
		try {
			logger.info("Salvando Bean " + bean.getClass().getName());
			entityManager.persist(bean);
			entityManager.flush();
			entityManager.clear();
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocorreu um erro ao tentar salvar. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao tentar salvar");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void update(AbstractBean bean) {
		try {
			logger.info("Alterando Bean " + bean.getClass().getName());
			entityManager.merge(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocorreu um erro ao tentar atualizar. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao tentar atualizar");
		}

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void delete(AbstractBean bean) {
		try {
			logger.info("Deletando Bean " + bean.getClass().getName());
			entityManager.remove(bean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocorreu um erro ao tentar deletar. MSG ORIGINAL: " + e.getMessage());
			throw new DAOException("Ocorreu um erro ao tentar deletar");
		}

	}

	@Override
	public void clear() {
		entityManager.clear();
	}

	@Override
	public void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

	@Override
	public void flush() {
		entityManager.flush();
	}

}
