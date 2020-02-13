package com.at.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.model.Sprint;
import com.at.model.persistence.SprintRepository;

@Service
public class SprintBusiness implements ISprintBusiness {

	@Autowired
	private SprintRepository sprintDAO;
	
	@Override
	public Sprint load(long id) throws BusinessException, NotFoundException {
		Optional<Sprint> o;
		try {
			o = sprintDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (o.isPresent())
			return o.get();
		else
			throw new NotFoundException();
	}

	@Override
	public Sprint add(Sprint sprint) throws BusinessException {
		try {
			return sprintDAO.save(sprint);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(long id) throws BusinessException {
		try {
			sprintDAO.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public Sprint update(Sprint sprint) throws BusinessException {
		try {
			return sprintDAO.save(sprint);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Sprint> list() throws BusinessException {
		try {
			return sprintDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
