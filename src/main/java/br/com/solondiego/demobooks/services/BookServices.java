package br.com.solondiego.demobooks.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.solondiego.demobooks.controllers.BookController;
import br.com.solondiego.demobooks.data.vo.v1.BookVO;
import br.com.solondiego.demobooks.exceptions.RequiredObjectIsNullException;
import br.com.solondiego.demobooks.exceptions.ResourceNotFoundException;
import br.com.solondiego.demobooks.mapper.DozerMapper;
import br.com.solondiego.demobooks.model.Book;
import br.com.solondiego.demobooks.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;

	public List<BookVO> findAll() {

		logger.info("Buscando todos os livros!");

		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);

		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;

	}

	public BookVO findById(Long id) {

		logger.info("Buscando um livro!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	public BookVO create(BookVO book) {
		if (book == null)
			throw new RequiredObjectIsNullException();

		logger.info("Incluindo um livro!");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public BookVO update(BookVO book) {
		if (book == null)
			throw new RequiredObjectIsNullException();

		logger.info("Atualizando um livro!");

		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLauncDate(book.getLauncDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new RequiredObjectIsNullException("Nenhum registro encontrado para este ID!"));
		repository.delete(entity);
	}

}
