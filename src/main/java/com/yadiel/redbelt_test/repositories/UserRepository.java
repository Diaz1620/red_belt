package com.yadiel.redbelt_test.repositories;

import org.springframework.data.repository.CrudRepository;

import com.yadiel.redbelt_test.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
