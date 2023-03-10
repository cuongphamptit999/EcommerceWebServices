package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.electronics.ElectronicsItem;
import vn.ptit.utils.FilterMap;

@Service
public class ElectronicsService {
	@PersistenceContext
	EntityManager entityManager;

	private int LIMIT = 28;

	@SuppressWarnings("unchecked")
	public List<ElectronicsItem> findByCategory(List<FilterMap> list) {
		int page = 1;

		String jpql = "select p from ElectronicsItem p where 1=1";

		for (FilterMap filterMap : list) {
			if (filterMap.getKey().equalsIgnoreCase("sort")) {
				if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
					jpql += " order by p.price asc";
				} else
					jpql += " order by p.price desc";

			} else if (filterMap.getKey().equalsIgnoreCase("price")) {
				if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
					jpql += " and p.price<10000000";

				} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
					jpql += " and p.price>=10000000 and p.price<20000000";

				} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
					jpql += " and p.price>=20000000 and p.price<30000000";

				} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
					jpql += " and p.price>=30000000 and p.price<40000000";

				} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
					jpql += " and p.price>=40000000 and p.price<50000000";

				} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
					jpql += " and p.price>=50000000";
				}
			} else if (filterMap.getKey().equalsIgnoreCase("screendSize")) {
				jpql += " and p.electronics.screendSize = " + filterMap.getValue();
			} else if (filterMap.getKey().equalsIgnoreCase("pageNumber")) {
				page = Integer.parseInt(filterMap.getValue());
			}

		}

		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectronicsItem> findItemByTypeElectronics(List<FilterMap> list) {
		String jpql = "select p from ElectronicsItem p where 1=1";

		for (FilterMap filterMap : list) {
			if (filterMap.getKey().equalsIgnoreCase("sort")) {
				if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
					jpql += " order by p.price asc";
				} else
					jpql += " order by p.price desc";

			} else if (filterMap.getKey().equalsIgnoreCase("price")) {
				if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
					jpql += " and p.price<10000000";

				} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
					jpql += " and p.price>=10000000 and p.price<20000000";

				} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
					jpql += " and p.price>=20000000 and p.price<30000000";

				} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
					jpql += " and p.price>=30000000 and p.price<40000000";

				} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
					jpql += " and p.price>=40000000 and p.price<50000000";

				} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
					jpql += " and p.price>=50000000";
				}
			} else if (filterMap.getKey().equalsIgnoreCase("screendSize")) {
				jpql += " and p.electronics.screendSize = " + filterMap.getValue();
			}

		}

		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElectronicsItem> findByManufacturer(List<FilterMap> list) {
		int page = 1;
		
		String jpql = "select p from ElectronicsItem p where 1=1 and p.electronics.manufacturer.id = "
				+ list.get(0).getValue();

		for (FilterMap filterMap : list) {
			if (filterMap.getKey().equalsIgnoreCase("sort")) {
				if (filterMap.getValue().equalsIgnoreCase("low-to-high")) {
					jpql += " order by p.price asc";
				} else
					jpql += " order by p.price desc";

			} else if (filterMap.getKey().equalsIgnoreCase("price")) {
				if (filterMap.getValue().compareToIgnoreCase("duoi10trieu") == 0) {
					jpql += " and p.price<10000000";

				} else if (filterMap.getValue().compareToIgnoreCase("10den20trieu") == 0) {
					jpql += " and p.price>=10000000 and p.price<20000000";

				} else if (filterMap.getValue().compareToIgnoreCase("20den30trieu") == 0) {
					jpql += " and p.price>=20000000 and p.price<30000000";

				} else if (filterMap.getValue().compareToIgnoreCase("30den40trieu") == 0) {
					jpql += " and p.price>=30000000 and p.price<40000000";

				} else if (filterMap.getValue().compareToIgnoreCase("40den50trieu") == 0) {
					jpql += " and p.price>=40000000 and p.price<50000000";

				} else if (filterMap.getValue().compareToIgnoreCase("tren50trieu") == 0) {
					jpql += " and p.price>=50000000";
				}
			} else if (filterMap.getKey().equalsIgnoreCase("screendSize")) {
				jpql += " and p.electronics.screendSize = " + filterMap.getValue();
			} else if (filterMap.getKey().equalsIgnoreCase("pageNumber")) {
				page = Integer.parseInt(filterMap.getValue());
			}
		}

		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElectronicsItem> getElectronicsItemBySlug(String slug) {

		String jpql = "select p from ElectronicsItem p";
		if (slug != null) {
			jpql += " where p.slug = '" + slug + "'";
		}

		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElectronicsItem> getSameElectronicsItem(String slug) {

		String jpql = "select p from ElectronicsItem p";
		if (slug != null) {
			jpql += " where p.slug <> '" + slug + "'order by rand ()";
		}

		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);
		query.setMaxResults(4);

		return query.getResultList();
	}

	public List<ElectronicsItem> findByName(String name) {
		String jpql = "SELECT p FROM ElectronicsItem p";
		jpql += " WHERE p.electronics.name LIKE '%" + name + "%'";
		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);
		return query.getResultList();
	}

	public List<ElectronicsItem> get8ItemInHome() {
		String jpql = "select p from ElectronicsItem p ORDER BY substring_index(p.slug, '-', -1) desc";
		Query query = entityManager.createQuery(jpql, ElectronicsItem.class);
		query.setMaxResults(8);

		return query.getResultList();
	}
}
