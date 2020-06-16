package com.in28Minutes.rest.webservices.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long>{
	
	public List<Todo> findByUsername(String username);

}
