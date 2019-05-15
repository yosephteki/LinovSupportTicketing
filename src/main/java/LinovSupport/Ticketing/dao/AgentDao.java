/**
 * 
 */
package LinovSupport.Ticketing.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import LinovSupport.Ticketing.model.Agent;

/**
 * @author Yosep Teki
 *
 */
@Repository("AgentDao")
public class AgentDao extends CommonDao {

	@SuppressWarnings("unchecked")
	@Transactional
	public Agent findById(String id) {
		List<Agent> list = super.entityManager.createQuery("FROM Agent WHERE idAgent=:id").setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Agent();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Agent findByBk(String bk1, String bk2) {
		List<Agent> list = super.entityManager.createQuery("FROM Agent WHERE idAccount=:idacc AND email=:email")
				.setParameter("idacc", bk1).setParameter("email", bk2).getResultList();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Agent();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isIDExist(String id) {
		List<Agent> list = super.entityManager.createQuery("FROM Agent WHERE idAgent=:id").setParameter("id", id)
				.getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public boolean isBkExist(String bk1, String bk2) {
		List<Agent> list = super.entityManager.createQuery("FROM Agent WHERE idAccount=:idacc AND email=:email")
				.setParameter("idacc", bk1).setParameter("email", bk2).getResultList();
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void create(Agent agent) {
		super.entityManager.merge(agent);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Agent> findByFilter(String email, String nama) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Agent WHERE 1=1");
		if (!email.trim().isEmpty()) {
			hql.append(" AND email=:email");
		}
		if (!nama.trim().isEmpty()) {
			hql.append(" AND nama=:nama");
		}
		Query query = super.entityManager.createQuery(hql.toString());

		if (!email.trim().isEmpty()) {
			query.setParameter("email", email);
		}
		if (!nama.trim().isEmpty()) {
			query.setParameter("nama", nama);
		}
		List<Agent> agents = query.getResultList();

		return agents;
	}

	@Transactional
	public void update(Agent agent) {
		super.entityManager.merge(agent);
	}

	@Transactional
	public void delete(String id) {
		Agent agent = findById(id);
		super.entityManager.remove(agent);
	}

}
