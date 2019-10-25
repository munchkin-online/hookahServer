package hello.repository;

import hello.entities.Tobacco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TobaccoRepository extends CrudRepository<Tobacco,Integer> {
    Tobacco findByLabel(String label);
}
