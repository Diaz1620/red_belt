package com.yadiel.redbelt_test.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yadiel.redbelt_test.models.Ideas;

@Repository
public interface IdeasRepository  extends CrudRepository<Ideas, Long> {
	
}
