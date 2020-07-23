package performetrics.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import performetrics.domain.Simulation;


@Repository
public interface SimulationRepository extends CrudRepository<Simulation, Long>  {

	
	Simulation findById(Long id);
	
	List<Simulation> findAll();
}
