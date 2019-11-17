package hello.repository;

import hello.entities.Tobacco;
import org.springframework.data.repository.CrudRepository;

public interface TobaccoRepository extends CrudRepository<Tobacco,Integer> {
    Tobacco findByLabel(String label);
    int findIdByFlavor(String flavor);
}
