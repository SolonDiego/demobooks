package br.com.solondiego.demobooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.solondiego.demobooks.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
