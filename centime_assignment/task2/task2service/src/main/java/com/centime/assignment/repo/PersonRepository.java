package com.centime.assignment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centime.assignment.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
	public List<Person> findByparentId(Long parentId);


}
