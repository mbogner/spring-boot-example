package pm.mbo.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import pm.mbo.springboot.model.Reading;

/**
 * A repository for the entity is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 */
public interface ReadingRepository extends CrudRepository<Reading, Long> {
}
