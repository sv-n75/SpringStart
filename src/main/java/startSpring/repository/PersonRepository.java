package startSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import startSpring.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
