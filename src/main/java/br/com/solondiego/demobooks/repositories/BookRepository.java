package br.com.solondiego.demobooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.solondiego.demobooks.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
