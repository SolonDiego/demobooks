package br.com.solondiego.demobooks.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.solondiego.demobooks.data.vo.v1.PersonVO;
import br.com.solondiego.demobooks.mapper.DozerMapper;
import br.com.solondiego.demobooks.repositories.PersonRepository;



@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll(){
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		return persons;
	}

}
