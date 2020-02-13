package com.at.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

			//List<Sprint> findbyList(int id);
			
			//List<Sprint>findbyIdSprint(int idSprint);
}
