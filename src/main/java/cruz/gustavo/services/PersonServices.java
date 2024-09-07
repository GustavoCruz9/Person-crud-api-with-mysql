package cruz.gustavo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cruz.gustavo.exceptions.ResourceNotFoundException;
import cruz.gustavo.model.Person;
import cruz.gustavo.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository pRep;

	public Person findById(Long id) {

		logger.info("Finding one person!");

		return pRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}

	public List<Person> findAll() {

		List<Person> persons = new ArrayList<>();

		return pRep.findAll();
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");

		return pRep.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		var entity = pRep.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return pRep.save(person);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = pRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		pRep.delete(entity);
		
	}

}
