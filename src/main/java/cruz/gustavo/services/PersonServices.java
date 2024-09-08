package cruz.gustavo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cruz.gustavo.data.vo.v1.PersonVO;
import cruz.gustavo.data.vo.v2.PersonVOV2;
import cruz.gustavo.exceptions.ResourceNotFoundException;
import cruz.gustavo.mapper.DozerMapper;
import cruz.gustavo.mapper.custom.PersonMapper;
import cruz.gustavo.model.Person;
import cruz.gustavo.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository pRep;

	@Autowired
	PersonMapper mapper;
	
	public PersonVO findById(Long id) {

		logger.info("Finding one person!");

		var entity = pRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {

		List<PersonVO> persons = new ArrayList<>();

		return DozerMapper.parseListObjects(pRep.findAll(), PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");

		var entity = DozerMapper.parseObject(person, Person.class);

		var vo = DozerMapper.parseObject(pRep.save(entity), PersonVO.class);

		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person!");
		
		var entity = mapper.convertVoToEntity(person);
		
		var vo = mapper.convertEntityToVo(pRep.save(entity));
		
		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");

		var entity = pRep.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(pRep.save(entity), PersonVO.class);

		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");

		var entity = pRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		pRep.delete(entity);

	}

}
