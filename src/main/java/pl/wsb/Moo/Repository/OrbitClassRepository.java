package pl.wsb.Moo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.Moo.Model.OrbitClassEntity;

public interface OrbitClassRepository extends JpaRepository<OrbitClassEntity, Long> {
}