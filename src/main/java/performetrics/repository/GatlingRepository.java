package performetrics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import performetrics.domain.ExecutionSteps;

@Repository
public interface GatlingRepository extends CrudRepository<ExecutionSteps, Long>  {

}
