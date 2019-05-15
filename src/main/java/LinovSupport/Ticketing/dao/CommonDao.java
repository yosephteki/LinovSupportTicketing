/**
 * 
 */
package LinovSupport.Ticketing.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Yosep Teki
 *
 */
@Repository
public abstract class CommonDao {
	@PersistenceContext
	EntityManager entityManager;
}
